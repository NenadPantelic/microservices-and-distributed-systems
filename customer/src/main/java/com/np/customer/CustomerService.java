package com.np.customer;

import com.np.client.fraud.FraudCheckResponse;
import com.np.client.fraud.FraudClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, FraudClient fraudClient) {
        this.customerRepository = customerRepository;
        this.fraudClient = fraudClient;
    }

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        // validate input params
        // store customer in db and handle exceptions (unique email e.g.)
        final Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster detected");
        }


    }
}
