package com.makara.phoneshop.models.mapper;

import com.makara.phoneshop.models.entities.Color;
import com.makara.phoneshop.models.request.ColorRequest;
import com.makara.phoneshop.models.response.ColorResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    Color toEntity(ColorRequest request);
    ColorResponse toDto(Color color);
}
