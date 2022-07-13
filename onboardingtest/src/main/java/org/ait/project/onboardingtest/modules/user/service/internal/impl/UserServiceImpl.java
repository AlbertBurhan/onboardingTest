package org.ait.project.onboardingtest.modules.user.service.internal.impl;

import lombok.RequiredArgsConstructor;
import org.ait.project.onboardingtest.modules.user.model.entity.OrderDetail;
import org.ait.project.onboardingtest.modules.user.model.entity.OrderHeader;
import org.ait.project.onboardingtest.modules.user.model.entity.Payment;
import org.ait.project.onboardingtest.modules.user.model.entity.Product;
import org.ait.project.onboardingtest.modules.user.dto.request.ItemOrderDto;
import org.ait.project.onboardingtest.modules.user.dto.request.PlaceOrderDto;
import org.ait.project.onboardingtest.modules.user.dto.request.SubmitPaymentDto;
import org.ait.project.onboardingtest.modules.user.dto.response.ReturnOrderDto;
import org.ait.project.onboardingtest.modules.user.dto.response.ReturnPaymentDto;

import org.ait.project.onboardingtest.modules.user.model.entity.Customer;
import org.ait.project.onboardingtest.modules.user.service.delegate.UserDelegate;
import org.ait.project.onboardingtest.modules.user.service.internal.UserService;
import org.ait.project.onboardingtest.modules.user.transform.UserDtoTransform;
import org.ait.project.onboardingtest.shared.constant.enums.ResponseEnum;
import org.ait.project.onboardingtest.shared.dto.template.ResponseDetail;
import org.ait.project.onboardingtest.shared.dto.template.ResponseList;
import org.ait.project.onboardingtest.shared.dto.template.ResponseTemplate;
import org.ait.project.onboardingtest.shared.utils.ResponseHelper;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ResponseHelper responseHelper;
    private final UserDelegate userDelegate;
    private final UserDtoTransform userDtoTransform;
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<ResponseTemplate<ResponseList<Product>>> getProduct()
    {
        List<Product> products = userDelegate.getAllProduct();

        return responseHelper.createResponseCollection(ResponseEnum.SUCCESS, null, products);
    }

    public ResponseEntity<ResponseTemplate<ResponseDetail<ReturnOrderDto>>> getOrder(PlaceOrderDto placeOrderDto)
    {
        ReturnOrderDto ret;
        Customer customer = userDelegate.getCustomer(placeOrderDto.getCustomerName(), placeOrderDto.getEmail());

        List<ItemOrderDto> items = placeOrderDto.getItems();

        OrderHeader header = userDtoTransform.createOrderHeader(customer);
        header.setId(userDelegate.getMaxId());
        header.setShippingId(null);
        userDelegate.saveOrder(header);

        ret = userDtoTransform.createReturnOrder(customer, header);

        Integer ttlPrice = this.calculatePrice(items, header.getId());
        ret.setTotalPrice(ttlPrice);
        ret.setStatusOrder("Finalized and Submitted");

        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, ret); //response detail tidak sesuai form (?)
    }

    private Integer calculatePrice(List<ItemOrderDto> itemOrderDtos, Integer headerId)
    {
        Integer ttlProductPrice = 0;
        for(ItemOrderDto item : itemOrderDtos)
        {
            Product product = userDelegate.getProductByName(item.getProductName());

            if (product.getQty() == 0) break;

            ttlProductPrice = ttlProductPrice + product.getPrice() * this.checkAvailableQty(item.getQty(), product.getQty());

            this.createAndSaveDetail(headerId, product, this.checkAvailableQty(item.getQty(), product.getQty()));

            this.substractProductStock(item.getQty(), product.getQty(), product.getName());

        }
        return ttlProductPrice;
    }

    private Integer checkAvailableQty(Integer itemBought, Integer productStock)
    {
        if (itemBought > productStock){
            return productStock;
        }
        else
        {
            return itemBought;
        }
    }

    private void createAndSaveDetail(Integer headerId, Product product, Integer qty)
    {
        OrderDetail orderDetail = userDtoTransform.createOrderDetail(product);

        orderDetail.setId(headerId);
        orderDetail.setRowDetailId(userDelegate.getDetailMaxId());
        orderDetail.setProductTotal(product.getPrice() * qty);
        orderDetail.setProductQty(qty);

        userDelegate.saveDetailOrder(orderDetail);
    }

    private void substractProductStock(Integer itemQty, Integer productStock, String name)
    {
        userDelegate.substractProduct(this.checkAvailableQty(itemQty, productStock), productStock, name);
    }

    public ResponseEntity<ResponseTemplate<ResponseDetail<ReturnPaymentDto>>> sendPaymentProof(SubmitPaymentDto submitPaymentDto)
    {
        ReturnPaymentDto ret;
        OrderHeader header = userDelegate.getOrderHeaderById(submitPaymentDto.getOrderId());
        ret = userDtoTransform.createReturnPayment(header, submitPaymentDto);

        this.savePaymentProof(submitPaymentDto);

        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, ret); //sama seperti getOrder, tidak sesuai format
    }

    private void savePaymentProof(SubmitPaymentDto submitPaymentDto)
    {
        Payment payment = userDtoTransform.createSubmitPayment(submitPaymentDto);

        payment.setPaymentId(userDelegate.getPaymentMaxId());

        userDelegate.savePayment(payment);
    }
}
