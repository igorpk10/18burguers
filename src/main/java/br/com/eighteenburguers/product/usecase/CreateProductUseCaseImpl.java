package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.services.CreateProductService;

public class CreateProductUseCaseImpl implements br.com.eighteenburguers.product.usecase.CreateProductUseCase {

    private final CreateProductService createProductService;

    public CreateProductUseCaseImpl(
        CreateProductService createProductService) {
            
        this.createProductService = createProductService;
    }

    @Override
    public Product insert(Product product) throws BusinessException {
        // Product createdProduct = findProductByIdOutputPort.find(product.getId());

        // if (Objects.nonNull(createdProduct)) {
        //     throw new ProductAlreadyExistsException();
        // }

        return createProductService.insert(product);
    }
}
