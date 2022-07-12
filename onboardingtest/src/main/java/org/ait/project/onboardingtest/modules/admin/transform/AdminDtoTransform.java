package org.ait.project.onboardingtest.modules.admin.transform;

import org.ait.project.onboardingtest.modules.user.model.entity.OrderHeader;
import org.ait.project.onboardingtest.modules.user.model.entity.Payment;
import org.ait.project.onboardingtest.modules.user.dto.request.ViewOrderDto;
import org.ait.project.onboardingtest.modules.user.dto.response.ReturnPaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring") // for Dependency Injection Spring
public interface AdminDtoTransform {
    @Named("createViewOrderDto")
    @Mapping(source = "orderHeader.id", target = "orderId")
    ViewOrderDto createViewOrderDto(OrderHeader orderHeader);

    @Named("createReturnPaymentDto")
    @Mapping(source = "header", target = "orderPlaced")
    @Mapping(source = "payment.paymentValue", target = "ttlPaid")
    ReturnPaymentDto createReturnPaymentDto(OrderHeader header, Payment payment);
}
