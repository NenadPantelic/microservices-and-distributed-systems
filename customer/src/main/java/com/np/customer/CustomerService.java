package com.np.customer;

import com.np.amqp.RabbitMQMessageProducer;
import com.np.client.fraud.FraudCheckResponse;
import com.np.client.fraud.FraudClient;
import com.np.client.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           FraudClient fraudClient,
                           RabbitMQMessageProducer rabbitMQMessageProducer) {
        this.customerRepository = customerRepository;
        this.fraudClient = fraudClient;
        this.rabbitMQMessageProducer = rabbitMQMessageProducer;
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

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Nenad's demo app...", customer.getFirstName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );


    }
}
