package br.com.eighteenburguers.order.valueobjects;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Payment {

	private Long orderId;
	private PaymentStatus status;
	private Instant timestamp;
}
