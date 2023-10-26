package com.np.fraud.controller;

import com.np.client.fraud.FraudCheckResponse;
import com.np.fraud.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/fraud-check")
public record FraudCheckController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "/{customerId}")
    public FraudCheckResponse checkIsFraudster(@PathVariable("customerId") Integer customerId) {
        log.info("Fraudulent customer check: {}", customerId);
        return fraudCheckService.isFraudulentCustomer(customerId);
    }

}
