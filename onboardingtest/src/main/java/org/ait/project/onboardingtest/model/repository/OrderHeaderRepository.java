package org.ait.project.onboardingtest.model.repository;

import org.ait.project.onboardingtest.model.entity.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, String> {
    @Query(value = "SELECT COALESCE(MAX(id+1),1) FROM OrderHeader ")
    Integer getMaxId();

    @Query(value = "select a from OrderHeader a where a.shippingId is null and a.orderStatus=0")
    List<OrderHeader> getOrderHeaderList();

    @Query(value = "select a from OrderHeader a where a.id = :id")
    OrderHeader getOrderHeaderById(@Param("id") Integer id);
}
