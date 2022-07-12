package org.ait.project.onboardingtest.modules.admin.service.internal;

import org.ait.project.onboardingtest.modules.user.dto.request.ViewOrderDto;
import org.ait.project.onboardingtest.modules.user.dto.response.ReturnPaymentDto;
import org.ait.project.onboardingtest.shared.dto.template.ResponseDetail;
import org.ait.project.onboardingtest.shared.dto.template.ResponseList;
import org.ait.project.onboardingtest.shared.dto.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<ResponseTemplate<ResponseList<ViewOrderDto>>> getAllOrder();

    ResponseEntity<ResponseTemplate<ResponseDetail<ViewOrderDto>>> getOrderById(Integer id);

    ResponseEntity<ResponseTemplate<ResponseDetail<ReturnPaymentDto>>> validate(Integer id);
}
