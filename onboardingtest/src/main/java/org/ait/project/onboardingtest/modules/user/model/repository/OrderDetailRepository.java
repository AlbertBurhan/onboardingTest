package org.ait.project.onboardingtest.modules.user.model.repository;

import org.ait.project.onboardingtest.modules.user.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    @Query(value = "SELECT COALESCE(MAX(rowDetailId+1),1) FROM OrderDetail ")
    Integer getRowId();

    @Query(value = "SELECT a from OrderDetail a where a.id = :id")
    List<OrderDetail> getOrderDetailById(@Param("id") Integer id);

    @Query(value = "SELECT sum(a.productTotal) from OrderDetail a where a.id = :id")
    Integer getTtlPrice(@Param("id") Integer id);
}
