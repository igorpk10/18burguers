package br.com.eighteenburguers.core.usecase.product;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.FindProductByIdInputPort;
import br.com.eighteenburguers.core.ports.outbound.FindProductByIdOutputPort;
import br.com.eighteenburguers.core.usecase.product.exceptions.ProductNotExistsException;

public class FindProductByIdUseCase implements FindProductByIdInputPort {

    private final FindProductByIdOutputPort findProductByIdOutputPort;

    public FindProductByIdUseCase(FindProductByIdOutputPort findProductByIdOutputPort) {
        this.findProductByIdOutputPort = findProductByIdOutputPort;
    }

    @Override
    public Product find(Long id) throws BusinessException {
        return findProductByIdOutputPort.find(id).orElseThrow(ProductNotExistsException::new);
    }
}
