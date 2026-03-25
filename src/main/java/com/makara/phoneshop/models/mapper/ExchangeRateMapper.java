package com.makara.phoneshop.models.mapper;

import com.makara.phoneshop.models.entities.ExchangeRate;
import com.makara.phoneshop.models.request.ExchangeRateRequest;
import com.makara.phoneshop.models.response.ExchangeRateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {
    ExchangeRate toEntities(ExchangeRateRequest request);

    ExchangeRateResponse toDTO(ExchangeRate exchangeRate);
}
