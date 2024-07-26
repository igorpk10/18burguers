package br.com.eighteenburguers.order.exceptions;

import br.com.eighteenburguers.product.exceptions.BusinessException;

public class InvalidOrderStatusException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public InvalidOrderStatusException() {
		super("INORST", "Invalid Order Status");
	}

}
