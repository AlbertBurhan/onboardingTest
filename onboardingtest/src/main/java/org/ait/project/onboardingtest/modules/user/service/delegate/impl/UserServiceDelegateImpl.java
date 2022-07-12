package org.ait.project.onboardingtest.modules.user.service.delegate.impl;

import lombok.RequiredArgsConstructor;
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
        return productRepository.findProductByName(name);
    }

    @Override
    public Customer getCustomer(String name, String email){
        return customerRepository.getCustomerByCustNameAndEmail(name, email);
    }

    @Override
    public Integer getMaxId()
    {
        return orderHeaderRepository.getMaxId();
    }

    @Override
    public OrderHeader getOrderHeaderById(Integer id)
    {
        return orderHeaderRepository.getOrderHeaderById(id);
    }

    @Override
    public OrderHeader saveOrder(OrderHeader orderHeader)
    {
        return orderHeaderRepository.save(orderHeader);
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
}
