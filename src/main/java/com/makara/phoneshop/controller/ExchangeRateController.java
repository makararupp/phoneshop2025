package com.makara.phoneshop.controller;

import com.makara.phoneshop.baseApi.BaseApi;
import com.makara.phoneshop.models.entities.ExchangeRate;
import com.makara.phoneshop.models.mapper.ExchangeRateMapper;
import com.makara.phoneshop.models.request.ExchangeRateRequest;
import com.makara.phoneshop.models.response.ExchangeRateResponse;
import com.makara.phoneshop.service.ExchangeRateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("exchangeRates")
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;
    private final ExchangeRateMapper rateMapper;

    @PostMapping
    public BaseApi<?> registerRate(@Valid @RequestBody ExchangeRateRequest request) {
        ExchangeRate exchangeRate = rateMapper.toEntities(request);
        ExchangeRate saveData = exchangeRateService.registerRate(exchangeRate);
        rateMapper.toDTO(saveData);
        log.info("{} : saved", saveData);
        return  BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Rate have been saved.")
                .timestamp(LocalDateTime.now())
                .data(saveData)
                .build();
    }
}
