package org.ait.project.onboardingtest.modules.user.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment", schema = "public", catalog = "postgres")
@Setter
@Getter
public class Payment {
    @Id
    @JsonProperty("payment_id")
    private int paymentId;

    @JsonProperty("order_id")
    private int orderId;

    @JsonProperty("payment_value")
    private int paymentValue;
}
