package br.com.eighteenburguers.order.usecases;

import static br.com.eighteenburguers.order.entitys.OrderStatus.AWAITING_PAYMENT;
import static br.com.eighteenburguers.order.entitys.OrderStatus.AWAITING_WITHDRAWAL;
import static br.com.eighteenburguers.order.entitys.OrderStatus.CREATED;
import static br.com.eighteenburguers.order.entitys.OrderStatus.IN_PREPARATION;
import static br.com.eighteenburguers.order.entitys.OrderStatus.PAID;
import static br.com.eighteenburguers.order.entitys.OrderStatus.READY;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderStatus;
import br.com.eighteenburguers.order.exceptions.InvalidOrderStatusException;
import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.services.SaveOrderService;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.exceptions.OrderNotFoundException;

public class UpdateOrderStatusImpl implements UpdateOrderStatus {

	private final FindOrderService findOrderService;
	private final SaveOrderService saveOrderService;
	
	public UpdateOrderStatusImpl(FindOrderService findOrderService, SaveOrderService saveOrderService) {
		this.findOrderService = findOrderService;
		this.saveOrderService = saveOrderService;
	}
	
	@Override
	public Order execute(Long orderId, OrderStatus status) throws BusinessException {
		Order order = findOrderService.findById(orderId).orElseThrow(OrderNotFoundException::new);
		
		boolean isValid = validate(order.getStatus(), status);
		
		if(!isValid) {
			throw new InvalidOrderStatusException();
		}
		
		order.setStatus(status);
		saveOrderService.save(order);
		return order;
	}

	private boolean validate(OrderStatus current, OrderStatus next) {
		
		switch (next) {
		case IN_PREPARATION:
			return current.equals(PAID);
		case PAID:
			return current.equals(AWAITING_PAYMENT);
		case READY:
			return current.equals(IN_PREPARATION);
		case AWAITING_WITHDRAWAL:
			return current.equals(READY);
		case COMPLETED:
			return current.equals(AWAITING_WITHDRAWAL);
		case AWAITING_PAYMENT:
			return current.equals(CREATED);
		default:
			return false;
		}
		
	}
	
}
