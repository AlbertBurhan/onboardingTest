package org.ait.project.onboardingtest.modules.user.service.internal;

import org.ait.project.onboardingtest.modules.user.model.entity.Product;
import org.ait.project.onboardingtest.modules.user.dto.request.PlaceOrderDto;
import org.ait.project.onboardingtest.modules.user.dto.request.SubmitPaymentDto;
import org.ait.project.onboardingtest.modules.user.dto.response.ReturnOrderDto;
import org.ait.project.onboardingtest.modules.user.dto.response.ReturnPaymentDto;
import org.ait.project.onboardingtest.shared.dto.template.ResponseDetail;
import org.ait.project.onboardingtest.shared.dto.template.ResponseList;
import org.ait.project.onboardingtest.shared.dto.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseTemplate<ResponseList<Product>>> getProduct();

    ResponseEntity<ResponseTemplate<ResponseDetail<ReturnOrderDto>>> getOrder(PlaceOrderDto placeOrderDto);

    ResponseEntity<ResponseTemplate<ResponseDetail<ReturnPaymentDto>>> sendPaymentProof(SubmitPaymentDto submitPaymentDto);
}
