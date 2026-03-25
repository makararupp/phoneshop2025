package com.makara.phoneshop.service.impl;

import com.makara.phoneshop.models.entities.ExchangeRate;
import com.makara.phoneshop.models.mapper.ExchangeRateMapper;
import com.makara.phoneshop.repository.ExchangeRateRepository;
import com.makara.phoneshop.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateMapper exchangeRateMapper;
    @Override
    public ExchangeRate registerRate(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }
}
