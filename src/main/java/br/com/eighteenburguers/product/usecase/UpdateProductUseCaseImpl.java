package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.services.FindProductByIdService;
import br.com.eighteenburguers.product.services.UpdateProductService;
import br.com.eighteenburguers.product.exceptions.ProductNotExistsException;

public class UpdateProductUseCaseImpl implements br.com.eighteenburguers.product.usecase.UpdateProductUseCase {

    private final FindProductByIdService findProductByIdOutputPort;

    private final UpdateProductService updateProductOutputPort;

    public UpdateProductUseCaseImpl(FindProductByIdService findProductByIdOutputPort, UpdateProductService updateProductOutputPort) {
        this.findProductByIdOutputPort = findProductByIdOutputPort;
        this.updateProductOutputPort = updateProductOutputPort;
    }

    @Override
    public void update(Product product) throws BusinessException {
        Product createdProduct = findProductByIdOutputPort.find(product.getId()).orElseThrow(ProductNotExistsException::new);

        updateProductOutputPort.update(product);
    }
}
