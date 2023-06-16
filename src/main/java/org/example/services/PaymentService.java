package org.example.services;

import org.example.entity.Order;
import org.example.entity.PayuResponse;

public interface PaymentService {
    PayuResponse sendRequestPayU(Order order);

    String getOrderStatusFromPayu(String orderIdPayu);
}
