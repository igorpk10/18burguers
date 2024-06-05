package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface DeleteProductByIdUseCase {

    void delete(Long id) throws BusinessException;
}
