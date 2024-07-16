package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface UpdateProductUseCase {

    void update(Product product) throws BusinessException;
}
