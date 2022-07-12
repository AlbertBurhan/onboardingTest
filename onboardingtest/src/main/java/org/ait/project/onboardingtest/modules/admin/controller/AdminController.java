package org.ait.project.onboardingtest.modules.admin.controller;

import lombok.RequiredArgsConstructor;
import org.ait.project.onboardingtest.modules.admin.service.internal.AdminService;
import org.ait.project.onboardingtest.modules.user.dto.request.ViewOrderDto;
import org.ait.project.onboardingtest.modules.user.dto.response.ReturnPaymentDto;
import org.ait.project.onboardingtest.shared.dto.template.ResponseDetail;
import org.ait.project.onboardingtest.shared.dto.template.ResponseList;
import org.ait.project.onboardingtest.shared.dto.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping(value = "/order-details")
    public ResponseEntity<ResponseTemplate<ResponseList<ViewOrderDto>>> getOrderDetail()
    {
        return adminService.getAllOrder();
    }

    @GetMapping(value = "/order-details/{id}")
    @ResponseBody
    public ResponseEntity<ResponseTemplate<ResponseDetail<ViewOrderDto>>> getOrderDetailById(@PathVariable("id") Integer id)
    {
        return adminService.getOrderById(id);
    }

    @GetMapping(value = "/validate/{id}")
    @ResponseBody
    public ResponseEntity<ResponseTemplate<ResponseDetail<ReturnPaymentDto>>> validatePayment(@PathVariable("id") Integer id)
    {
        return adminService.validate(id);
    }
}
