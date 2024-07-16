package br.com.eighteenburguers.product.exceptions;

public class OrderNotFoundException extends BusinessException {

    public OrderNotFoundException() {
        super("ONF001", "Order not found");
    }
    
}
