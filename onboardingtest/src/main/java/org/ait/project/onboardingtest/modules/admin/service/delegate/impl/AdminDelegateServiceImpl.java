package org.ait.project.onboardingtest.modules.admin.service.delegate.impl;

import lombok.RequiredArgsConstructor;
import org.ait.project.onboardingtest.modules.user.model.entity.OrderDetail;
import org.ait.project.onboardingtest.modules.user.model.entity.OrderHeader;
import org.ait.project.onboardingtest.modules.user.model.entity.Payment;
import org.ait.project.onboardingtest.modules.user.model.repository.OrderDetailRepository;
import org.ait.project.onboardingtest.modules.user.model.repository.OrderHeaderRepository;
import org.ait.project.onboardingtest.modules.user.model.repository.PaymentRepository;
import org.ait.project.onboardingtest.modules.admin.service.delegate.AdminDelegate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminDelegateServiceImpl implements AdminDelegate {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderHeaderRepository orderHeaderRepository;
    private final PaymentRepository paymentRepository;

    public List<OrderHeader> getAllOrderHeader()
    {
        return orderHeaderRepository.findAll();
    }

    public OrderHeader getOrderHeaderById(Integer id)
    {
        return orderHeaderRepository.getOrderHeaderById(id);
    }

    public List<OrderDetail> getOrderDetailById(Integer id)
    {
        return orderDetailRepository.getOrderDetailById(id);
    }

    public Integer ttlSumDetail(Integer id)
    {
        return orderDetailRepository.getTtlPrice(id);
    }

    public Payment getPaymentById(Integer orderId)
    {
        return paymentRepository.getAllByOrderId(orderId);
    }

    public OrderHeader updateShipping(OrderHeader header, Integer validity)
    {
        header.setShippingId(UUID.randomUUID().toString());
        header.setOrderStatus(validity);

        return orderHeaderRepository.save(header);
    }
}
