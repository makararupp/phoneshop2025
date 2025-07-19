package com.makara.phoneshop.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_local_name")
    private String customerLocalName;
    @Column(name = "customer_english_name")
    private String customerEngName;
    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name = "customer_phone_number")
    private String customerPhone;
    @Column(name = "customer_address")
    private String customerAddress;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isDeleted =false;
}
