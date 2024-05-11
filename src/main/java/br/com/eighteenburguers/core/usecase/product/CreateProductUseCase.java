package br.com.eighteenburguers.core.usecase.product;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.CreateProductInputPort;
import br.com.eighteenburguers.core.ports.outbound.CreateProductOutputPort;
import br.com.eighteenburguers.core.ports.outbound.FindProductByIdOutputPort;

import java.util.Optional;

public class CreateProductUseCase implements CreateProductInputPort {

    private final FindProductByIdOutputPort findProductByIdOutputPort;

    private final CreateProductOutputPort createProductOutputPort;

    public CreateProductUseCase(FindProductByIdOutputPort findProductByIdOutputPort, CreateProductOutputPort createProductOutputPort) {
        this.findProductByIdOutputPort = findProductByIdOutputPort;
        this.createProductOutputPort = createProductOutputPort;
    }

    @Override
    public Product insert(Product product) throws BusinessException {
        Optional<Product> optional = findProductByIdOutputPort.find(product.getId());

        if (optional.isPresent()) {
            throw new ProductAlreadyExistsException();
        }

        return createProductOutputPort.insert(product);
    }
}
