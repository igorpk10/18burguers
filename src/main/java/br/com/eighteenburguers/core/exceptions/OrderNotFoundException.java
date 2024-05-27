package br.com.eighteenburguers.core.exceptions;

public class OrderNotFoundException extends BusinessException {

    public OrderNotFoundException() {
        super("ONF001", "Order not found");
    }
    
}
