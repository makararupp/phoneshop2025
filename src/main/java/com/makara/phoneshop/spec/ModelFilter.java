package com.makara.phoneshop.spec;
import lombok.Data;

@Data
public class ModelFilter {
    private Integer id;
    private String name;
    private Long brandId;
    private String brandName;
    private Boolean isDeleted;
    private BrandFilter brandFilter;

}
