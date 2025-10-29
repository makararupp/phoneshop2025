package com.makara.phoneshop.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Long id;
    @Column(name = "color_name")
    private String name;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted = false;

}
