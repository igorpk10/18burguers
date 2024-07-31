package br.com.eighteenburguers.product.exceptions;

public class BusinessException extends Exception {

    private String code;
    private String message;
    
    protected BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    
}
