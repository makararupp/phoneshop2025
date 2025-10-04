package com.makara.phoneshop.models.mapper;

import com.makara.phoneshop.models.dto.BrandDTO;
import com.makara.phoneshop.models.entities.Brand;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toEntity(BrandDTO brandDTO);
    BrandDTO toBrandDto(Brand brand);

}
