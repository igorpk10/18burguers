package br.com.eighteenburguers.core.usecase.product;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.product.UpdateProductInputPort;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductByIdOutputPort;
import br.com.eighteenburguers.core.ports.outbound.product.UpdateProductOutputPort;
import br.com.eighteenburguers.core.usecase.product.exceptions.ProductNotExistsException;

import java.util.Objects;
import java.util.Optional;

public class UpdateProductUseCase implements UpdateProductInputPort {

    private final FindProductByIdOutputPort findProductByIdOutputPort;

    private final UpdateProductOutputPort updateProductOutputPort;

    public UpdateProductUseCase(FindProductByIdOutputPort findProductByIdOutputPort, UpdateProductOutputPort updateProductOutputPort) {
        this.findProductByIdOutputPort = findProductByIdOutputPort;
        this.updateProductOutputPort = updateProductOutputPort;
    }

    @Override
    public void update(Product product) throws BusinessException {
        Product createdProduct = findProductByIdOutputPort.find(product.getId()).orElseThrow(ProductNotExistsException::new);

        updateProductOutputPort.update(product);
    }
}
