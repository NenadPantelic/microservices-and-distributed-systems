package com.np.fraud.service;

import com.np.fraud.dto.FraudCheckResponse;
import com.np.fraud.model.FraudCheckHistory;
import com.np.fraud.repository.FraudCheckHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public record FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {


    public FraudCheckResponse isFraudulentCustomer(Integer customerId) {
        // dummy implementation
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .fraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return new FraudCheckResponse(false);
    }
}
