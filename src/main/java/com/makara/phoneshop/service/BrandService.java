package com.makara.phoneshop.service;

import com.makara.phoneshop.models.dto.BrandDTO;
import com.makara.phoneshop.models.entities.Brand;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {
    Brand creat(Brand brand);
    Brand getById(Long Id);
    void deleteId(Long Id);
    Brand update(Long Id, Brand updateBrand);
    List<Brand> getBrands(String name);
    List<BrandDTO> listAll();
    Page<BrandDTO> getWithPagination(Map<String, String> params);
}
