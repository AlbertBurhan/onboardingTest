package org.ait.project.onboardingtest.modules.admin.service.internal.impl;

import lombok.RequiredArgsConstructor;
import org.ait.project.onboardingtest.modules.user.model.entity.OrderHeader;
import org.ait.project.onboardingtest.modules.user.model.entity.Payment;
import org.ait.project.onboardingtest.modules.admin.service.delegate.AdminDelegate;
import org.ait.project.onboardingtest.modules.admin.service.internal.AdminService;
import org.ait.project.onboardingtest.modules.admin.transform.AdminDtoTransform;
import org.ait.project.onboardingtest.modules.user.dto.request.ViewOrderDto;
import org.ait.project.onboardingtest.modules.user.dto.response.ReturnPaymentDto;
import org.ait.project.onboardingtest.modules.user.service.delegate.UserDelegate;
import org.ait.project.onboardingtest.shared.constant.enums.ResponseEnum;
import org.ait.project.onboardingtest.shared.dto.template.ResponseDetail;
import org.ait.project.onboardingtest.shared.dto.template.ResponseList;
import org.ait.project.onboardingtest.shared.dto.template.ResponseTemplate;
import org.ait.project.onboardingtest.shared.utils.ResponseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminDelegate adminDelegate;
    private final UserDelegate userDelegate;
    private final ResponseHelper responseHelper;
    private final AdminDtoTransform adminDtoTransform;

    public ResponseEntity<ResponseTemplate<ResponseList<ViewOrderDto>>> getAllOrder()
    {
        List<ViewOrderDto> viewOrderDtoList = new ArrayList<>();

        viewOrderDtoList = this.createViewer();

        return responseHelper.createResponseCollection(ResponseEnum.SUCCESS, null, viewOrderDtoList);
    }

    public ResponseEntity<ResponseTemplate<ResponseDetail<ViewOrderDto>>> getOrderById(Integer id)
    {
        OrderHeader orderHeader = adminDelegate.getOrderHeaderById(id);
        ViewOrderDto viewOrderDto = adminDtoTransform.createViewOrderDto(orderHeader);
        viewOrderDto.setTtlPrice(adminDelegate.ttlSumDetail(id));
        viewOrderDto.setOrderDetails(adminDelegate.getOrderDetailById(id));
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, viewOrderDto);
    }

    private List<ViewOrderDto> createViewer()
    {
        List<OrderHeader> headers = adminDelegate.getAllOrderHeader();
        List<ViewOrderDto> viewOrderDtoList = new ArrayList<>();
        for (OrderHeader header : headers)
        {
            ViewOrderDto viewOrderDto = adminDtoTransform.createViewOrderDto(header);
            viewOrderDto.setTtlPrice(adminDelegate.ttlSumDetail(header.getId()));
            viewOrderDto.setOrderDetails(adminDelegate.getOrderDetailById(header.getId()));

            viewOrderDtoList.add(viewOrderDto);
        }
        return viewOrderDtoList;
    }

    public ResponseEntity<ResponseTemplate<ResponseDetail<ReturnPaymentDto>>> validate(Integer id)
    {
        OrderHeader orderHeader = adminDelegate.getOrderHeaderById(id);
        Payment payment = adminDelegate.getPaymentById(id);
        ReturnPaymentDto ret = adminDtoTransform.createReturnPaymentDto(orderHeader, payment);

        Integer validity = 99;

        if (payment.getPaymentValue() == adminDelegate.ttlSumDetail(id)) validity = 1;

        this.updateShipping(orderHeader, validity);

        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, ret);
    }

    private void updateShipping(OrderHeader header, Integer validity)
    {
        adminDelegate.updateShipping(header, validity);
    }
}
