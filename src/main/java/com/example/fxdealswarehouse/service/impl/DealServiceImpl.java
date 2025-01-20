package com.example.fxdealswarehouse.service.impl;

import com.example.fxdealswarehouse.exception.ValidationException;
import com.example.fxdealswarehouse.model.dto.DealRequest;
import com.example.fxdealswarehouse.model.entity.Deal;
import com.example.fxdealswarehouse.model.mapper.DealMapper;
import com.example.fxdealswarehouse.repository.DealRepository;
import com.example.fxdealswarehouse.service.DealService;
import com.example.fxdealswarehouse.util.ErrorMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DealServiceImpl implements DealService {
    private static final Logger logger = LoggerFactory.getLogger(DealServiceImpl.class);

    private final DealRepository dealRepository;
    @Override
    public DealRequest saveDeal(DealRequest dealRequest) throws ValidationException {
        logger.info("Attempting to save a deal: {}", dealRequest);
        List<ErrorMessage> errorMessages = new ArrayList<>();
        if (isDuplicateDeal(dealRequest.getFromCurrency(), dealRequest.getToCurrency())) {
            logger.warn("Duplicate deal detected for currencies: {} -> {}", dealRequest.getFromCurrency(), dealRequest.getToCurrency());
            errorMessages.add(ErrorMessage.builder().message("Deal already exists").build());
        }
        if (errorMessages.size() > 0) {
            logger.error("Validation errors occurred while saving the deal: {}", errorMessages);
            throw new ValidationException(errorMessages);
        }

        Deal deal = DealMapper.mapDealRequestToDeal(dealRequest);
        return DealMapper.mapDealToDealRequest(dealRepository.save(deal));
    }

    @Override
    public List<DealRequest> getDeals() {
        List<DealRequest> dealRequests = DealMapper.mapDealsToDealRequests(dealRepository.findAll());
        logger.debug("Fetched {} deals", dealRequests.size());
        return dealRequests;
    }

    @Override
    public DealRequest getDeal(Long id) throws ValidationException {
        Deal deal = dealRepository.findById(id).orElseThrow(() -> new ValidationException(List.of(ErrorMessage.builder().message("Deal not found").build())));
        return DealMapper.mapDealToDealRequest(deal);
    }

    public Boolean isDuplicateDeal(String fromCurrency, String toCurrency)
    {
        return dealRepository.existsByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
    }
}

