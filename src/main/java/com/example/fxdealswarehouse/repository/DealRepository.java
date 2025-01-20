package com.example.fxdealswarehouse.repository;

import com.example.fxdealswarehouse.model.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    Boolean existsByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}
