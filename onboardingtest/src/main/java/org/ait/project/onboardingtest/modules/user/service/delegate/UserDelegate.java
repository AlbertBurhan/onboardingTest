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

    List<OrderHeader> getAllOrderHeader();

    OrderHeader getOrderHeaderById(Integer id);

    OrderHeader saveOrder(OrderHeader orderHeader);

    void deleteOrder(OrderHeader orderHeader);

    List<OrderDetail> getOrderDetailById(Integer id);

    Integer ttlSumDetail(Integer id);

    Integer getDetailMaxId();

    OrderDetail saveDetailOrder(OrderDetail orderDetail);

    Payment getPaymentByOrderId(Integer orderId);

    Integer getPaymentMaxId();

    Payment savePayment(Payment payment);

    Product substractProduct(Integer itemQty, Integer productStock, String name);

    OrderHeader updateShipping(OrderHeader header, Integer validity);

}
