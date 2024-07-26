package br.com.eighteenburguers.order.services;

import java.time.Instant;

import org.springframework.stereotype.Service;

import br.com.eighteenburguers.order.valueobjects.Payment;
import br.com.eighteenburguers.order.valueobjects.PaymentStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public Payment check(Long orderId) {
		log.info("Fake payment api, processing order: {}", orderId);
		return new Payment(orderId, PaymentStatus.COMPLETED, Instant.now());
	}
}
