package org.ait.project.onboardingtest.modules.controller;

import org.ait.project.onboardingtest.modules.dto.request.ViewOrderDto;
import org.ait.project.onboardingtest.modules.dto.response.ReturnPaymentDto;
import org.ait.project.onboardingtest.modules.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/order-details")
    public List<ViewOrderDto> getOrderDetail()
    {
        return orderService.getOrderList();
    }

    @GetMapping(value = "/order-details/{id}")
    @ResponseBody
    public ViewOrderDto getOrderDetailById(@PathVariable("id") Integer id)
    {
        return orderService.getOrderDetail(id);
    }

    @GetMapping(value = "/validate/{id}")
    @ResponseBody
    public ReturnPaymentDto validatePayment(@PathVariable("id") Integer id)
    {
        return orderService.validatePayment(id);
    }
}
