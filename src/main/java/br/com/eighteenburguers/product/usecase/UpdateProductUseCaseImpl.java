package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.services.FindProductByIdService;
import br.com.eighteenburguers.product.services.UpdateProductService;
import br.com.eighteenburguers.product.exceptions.ProductNotExistsException;

public class UpdateProductUseCaseImpl implements br.com.eighteenburguers.product.usecase.UpdateProductUseCase {

    private final FindProductByIdService findProductByIdService;

    private final UpdateProductService updateProductService;

    public UpdateProductUseCaseImpl(FindProductByIdService findProductByIdService, UpdateProductService updateProductService) {
        this.findProductByIdService = findProductByIdService;
        this.updateProductService = updateProductService;
    }

    @Override
    public void update(Product product) throws BusinessException {
        Product createdProduct = findProductByIdService.find(product.getId()).orElseThrow(ProductNotExistsException::new);
        updateProductService.update(product);
    }
}
