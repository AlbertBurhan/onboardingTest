package org.ait.project.onboardingtest.modules.user.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product", schema = "public", catalog = "postgres")
@Setter
@Getter
public class Product {
    @Id
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("qty")
    private int qty;

    @JsonProperty("price")
    private int price;
}
