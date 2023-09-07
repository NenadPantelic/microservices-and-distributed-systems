package com.np.notification.fraud.service;

import com.np.client.fraud.FraudCheckResponse;
import com.np.notification.fraud.model.FraudCheckHistory;
import com.np.notification.fraud.repository.FraudCheckHistoryRepository;
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
