package com.makara.phoneshop.baseApi;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BaseApi<T> {
        private Boolean status;
        private Integer code;
        private String message;

        @JsonFormat(pattern = "yyyyMMdd HH:mm")
        private LocalDateTime timestamp;

        private T data;
}
