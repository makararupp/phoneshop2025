package com.makara.phoneshop.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_name")
    @NotBlank(message = "model name is required")
    @Size(min = 2, max = 40,message = "model name must be between 2 and 40 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;

    @Column(name = "is_deleted",
            columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isDeleted = false;

}
