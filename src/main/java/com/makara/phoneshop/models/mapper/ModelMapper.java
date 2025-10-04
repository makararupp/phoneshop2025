package com.makara.phoneshop.models.mapper;

import com.makara.phoneshop.models.dto.ModelDTO;
import com.makara.phoneshop.models.entities.Model;
import com.makara.phoneshop.service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {BrandService.class})
public interface ModelMapper {
    @Mapping(target = "brand",source = "brandId")
    Model toModel(ModelDTO dto);
    @Mapping(target = "brandId",source = "brand.id")
    ModelDTO toModelDto(Model model);
}
