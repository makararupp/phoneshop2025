package com.makara.phoneshop.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BrandDTO {
    private Integer id;
    @NotBlank(message = "name can't be empty")
    @Size(min = 3, max = 50 , message = "name must be between 3 to 50 characters")
    private String name;
}
