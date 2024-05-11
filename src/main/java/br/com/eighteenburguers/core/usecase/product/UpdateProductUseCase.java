package br.com.eighteenburguers.core.usecase.product;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.UpdateProductInputPort;
import br.com.eighteenburguers.core.ports.outbound.FindProductByIdOutputPort;
import br.com.eighteenburguers.core.ports.outbound.UpdateProductOutputPort;
import br.com.eighteenburguers.core.usecase.product.exceptions.ProductNotExistsException;

import java.util.Optional;

public class UpdateProductUseCase implements UpdateProductInputPort {

    private final FindProductByIdOutputPort findProductByIdOutputPort;

    private final UpdateProductOutputPort updateProductOutputPort;

    public UpdateProductUseCase(FindProductByIdOutputPort findProductByIdOutputPort, UpdateProductOutputPort updateProductOutputPort) {
        this.findProductByIdOutputPort = findProductByIdOutputPort;
        this.updateProductOutputPort = updateProductOutputPort;
    }

    @Override
    public Product update(Product product) throws BusinessException {
        Optional<Product> optional = findProductByIdOutputPort.find(product.getId());

        if (optional.isPresent()) {
            throw new ProductNotExistsException();
        }

        return updateProductOutputPort.update(product);
    }
}
