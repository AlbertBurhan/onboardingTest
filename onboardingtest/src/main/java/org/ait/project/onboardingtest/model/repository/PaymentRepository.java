package org.ait.project.onboardingtest.model.repository;

import org.ait.project.onboardingtest.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    @Query(value = "SELECT COALESCE(MAX(paymentId+1),1) FROM Payment ")
    Integer getPaymentId();

    @Query(value = "SELECT a FROM Payment a where a.orderId = :id")
    Payment getAllByOrderId(@Param("id") Integer id);
}
