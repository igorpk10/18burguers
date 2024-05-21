package br.com.eighteenburguers.core.usecase.product;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.product.CreateProductInputPort;
import br.com.eighteenburguers.core.ports.outbound.product.CreateProductOutputPort;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductByIdOutputPort;
import br.com.eighteenburguers.core.usecase.product.exceptions.ProductAlreadyExistsException;

import java.util.Objects;

public class CreateProductUseCase implements CreateProductInputPort {

    private final FindProductByIdOutputPort findProductByIdOutputPort;

    private final CreateProductOutputPort createProductOutputPort;

    public CreateProductUseCase(
        FindProductByIdOutputPort findProductByIdOutputPort, 
        CreateProductOutputPort createProductOutputPort) {
            
        this.findProductByIdOutputPort = findProductByIdOutputPort;
        this.createProductOutputPort = createProductOutputPort;
    }

    @Override
    public Product insert(Product product) throws BusinessException {
        // Product createdProduct = findProductByIdOutputPort.find(product.getId());

        // if (Objects.nonNull(createdProduct)) {
        //     throw new ProductAlreadyExistsException();
        // }

        return createProductOutputPort.insert(product);
    }
}
