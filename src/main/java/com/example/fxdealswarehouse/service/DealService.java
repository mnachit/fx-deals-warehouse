package com.example.fxdealswarehouse.service;

import com.example.fxdealswarehouse.exception.ValidationException;
import com.example.fxdealswarehouse.model.dto.DealRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface DealService {
    public DealRequest saveDeal(DealRequest dealRequest) throws ValidationException;
    public List<DealRequest> getDeals();
    public DealRequest getDeal(Long id);
    public Boolean isDuplicateDeal(String fromCurrency, String toCurrency);
}
