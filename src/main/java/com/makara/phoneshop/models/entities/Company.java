package com.makara.phoneshop.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "companies")
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(name = "company_local_name")
    private String companyLocalName;

    @Column(name = "company_english_name")
    private String companyEngName;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "company_phone")
    private String companyPhone;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "company_vat_number")
    private String vatNumber;

    @Column(name = "company_image_path")
    private String imagePath;

    @Column(name = "company_image")
    private String image;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted = false;

}
