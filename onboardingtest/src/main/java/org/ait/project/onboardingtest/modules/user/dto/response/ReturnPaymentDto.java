package org.ait.project.onboardingtest.modules.user.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.ait.project.onboardingtest.modules.user.model.entity.OrderHeader;

@Setter
@Getter
public class ReturnPaymentDto {
    private OrderHeader orderPlaced;
    private int ttlPaid;
    private String status;
}
