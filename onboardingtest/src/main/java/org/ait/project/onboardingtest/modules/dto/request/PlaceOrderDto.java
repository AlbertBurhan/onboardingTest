package org.ait.project.onboardingtest.modules.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PlaceOrderDto {
    private String customerName;
    private String phoneNo;
    private String email;
    private String address;
    private List<ItemOrderDto> items;
}
