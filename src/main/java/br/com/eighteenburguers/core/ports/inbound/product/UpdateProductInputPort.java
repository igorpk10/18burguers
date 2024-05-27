package br.com.eighteenburguers.core.ports.inbound.product;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;

public interface UpdateProductInputPort {

    void update(Product product) throws BusinessException;
}
