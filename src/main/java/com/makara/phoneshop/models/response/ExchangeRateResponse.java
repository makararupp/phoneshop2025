package com.makara.phoneshop.models.response;

import lombok.Data;

@Data
public class ExchangeRateResponse {
    private Long id;
    private String toCurrency;
    private String fromCurrency;
    private Double exchange;
}
