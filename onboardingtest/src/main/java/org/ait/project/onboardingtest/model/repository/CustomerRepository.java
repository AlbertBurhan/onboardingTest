package org.ait.project.onboardingtest.model.repository;

import org.ait.project.onboardingtest.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(value = "select a from Customer a where a.customerName = :name and a.email =:email")
    Customer getCustomerByCustNameAndEmail(@Param("name") String name, @Param("email") String email);
}
