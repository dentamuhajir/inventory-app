package com.dentamuhajir.inventory.dto;

import lombok.Data;

@Data
public class StockUpdateRequestDTO {
    private String itemName;
    private Integer stockQuantity;
    private String serialNumber;
    private String additionalInfo;
    private String image;
}
