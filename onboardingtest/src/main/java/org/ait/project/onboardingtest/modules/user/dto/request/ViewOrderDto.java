package org.ait.project.onboardingtest.modules.user.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.ait.project.onboardingtest.modules.user.model.entity.OrderDetail;

import java.util.List;

@Setter
@Getter
public class ViewOrderDto {
    private String customerName;
    private String customerEmail;
    private int orderId;
    private List<OrderDetail> orderDetails;
    private int ttlPrice;
}
