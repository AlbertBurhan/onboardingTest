package org.ait.project.onboardingtest.modules.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubmitPaymentDto {
    private int orderId;
    private int ttlPayment;
}
