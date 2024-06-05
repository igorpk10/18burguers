package br.com.eighteenburguers.product.exceptions;

import br.com.eighteenburguers.product.exceptions.BusinessException;

public class ProductNotExistsException extends BusinessException {

    public ProductNotExistsException() { super("PRD002", "Produto não cadastrado!"); }
}
