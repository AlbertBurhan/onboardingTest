package org.ait.project.onboardingtest.modules.user.service.delegate.impl;

import lombok.RequiredArgsConstructor;
import org.ait.project.onboardingtest.modules.user.exception.UserRepositoryNotFound;
import org.ait.project.onboardingtest.modules.user.model.entity.OrderDetail;
import org.ait.project.onboardingtest.modules.user.model.entity.OrderHeader;
import org.ait.project.onboardingtest.modules.user.model.entity.Payment;
import org.ait.project.onboardingtest.modules.user.model.entity.Product;
import org.ait.project.onboardingtest.modules.user.model.repository.OrderDetailRepository;
import org.ait.project.onboardingtest.modules.user.model.repository.OrderHeaderRepository;
import org.ait.project.onboardingtest.modules.user.model.repository.PaymentRepository;
import org.ait.project.onboardingtest.modules.user.model.entity.Customer;
import org.ait.project.onboardingtest.modules.user.model.repository.CustomerRepository;
import org.ait.project.onboardingtest.modules.user.model.repository.ProductRepository;
import org.ait.project.onboardingtest.modules.user.service.delegate.UserDelegate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceDelegateImpl implements UserDelegate {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderHeaderRepository orderHeaderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public List<Product> getAllProduct()
    {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByName(String name){
        return productRepository.findByName(name);
    }

    @Override
    public Customer getCustomer(String name, String email){
        return customerRepository.findAllByCustomerNameAndEmail(name, email);
    }

    @Override
    public Integer getMaxId()
    {
        return orderHeaderRepository.getMaxId();
    }

    @Override
    public List<OrderHeader> getAllOrderHeader()
    {
        return orderHeaderRepository.findAll();
    }

    @Override
    public OrderHeader getOrderHeaderById(Integer id)
    {
        return orderHeaderRepository.findById(id).orElseThrow(UserRepositoryNotFound::new);
    }

    @Override
    public OrderHeader saveOrder(OrderHeader orderHeader)
    {
        return orderHeaderRepository.save(orderHeader);
    }

    @Override
    public List<OrderDetail> getOrderDetailById(Integer id)
    {
        return orderDetailRepository.getOrderDetailById(id);
    }

    @Override
    public Integer ttlSumDetail(Integer id)
    {
        return orderDetailRepository.getTtlPrice(id);
    }

    @Override
    public Integer getDetailMaxId()
    {
        return orderDetailRepository.getRowId();
    }

    @Override
    public OrderDetail saveDetailOrder(OrderDetail orderDetail)
    {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public Payment getPaymentById(Integer orderId)
    {
        return paymentRepository.getAllByOrderId(orderId);
    }

    @Override
    public Integer getPaymentMaxId()
    {
        return paymentRepository.getPaymentId();
    }

    @Override
    public Payment savePayment(Payment payment)
    {
        return paymentRepository.save(payment);
    }

    @Override
    public Product substractProduct(Integer itemQty, Integer productStock, String name)
    {
        Product product = this.getProductByName(name);
        product.setQty(productStock - itemQty);

        return productRepository.save(product);
    }

    @Override
    public OrderHeader updateShipping(OrderHeader header, Integer validity)
    {
        header.setShippingId(UUID.randomUUID().toString());
        header.setOrderStatus(validity);

        return orderHeaderRepository.save(header);
    }
}
