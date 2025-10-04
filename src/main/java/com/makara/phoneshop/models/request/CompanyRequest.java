package com.makara.phoneshop.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CompanyRequest {
    @NotBlank(message = "company local name is required!")
    @Length(min = 4, max = 100, message = "company local name between 4 to 100 characters")
    private String companyLocalName;

    @NotBlank(message = "company english name is required!")
    @Length(min = 4, max = 100, message = "company english name is between 4 to 100 characters")
    private String companyEngName;

    @NotBlank(message = "company email is required!")
    @Length(min = 6, max = 50, message = "company email is between 6 to 50 characters")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email address")
    private String companyEmail;

    @NotBlank(message = "phone number is required!")
    @Length(min = 9, max = 10, message = "phone number is between 9 to 10 characters")
    private String companyPhone;

    @NotBlank(message = "company address is required!")
    @Length(min = 8, max = 100, message = "company address is between 8 to 100 characters")
    private String companyAddress;

    @NotBlank(message = "the vat number is required!")
    @Length(min = 6, max = 12, message = "the vat number is between 6 to 12 characters")
    @Pattern(regexp = "A\\d{6}$",message = "vat number must start with 'A' followed by digits")
    private String vatNumber;

    private String imagePath;
    private String image;

}
