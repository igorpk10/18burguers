package br.com.eighteenburguers.product.exceptions;

import br.com.eighteenburguers.product.exceptions.BusinessException;

public class ProductAlreadyExistsException extends BusinessException {

    public ProductAlreadyExistsException() {
        super("PRD001", "Produto já cadastrado!");
    }
}
