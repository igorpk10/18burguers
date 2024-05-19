package br.com.eighteenburguers.core.usecase.product;

import java.util.Optional;
import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.product.CreateProductInputPort;
import br.com.eighteenburguers.core.ports.outbound.product.CreateProductOutputPort;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductByIdOutputPort;
import br.com.eighteenburguers.core.usecase.product.exceptions.ProductAlreadyExistsException;

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
        Optional<Product> optional = findProductByIdOutputPort.find(product.getId());

        if (optional.isPresent()) {
            throw new ProductAlreadyExistsException();
        }

        return createProductOutputPort.insert(product);
    }
}
