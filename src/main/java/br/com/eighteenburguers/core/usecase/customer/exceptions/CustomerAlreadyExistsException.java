package br.com.eighteenburguers.core.usecase.customer.exceptions;

import br.com.eighteenburguers.core.exceptions.BusinessException;

public class CustomerAlreadyExistsException extends BusinessException{
    
    public CustomerAlreadyExistsException() {
        super("CST001", "Customer Already Exists");
    }
}
