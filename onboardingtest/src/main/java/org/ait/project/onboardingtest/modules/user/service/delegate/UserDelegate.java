package org.ait.project.onboardingtest.modules.user.service.delegate;

import org.ait.project.onboardingtest.modules.user.model.entity.OrderDetail;
import org.ait.project.onboardingtest.modules.user.model.entity.OrderHeader;
import org.ait.project.onboardingtest.modules.user.model.entity.Payment;
import org.ait.project.onboardingtest.modules.user.model.entity.Product;
import org.ait.project.onboardingtest.modules.user.model.entity.Customer;

import java.util.List;

public interface UserDelegate {

    List<Product> getAllProduct();

    Product getProductByName(String name);

    Customer getCustomer(String name, String email);

    Integer getMaxId();

    OrderHeader getOrderHeaderById(Integer id);

    OrderHeader saveOrder(OrderHeader orderHeader);

    Integer getDetailMaxId();

    OrderDetail saveDetailOrder(OrderDetail orderDetail);

    Integer getPaymentMaxId();

    Payment savePayment(Payment payment);

    Product substractProduct(Integer itemQty, Integer productStock, String name);

}
