package br.com.eighteenburguers.core.usecase.product;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.product.FindProductByCategoryInputPort;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductByCategoryOutputPort;

import java.util.List;

public class FindProductByCategoryUseCase implements FindProductByCategoryInputPort {

    private final FindProductByCategoryOutputPort findProductByCategoryOutputPort;

    public FindProductByCategoryUseCase(FindProductByCategoryOutputPort findProductByCategoryOutputPort) {
        this.findProductByCategoryOutputPort = findProductByCategoryOutputPort;
    }

    @Override
    public List<Product> find(int codigo) throws BusinessException {
        return findProductByCategoryOutputPort.find(codigo);
    }
}
