package org.ait.project.onboardingtest.modules.user.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_d", schema = "public", catalog = "postgres")
@Setter
@Getter
public class OrderDetail {
    @Id
    @JsonProperty("row_detail_id")
    private int rowDetailId;

    @JsonProperty("id")
    private int id;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_price")
    private int productPrice;

    @JsonProperty("product_qty")
    private int productQty;

    @JsonProperty("product_total")
    private int productTotal;
}
