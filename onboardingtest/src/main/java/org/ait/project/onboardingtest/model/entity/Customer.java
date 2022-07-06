package org.ait.project.onboardingtest.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer", schema = "public", catalog = "postgres")
@Setter
@Getter
public class Customer {
    @Id
    @JsonProperty("id")
    private int id;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private String address;

}
