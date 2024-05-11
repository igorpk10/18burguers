package br.com.eighteenburguers.core.usecase.product;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.DeleteProductByIdInputPort;
import br.com.eighteenburguers.core.ports.outbound.DeleteProductByIdOutputPort;
import br.com.eighteenburguers.core.ports.outbound.FindProductByIdOutputPort;
import br.com.eighteenburguers.core.usecase.product.exceptions.ProductNotExistsException;

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
        Optional<Product> optional = findProductByIdOutputPort.find(id);

        if (optional.isPresent()) {
            throw new ProductNotExistsException();
        }

        return deleteProductByIdOutputPort.delete(id);
    }
}
