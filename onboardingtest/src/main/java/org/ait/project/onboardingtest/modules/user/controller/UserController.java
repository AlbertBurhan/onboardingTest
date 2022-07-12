package org.ait.project.onboardingtest.modules.user.controller;

import lombok.RequiredArgsConstructor;
import org.ait.project.onboardingtest.modules.user.model.entity.Product;
import org.ait.project.onboardingtest.modules.user.dto.request.PlaceOrderDto;
import org.ait.project.onboardingtest.modules.user.dto.request.SubmitPaymentDto;
import org.ait.project.onboardingtest.modules.user.dto.response.ReturnOrderDto;
import org.ait.project.onboardingtest.modules.user.dto.response.ReturnPaymentDto;
import org.ait.project.onboardingtest.modules.user.service.internal.UserService;
import org.ait.project.onboardingtest.shared.dto.template.ResponseDetail;
import org.ait.project.onboardingtest.shared.dto.template.ResponseList;
import org.ait.project.onboardingtest.shared.dto.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/products")
    @ResponseBody
    public ResponseEntity<ResponseTemplate<ResponseList<Product>>> getProduct()
    {
        return userService.getProduct();
    }


    @PostMapping(value = "/order")
    @ResponseBody
    public ResponseEntity<ResponseTemplate<ResponseDetail<ReturnOrderDto>>> placeOrder(@RequestBody PlaceOrderDto pods)
    {
        return userService.getOrder(pods);

    }

    @PostMapping(value = "/payment")
    @ResponseBody
    public ResponseEntity<ResponseTemplate<ResponseDetail<ReturnPaymentDto>>> paymentOrder(@RequestBody SubmitPaymentDto submitPaymentDto)
    {
        return userService.sendPaymentProof(submitPaymentDto);
    }
}
