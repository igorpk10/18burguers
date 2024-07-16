package br.com.eighteenburguers.customers.exceptions;

import br.com.eighteenburguers.product.exceptions.BusinessException;

public class CustomerAlreadyExistsException extends BusinessException{
    
    public CustomerAlreadyExistsException() {
        super("CST001", "Customer Already Exists");
    }
}
