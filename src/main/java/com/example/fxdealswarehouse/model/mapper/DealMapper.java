package com.example.fxdealswarehouse.model.mapper;

import com.example.fxdealswarehouse.model.dto.DealRequest;
import com.example.fxdealswarehouse.model.entity.Deal;

import java.util.ArrayList;
import java.util.List;

public class DealMapper {

    public static Deal mapDealRequestToDeal(DealRequest dealRequest) {
        Deal deal = new Deal();
        deal.setFromCurrency(dealRequest.getFromCurrency());
        deal.setToCurrency(dealRequest.getToCurrency());
        deal.setDealTimestamp(dealRequest.getDealTimestamp());
        deal.setDealAmount(dealRequest.getDealAmount());
        return deal;
    }

    public static DealRequest mapDealToDealRequest(Deal deal) {
        DealRequest dealRequest = new DealRequest();
        dealRequest.setFromCurrency(deal.getFromCurrency());
        dealRequest.setToCurrency(deal.getToCurrency());
        dealRequest.setDealTimestamp(deal.getDealTimestamp());
        dealRequest.setDealAmount(deal.getDealAmount());
        return dealRequest;
    }

    public static List<DealRequest> mapDealsToDealRequests(List<Deal> deals) {
        List<DealRequest> dealRequests = new ArrayList<>();
        for (Deal deal : deals) {
            dealRequests.add(mapDealToDealRequest(deal));
        }
        return dealRequests;
    }
}
