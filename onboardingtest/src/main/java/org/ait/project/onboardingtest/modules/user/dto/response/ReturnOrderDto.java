package org.ait.project.onboardingtest.modules.user.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.ait.project.onboardingtest.modules.user.model.entity.Customer;

@Setter
@Getter
public class ReturnOrderDto {
    private Customer dataCustomer;
    private int totalPrice;
    private String statusOrder;
    private int orderId;
}
