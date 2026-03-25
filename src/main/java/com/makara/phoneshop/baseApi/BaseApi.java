package com.makara.phoneshop.baseApi;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BaseApi<T> (
        Boolean status,
        Integer code,
        String message,
        @JsonFormat(pattern = "yyyyMMdd HH:mm")
        LocalDateTime timestamp,
        T data
){
}
