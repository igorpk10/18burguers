package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface CreateProductUseCase {

    Product insert(Product product) throws BusinessException;
}
