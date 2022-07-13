package org.ait.project.onboardingtest.modules.user.model.repository;

import org.ait.project.onboardingtest.modules.user.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
//    @Query(value = "SELECT a FROM Product a WHERE a.name = :name")
//    Product findProductByName(@org.springframework.data.repository.query.Param("name") String name);


    Product findByName(String name);
}
