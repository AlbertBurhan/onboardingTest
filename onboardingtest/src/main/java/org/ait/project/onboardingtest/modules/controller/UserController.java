package org.ait.project.onboardingtest.modules.controller;

import org.ait.project.onboardingtest.modules.dto.request.PlaceOrderDto;
import org.ait.project.onboardingtest.modules.dto.request.SubmitPaymentDto;
import org.ait.project.onboardingtest.modules.dto.response.ReturnOrderDto;
import org.ait.project.onboardingtest.modules.dto.response.ReturnPaymentDto;
import org.ait.project.onboardingtest.modules.service.OrderService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;


    @PostMapping(value = "/order")
    @ResponseBody
    public ReturnOrderDto placeOrder(@RequestBody PlaceOrderDto pods)
    {
        return orderService.createOrder(pods);

    }

    @PostMapping(value = "/payment")
    @ResponseBody
    public ReturnPaymentDto paymentOrder(@RequestBody SubmitPaymentDto submitPaymentDto)
    {
        return orderService.sendPaymentProof(submitPaymentDto);
    }
}
