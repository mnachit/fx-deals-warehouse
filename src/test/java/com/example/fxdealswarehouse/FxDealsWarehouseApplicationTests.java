package com.example.fxdealswarehouse;

import com.example.fxdealswarehouse.exception.ValidationException;
import com.example.fxdealswarehouse.model.dto.DealRequest;
import com.example.fxdealswarehouse.model.entity.Deal;
import com.example.fxdealswarehouse.model.mapper.DealMapper;
import com.example.fxdealswarehouse.repository.DealRepository;
import com.example.fxdealswarehouse.service.impl.DealServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class FxDealsWarehouseApplicationTests {

    @Test
    void contextLoads() {
    }

    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealServiceImpl dealService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveDeal() throws ValidationException {
        DealRequest dealRequest = new DealRequest();
        dealRequest.setFromCurrency("USD");
        dealRequest.setToCurrency("EUR");
        dealRequest.setDealTimestamp(LocalDateTime.now());
        dealRequest.setDealAmount(new BigDecimal("1000.50"));

        Deal deal = DealMapper.mapDealRequestToDeal(dealRequest);

        when(dealRepository.existsByFromCurrencyAndToCurrency(any(), any())).thenReturn(false);
        when(dealRepository.save(any(Deal.class))).thenReturn(deal);

        DealRequest savedDeal = dealService.saveDeal(dealRequest);

        assertNotNull(savedDeal);
        assertEquals(dealRequest.getFromCurrency(), savedDeal.getFromCurrency());
        assertEquals(dealRequest.getToCurrency(), savedDeal.getToCurrency());
        assertEquals(dealRequest.getDealTimestamp(), savedDeal.getDealTimestamp());
        assertEquals(dealRequest.getDealAmount(), savedDeal.getDealAmount());
    }

    @Test
    void testGetDeal() throws ValidationException {
        Long dealId = 1L;
        Deal deal = new Deal();
        deal.setId(dealId);
        deal.setFromCurrency("USD");
        deal.setToCurrency("EUR");
        deal.setDealTimestamp(LocalDateTime.now());
        deal.setDealAmount(new BigDecimal("1000.50"));

        when(dealRepository.findById(dealId)).thenReturn(Optional.of(deal));

        DealRequest dealRequest = dealService.getDeal(dealId);

        assertNotNull(dealRequest);
        assertEquals(deal.getFromCurrency(), dealRequest.getFromCurrency());
        assertEquals(deal.getToCurrency(), dealRequest.getToCurrency());
        assertEquals(deal.getDealTimestamp(), dealRequest.getDealTimestamp());
        assertEquals(deal.getDealAmount(), dealRequest.getDealAmount());
    }

}
