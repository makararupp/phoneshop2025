package com.makara.phoneshop.models.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExchangeRateRequest {
    @NotNull(message = "From Current Rate must be not Null.")
    private String fromCurrency;
    @NotNull(message = "To Current Rate Must Be Not Null.")
    private String toCurrency;
    @NotNull(message = "Rate Must Be Not Null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Rate Must Be Must Greater Than 0.")
    private Double exchange;
}
