package br.com.eighteenburguers.order.services;

import br.com.eighteenburguers.order.valueobjects.Payment;

public interface PaymentService {

	Payment check(Long orderId);
}
