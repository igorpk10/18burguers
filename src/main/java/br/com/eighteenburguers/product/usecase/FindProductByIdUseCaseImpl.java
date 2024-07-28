package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.services.FindProductByIdService;
import br.com.eighteenburguers.product.exceptions.ProductNotExistsException;

public class FindProductByIdUseCaseImpl implements br.com.eighteenburguers.product.usecase.FindProductByIdUseCase {

    private final FindProductByIdService findProductByIdService;

    public FindProductByIdUseCaseImpl(FindProductByIdService findProductByIdService) {
        this.findProductByIdService = findProductByIdService;
    }

    @Override
    public Product find(Long id) throws BusinessException {
        return findProductByIdService.find(id).orElseThrow(ProductNotExistsException::new);
    }
}
