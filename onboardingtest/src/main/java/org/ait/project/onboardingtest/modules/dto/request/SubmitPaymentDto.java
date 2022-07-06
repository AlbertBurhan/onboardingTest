package org.ait.project.onboardingtest.modules.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.ait.project.onboardingtest.model.entity.OrderHeader;

@Setter
@Getter
public class SubmitPaymentDto {
    private int orderId;
    private int ttlPayment;
}
