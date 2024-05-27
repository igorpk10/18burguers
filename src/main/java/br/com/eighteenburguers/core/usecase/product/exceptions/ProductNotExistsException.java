package br.com.eighteenburguers.core.usecase.product.exceptions;

import br.com.eighteenburguers.core.exceptions.BusinessException;

public class ProductNotExistsException extends BusinessException {

    public ProductNotExistsException() { super("PRD002", "Produto não cadastrado!"); }
}
