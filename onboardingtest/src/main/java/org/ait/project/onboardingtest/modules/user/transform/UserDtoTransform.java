package org.ait.project.onboardingtest.modules.user.transform;

import org.ait.project.onboardingtest.modules.user.model.entity.OrderDetail;
import org.ait.project.onboardingtest.modules.user.model.entity.OrderHeader;
import org.ait.project.onboardingtest.modules.user.model.entity.Payment;
import org.ait.project.onboardingtest.modules.user.model.entity.Product;
import org.ait.project.onboardingtest.modules.user.dto.request.SubmitPaymentDto;
import org.ait.project.onboardingtest.modules.user.dto.response.ReturnPaymentDto;
import org.ait.project.onboardingtest.modules.user.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring") // for Dependency Injection Spring
public interface UserDtoTransform {

    @Named("createOrderHeader")
    @Mapping(source = "customer.phoneNumber", target = "customerPhone")
    @Mapping(source = "customer.email", target = "customerEmail")
    @Mapping(source = "customer.address", target = "customerAddress")
    OrderHeader createOrderHeader(Customer customer);

    @Named("createOrderDetail")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.qty", target = "productQty")
    @Mapping(source = "product.price", target = "productPrice")
    OrderDetail createOrderDetail(Product product);

    @Named("createReturnPayment")
    @Mapping(source = "submit.ttlPayment", target = "ttlPaid")
    @Mapping(source = "header", target = "orderPlaced")
    ReturnPaymentDto createReturnPayment(OrderHeader header, SubmitPaymentDto submit);

    @Named("createSubmitPayment")
    @Mapping(source = "submitPaymentDto.ttlPayment", target = "paymentValue")
    Payment createSubmitPayment(SubmitPaymentDto submitPaymentDto);
}
