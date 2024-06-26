package br.com.eighteenburguers.core.usecase.product.exceptions;

import br.com.eighteenburguers.core.exceptions.BusinessException;

public class ProductAlreadyExistsException extends BusinessException {

    public ProductAlreadyExistsException() {
        super("PRD001", "Produto já cadastrado!");
    }
}
