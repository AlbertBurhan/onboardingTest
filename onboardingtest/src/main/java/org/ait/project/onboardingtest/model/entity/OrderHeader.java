package org.ait.project.onboardingtest.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_h", schema = "public", catalog = "postgres")
@Setter
@Getter
public class OrderHeader {
    @Id
    @JsonProperty("id")
    private int id;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("customer_phone")
    private String customerPhone;

    @JsonProperty("customer_email")
    private String customerEmail;

    @JsonProperty("customer_address")
    private String customerAddress;

    @JsonProperty("shipping_id")
    private String shippingId;

    @JsonProperty("order_status")
    private int orderStatus;
}
