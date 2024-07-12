package br.com.eighteenburguers.order.exceptions;

public class OrderInvalidFieldException extends RuntimeException {

    private String fieldError;

    public OrderInvalidFieldException (String fieldError){
        this.fieldError = fieldError;
    }

    @Override
    public String getMessage() {
        return new StringBuilder().append("The field ").append(fieldError).append("must not be empty or null").toString();
    }
}
