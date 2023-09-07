package com.np.client.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// this client will be able to resolve the physical address on its own through Eureka service discovery
@FeignClient(value = "fraud", url = "${client.fraud.url}", path = "api/v1/fraud-check")
public interface FraudClient {

    @GetMapping(path = "{customerId}")
    FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
