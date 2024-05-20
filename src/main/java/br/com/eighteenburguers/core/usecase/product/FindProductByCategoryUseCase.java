package br.com.eighteenburguers.core.usecase.product;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.product.FindProductByCategoryInputPort;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductByCategoryOutputPort;
import br.com.eighteenburguers.core.usecase.customer.exceptions.CustomerNotFound;
import br.com.eighteenburguers.core.usecase.product.exceptions.ProductNotExistsException;

import java.util.List;
import java.util.Objects;

public class FindProductByCategoryUseCase implements FindProductByCategoryInputPort {

    private final FindProductByCategoryOutputPort findProductByCategoryOutputPort;

    public FindProductByCategoryUseCase(FindProductByCategoryOutputPort findProductByCategoryOutputPort) {
        this.findProductByCategoryOutputPort = findProductByCategoryOutputPort;
    }

    @Override
    public List<Product> find(int codigo) throws BusinessException {
        List<Product> productList = findProductByCategoryOutputPort.find(codigo);

        if(productList.isEmpty()){
            throw new ProductNotExistsException();
        }

        return productList;
    }
}
