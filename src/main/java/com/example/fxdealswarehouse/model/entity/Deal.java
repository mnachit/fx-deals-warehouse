package com.example.fxdealswarehouse.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Deal")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private LocalDateTime dealTimestamp;
    private BigDecimal dealAmount;
}
