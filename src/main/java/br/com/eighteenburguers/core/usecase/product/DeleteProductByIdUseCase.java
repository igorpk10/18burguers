package br.com.eighteenburguers.core.usecase.product;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.product.DeleteProductByIdInputPort;
import br.com.eighteenburguers.core.ports.outbound.product.DeleteProductByIdOutputPort;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductByIdOutputPort;
import br.com.eighteenburguers.core.usecase.product.exceptions.ProductNotExistsException;

import java.util.Objects;
import java.util.Optional;

public class DeleteProductByIdUseCase implements DeleteProductByIdInputPort {

    private final DeleteProductByIdOutputPort deleteProductByIdOutputPort;

    private final FindProductByIdOutputPort findProductByIdOutputPort;

    public DeleteProductByIdUseCase(DeleteProductByIdOutputPort deleteProductByIdOutputPort, FindProductByIdOutputPort findProductByIdOutputPort) {
        this.deleteProductByIdOutputPort = deleteProductByIdOutputPort;
        this.findProductByIdOutputPort = findProductByIdOutputPort;
    }

    @Override
    public Product delete(Long id) throws BusinessException {
        Product createdProduct = findProductByIdOutputPort.find(id);

        if (Objects.nonNull(createdProduct)) {
            throw new ProductNotExistsException();
        }

        return deleteProductByIdOutputPort.delete(id);
    }
}
