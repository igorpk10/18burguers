package br.com.eighteenburguers.order.usecases;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderStatus;
import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.services.PaymentService;
import br.com.eighteenburguers.order.services.SaveOrderService;
import br.com.eighteenburguers.order.valueobjects.Payment;
import br.com.eighteenburguers.order.valueobjects.PaymentStatus;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.exceptions.OrderNotFoundException;

public class PaymentUpdateUseCaseImpl implements PaymentUpdateUseCase {

    private final SaveOrderService saveOrderService;
    private final FindOrderService findOrderService;
    private final PaymentService paymentService;
    
	public PaymentUpdateUseCaseImpl(SaveOrderService saveOrderService, FindOrderService findOrderService,
			PaymentService paymentService) {
		this.saveOrderService = saveOrderService;
		this.findOrderService = findOrderService;
		this.paymentService = paymentService;
	}
	
	@Override
	public void execute(Long orderId) throws BusinessException {
		Order order = findOrderService.findById(orderId).orElseThrow(OrderNotFoundException::new);
		Payment payment = paymentService.check(orderId);
		OrderStatus orderStatus = getStatusFromPayment(payment.getStatus());
		order.setStatus(orderStatus);
		saveOrderService.save(order);
	}

	private OrderStatus getStatusFromPayment(PaymentStatus status) {
		switch (status) {
		case AUTHORIZED:
			return OrderStatus.AWAITING_PAYMENT;
		case CANCELLED:
			return OrderStatus.CANCELED;
		case CAPTURED:
			return OrderStatus.AWAITING_PAYMENT;
		case COMPLETED:
			return OrderStatus.PAID;
		case FAILED:
			return OrderStatus.CANCELED;
		case PENDING:
			return OrderStatus.AWAITING_PAYMENT;
		case PROCESSING:
			return OrderStatus.AWAITING_PAYMENT;
		case REFUNDED:
			return OrderStatus.CANCELED;
		case VOIDED:
			return OrderStatus.CANCELED;
		default:
			return OrderStatus.AWAITING_PAYMENT;
		}
	}
}