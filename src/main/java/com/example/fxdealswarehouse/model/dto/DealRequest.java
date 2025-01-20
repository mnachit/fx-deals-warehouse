package com.example.fxdealswarehouse.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Validated
public class DealRequest {
    @NotBlank(message = "fromCurrency is mandatory")
    private String fromCurrency;

    @NotBlank(message = "toCurrency is mandatory")
    private String toCurrency;

    @NotNull(message = "dealTimestamp is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dealTimestamp;

    @NotNull(message = "dealAmount is mandatory")
    @Digits(integer = 10, fraction = 2, message = "dealAmount must be a number")
    @Positive(message = "dealAmount must be greater than 0")
    private BigDecimal dealAmount;
}
