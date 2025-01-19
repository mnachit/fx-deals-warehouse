package com.example.fxdealswarehouse.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealRequest {
    @NotBlank(message = "fromCurrency is mandatory")
    private String fromCurrency;
    @NotBlank(message = "toCurrency is mandatory")
    private String toCurrency;
    @NotNull(message = "dealTimestamp is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dealTimestamp;
    @NotNull(message = "dealAmount is mandatory")
    @Positive(message = "dealAmount must be greater than 0")
    private BigDecimal dealAmount;
}
