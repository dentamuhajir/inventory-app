package com.dentamuhajir.inventory.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
public class StockUpdateRequestDTO {
    @NotNull(message = "Item Name shouldn't blank")
    private String itemName;
    private Integer stockQuantity;
    private String serialNumber;
    private String additionalInfo;
    private String image;
}
