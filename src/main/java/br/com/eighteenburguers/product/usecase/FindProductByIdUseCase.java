package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface FindProductByIdUseCase {

    Product find(Long id) throws BusinessException;
}
