package org.ait.project.onboardingtest.modules.service;

import org.ait.project.onboardingtest.model.entity.*;
import org.ait.project.onboardingtest.model.repository.*;
import org.ait.project.onboardingtest.modules.dto.request.ItemOrderDto;
import org.ait.project.onboardingtest.modules.dto.request.PlaceOrderDto;
import org.ait.project.onboardingtest.modules.dto.request.SubmitPaymentDto;
import org.ait.project.onboardingtest.modules.dto.request.ViewOrderDto;
import org.ait.project.onboardingtest.modules.dto.response.ReturnOrderDto;
import org.ait.project.onboardingtest.modules.dto.response.ReturnPaymentDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public ReturnOrderDto createOrder(PlaceOrderDto pod)
    {
        Product product;
        ReturnOrderDto ret = new ReturnOrderDto();
        Customer cust = new Customer();
        OrderHeader orderHeader = new OrderHeader();
        OrderDetail orderDetail = new OrderDetail();
        Integer ttlPrice = 0;
        Integer custId = 0;

        if (customerRepository.getCustomerByCustNameAndEmail(pod.getCustomerName(), pod.getEmail())!=null) //untuk nanti validasi data yg dapat, sama dengan yg di db ga, kalau sama akan muncul id selain 0
        {
            custId = customerRepository.getCustomerByCustNameAndEmail(pod.getCustomerName(), pod.getEmail()).getId();
        }

        if (pod.getCustomerName().isEmpty() || pod.getEmail().isEmpty() || pod.getPhoneNo().isEmpty() || pod.getAddress().isEmpty()) //validasi point 4
        {
            ret.setStatusOrder("Customer data incomplete, Order Failed");
            return ret;
        }

        cust.setAddress(pod.getAddress());
        cust.setCustomerName(pod.getCustomerName());
        cust.setPhoneNumber(pod.getPhoneNo());
        cust.setEmail(pod.getEmail());
        cust.setAddress(pod.getAddress());
        cust.setId(custId);

        //create order header and detail
        orderHeader.setId(orderHeaderRepository.getMaxId());
        orderHeader.setCustomerName(pod.getCustomerName());
        orderHeader.setCustomerPhone(pod.getPhoneNo());
        orderHeader.setCustomerEmail(pod.getEmail());
        orderHeader.setCustomerAddress(pod.getAddress());
        orderHeader.setShippingId(null);

        orderDetail.setId(orderHeader.getId());

        orderHeaderRepository.save(orderHeader);

        for(ItemOrderDto item : pod.getItems())
        {
            product = productRepository.findProductByName(item.getProductName());
            if (product.getQty() < item.getQty()) //kalau stock kurang
            {
                if(product.getQty() == 0) { //kalau ada barang kosong, order failed (point 6)
                    ret.setDataCustomer(cust);
                    ret.setStatusOrder("Product not available, Order Failed");

                    orderHeaderRepository.delete(orderHeader);
                    return ret;
                }

                product.setQty(0); //pasti jadi 0
                orderDetail.setProductTotal(product.getQty()*product.getPrice());

                ttlPrice = ttlPrice + (product.getQty()*product.getPrice());
                productRepository.save(product);
            }
            else
            {
                product.setQty(product.getQty()-item.getQty());
                orderDetail.setProductTotal(item.getQty()*product.getPrice());
                ttlPrice = ttlPrice + (item.getQty()*product.getPrice());

                productRepository.save(product);
            }
            orderDetail.setRowDetailId(orderDetailRepository.getRowId());
            orderDetail.setProductName(item.getProductName());
            orderDetail.setProductPrice(product.getPrice());
            orderDetail.setProductQty(item.getQty());

            orderDetailRepository.save(orderDetail);
        }
        ret.setTotalPrice(ttlPrice);
        ret.setDataCustomer(cust);
        ret.setStatusOrder("Finalized and Submitted");
        ret.setOrderId(orderHeader.getId());

        return ret;
    }

    public ReturnPaymentDto sendPaymentProof(SubmitPaymentDto submitPaymentDto)
    {
        ReturnPaymentDto returnPaymentDto = new ReturnPaymentDto();
        OrderHeader orderHeader = orderHeaderRepository.getOrderHeaderById(submitPaymentDto.getOrderId());
        returnPaymentDto.setOrderPlaced(orderHeader);
        returnPaymentDto.setTtlPaid(submitPaymentDto.getTtlPayment());
        logger.info("id : " + submitPaymentDto.getOrderId());

        Payment payment = new Payment();
        payment.setPaymentId(paymentRepository.getPaymentId());
        payment.setOrderId(submitPaymentDto.getOrderId());
        payment.setPaymentValue(submitPaymentDto.getTtlPayment());
        paymentRepository.save(payment);

        return returnPaymentDto;
    }

    public List<ViewOrderDto> getOrderList()
    {
        List<ViewOrderDto> viewOrderDtos = new ArrayList<>();
        if (!orderHeaderRepository.getOrderHeaderList().isEmpty()) {
            List<OrderHeader> orderHeader = orderHeaderRepository.getOrderHeaderList();
            for (OrderHeader order : orderHeader)
            {
                ViewOrderDto viewOrderDto = new ViewOrderDto();
                viewOrderDto.setCustomerName(order.getCustomerName());
                viewOrderDto.setCustomerEmail(order.getCustomerEmail());
                viewOrderDto.setOrderId(order.getId());

                List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailById(order.getId());

                viewOrderDto.setOrderDetails(orderDetails);
                viewOrderDto.setTtlPrice(orderDetailRepository.getTtlPrice(order.getId()));

                viewOrderDtos.add(viewOrderDto);
            }
        }
        return viewOrderDtos;
    }

    public ViewOrderDto getOrderDetail(Integer id)
    {
        ViewOrderDto viewOrderDto = new ViewOrderDto();

        OrderHeader header = orderHeaderRepository.getOrderHeaderById(id);

        viewOrderDto.setCustomerName(header.getCustomerName());
        viewOrderDto.setCustomerEmail(header.getCustomerEmail());
        viewOrderDto.setOrderId(id);
        viewOrderDto.setOrderDetails(orderDetailRepository.getOrderDetailById(id));
        viewOrderDto.setTtlPrice(orderDetailRepository.getTtlPrice(id));

        return viewOrderDto;

    }

    public ReturnPaymentDto validatePayment(Integer id)
    {
        ReturnPaymentDto returnPaymentDto = new ReturnPaymentDto();

        OrderHeader header;
        if (orderHeaderRepository.getOrderHeaderById(id) == null)
        {
            returnPaymentDto.setStatus("No order with this ID");
            return returnPaymentDto;
        }
        else
        {
            header = orderHeaderRepository.getOrderHeaderById(id);
        }

        Payment payment;

        if (paymentRepository.getAllByOrderId(id) == null)
        {
            returnPaymentDto.setStatus("Payment has not been made");
            return returnPaymentDto;
        }
        else
        {
            payment = paymentRepository.getAllByOrderId(id);
        }

        returnPaymentDto.setOrderPlaced(header);

        if(customerRepository.getCustomerByCustNameAndEmail(header.getCustomerName(), header.getCustomerEmail()) == null)//if data customer tidak ada /tidak sama
        {
            returnPaymentDto.setStatus("Name, Phone number, Email, and Address did not match!");
            header.setOrderStatus(99);//99 for cancelled
            orderHeaderRepository.save(header);
            return returnPaymentDto;
        }

        Integer sumTotal  = orderDetailRepository.getTtlPrice(id);

        if (payment.getPaymentValue() != sumTotal)//kalau tidak sama jumlah bayarnya
        {
            returnPaymentDto.setStatus("Invalid, Price did not match");

            header.setOrderStatus(99);//99 for cancelled
            orderHeaderRepository.save(header);
        }
        else
        {
            header.setOrderStatus(1);//1 for confirmed
            String shipping = UUID.randomUUID().toString();
            header.setShippingId(shipping);
            returnPaymentDto.setTtlPaid(payment.getPaymentValue());
            returnPaymentDto.setStatus("Payment proof valid, order processed with shipping number : " + shipping);

            orderHeaderRepository.save(header);
        }

        return returnPaymentDto;
    }
}
