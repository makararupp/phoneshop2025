package com.makara.phoneshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ModelDTO {
    
    @Min(value = 1, message = "model is must be positive")
    private Long id;

    @NotNull(message = "name is required")
    @Size(max = 50, message = "name must be less than 50 characters")
    private String name;

    @NotNull(message = "brand ID is required")
    @Min(value = 1, message = "Brand ID must be positive")
    private Integer brandId;
    
}
