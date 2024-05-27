package br.com.eighteenburguers.core.usecase.product;

import java.util.List;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.ports.inbound.product.FindProductsInputPort;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductOutputPort;

public class FindProductsUseCase implements FindProductsInputPort {

    private final FindProductOutputPort findProductOutputPort;

    public FindProductsUseCase(FindProductOutputPort findProductOutputPort) {
        this.findProductOutputPort = findProductOutputPort;
    }

    @Override
    public List<Product> execute() {
        return findProductOutputPort.findAll();
    }
    
}
