package com.makara.phoneshop.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CustomerRequest {

    @NotBlank(message = "customer local name is required!")
    @Length(min =3, max =100, message = "name is between 3 to 100 characters")
    private String customerLocalName;

    @NotBlank(message = "customer english name is required!")
    @Length(min = 3, max = 100, message = "name is between 2 to 100 characters")
    private String customerEngName;

    @NotBlank(message = "customer email is required!")
    @Length(min = 6, max = 30, message = "email is between 6 to 30 characters")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email address")
    private String customerEmail;

    @NotBlank(message = "phone number is required!")
    @Length(min=8, max = 20, message = "phone number is between 8 to 20 characters")
    @Pattern(regexp = "^((\\\\+855)|0)?[1-9][0-9]{7,8}$",message = "Invalid phone number")
    private String customerPhone;

    @NotBlank(message = "Address is required!")
    @Length(min=10, max = 100, message = "Address is between 10 to 100 characters")
    @Pattern(regexp = "^[A-Za-z0-9#.,\\-\\s/]+$", message = "Invalid address format")
    private String customerAddress;

}
