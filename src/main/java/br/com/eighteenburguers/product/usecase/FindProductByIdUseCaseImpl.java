package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.services.FindProductByIdService;
import br.com.eighteenburguers.product.exceptions.ProductNotExistsException;

public class FindProductByIdUseCaseImpl implements br.com.eighteenburguers.product.usecase.FindProductByIdUseCase {

    private final FindProductByIdService findProductByIdOutputPort;

    public FindProductByIdUseCaseImpl(FindProductByIdService findProductByIdOutputPort) {
        this.findProductByIdOutputPort = findProductByIdOutputPort;
    }

    @Override
    public Product find(Long id) throws BusinessException {
        return findProductByIdOutputPort.find(id).orElseThrow(ProductNotExistsException::new);
    }
}
