package com.np.client.notification;

public record NotificationRequest(Integer toCustomerId,
                                  String toCustomerName,
                                  String message) {
}
