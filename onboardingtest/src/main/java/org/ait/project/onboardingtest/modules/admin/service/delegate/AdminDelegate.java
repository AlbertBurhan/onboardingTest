package org.ait.project.onboardingtest.modules.admin.service.delegate;

import org.ait.project.onboardingtest.modules.user.model.entity.OrderDetail;
import org.ait.project.onboardingtest.modules.user.model.entity.OrderHeader;
import org.ait.project.onboardingtest.modules.user.model.entity.Payment;

import java.util.List;

public interface AdminDelegate {
    List<OrderHeader> getAllOrderHeader();

    OrderHeader getOrderHeaderById(Integer id);

    List<OrderDetail> getOrderDetailById(Integer id);

    Integer ttlSumDetail(Integer id);

    Payment getPaymentById(Integer orderId);

    OrderHeader updateShipping(OrderHeader header, Integer validity);

}
