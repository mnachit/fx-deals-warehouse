package com.example.fxdealswarehouse;

import com.example.fxdealswarehouse.exception.ValidationException;
import com.example.fxdealswarehouse.model.dto.DealRequest;
import com.example.fxdealswarehouse.model.entity.Deal;
import com.example.fxdealswarehouse.repository.DealRepository;
import com.example.fxdealswarehouse.service.impl.DealServiceImpl;
import com.example.fxdealswarehouse.util.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DealServiceImplTest {

    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealServiceImpl dealService;

    @Test
    void saveDeal_Success() throws ValidationException {
        DealRequest request = new DealRequest();
        request.setFromCurrency("USD");
        request.setToCurrency("EUR");
        request.setDealTimestamp(LocalDateTime.now());
        request.setDealAmount(new BigDecimal("100.00"));

        Deal deal = new Deal();
        deal.setFromCurrency(request.getFromCurrency());
        deal.setToCurrency(request.getToCurrency());
        deal.setDealTimestamp(request.getDealTimestamp());
        deal.setDealAmount(request.getDealAmount());

        when(dealRepository.existsByFromCurrencyAndToCurrency(anyString(), anyString())).thenReturn(false);
        when(dealRepository.save(any(Deal.class))).thenReturn(deal);

        DealRequest result = dealService.saveDeal(request);

        assertNotNull(result);
        assertEquals(request.getFromCurrency(), result.getFromCurrency());
        assertEquals(request.getToCurrency(), result.getToCurrency());
        assertEquals(request.getDealTimestamp(), result.getDealTimestamp());
        assertEquals(request.getDealAmount(), result.getDealAmount());

        verify(dealRepository).existsByFromCurrencyAndToCurrency(request.getFromCurrency(), request.getToCurrency());
        verify(dealRepository).save(any(Deal.class));
    }

    @Test
    void saveDeal_ThrowsValidationException_WhenDealExists() {
        DealRequest request = new DealRequest();
        request.setFromCurrency("USD");
        request.setToCurrency("EUR");
        request.setDealTimestamp(LocalDateTime.now());
        request.setDealAmount(new BigDecimal("100.00"));

        when(dealRepository.existsByFromCurrencyAndToCurrency(anyString(), anyString())).thenReturn(true);

        ValidationException exception = assertThrows(ValidationException.class,
                () -> dealService.saveDeal(request));

        List<ErrorMessage> errorMessages = exception.getErrorMessages();
        assertNotNull(errorMessages);
        assertFalse(errorMessages.isEmpty());
        assertEquals("Deal already exists", errorMessages.get(0).getMessage());

        verify(dealRepository).existsByFromCurrencyAndToCurrency(request.getFromCurrency(), request.getToCurrency());
        verify(dealRepository, never()).save(any(Deal.class));
    }

    @Test
    void saveDeal_WithNullValues_ValidationFails() {
        DealRequest request = new DealRequest();

        ValidationException exception = assertThrows(ValidationException.class,
                () -> dealService.saveDeal(request));

        assertNotNull(exception.getErrorMessages());
        assertTrue(exception.getErrorMessages().size() > 0);
    }
}