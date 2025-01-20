package com.example.fxdealswarehouse.controller;

import com.example.fxdealswarehouse.exception.ValidationException;
import com.example.fxdealswarehouse.model.dto.DealRequest;
import com.example.fxdealswarehouse.service.DealService;
import com.example.fxdealswarehouse.util.Response;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DealController {

    private final DealService dealService;

    @PostMapping("/deal")
    public ResponseEntity<?> createDeal(@RequestBody @Valid DealRequest dealRequest) throws ValidationException {
        Response response = new Response<>();
        response.setMessage("Deal created successfully");
        response.setResult(dealService.saveDeal(dealRequest));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/deals")
    public ResponseEntity<?> getDeals() throws ValidationException {
        Response response = new Response<>();
        response.setMessage("Deals retrieved successfully");
        response.setResult(dealService.getDeals());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/deal/{id}")
    public ResponseEntity<?> getDeal(@PathVariable Long id) throws ValidationException {
        Response response = new Response<>();
        response.setMessage("Deal retrieved successfully");
        response.setResult(dealService.getDeal(id));
        return ResponseEntity.ok(response);
    }
}
