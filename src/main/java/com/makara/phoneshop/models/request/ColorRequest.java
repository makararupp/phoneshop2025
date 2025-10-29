package com.makara.phoneshop.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ColorRequest {
    @NotBlank(message = "color name is required!")
    @Length(min = 3, max = 10,message = "color name between 3 to 10 characters")
    private String name;
}
