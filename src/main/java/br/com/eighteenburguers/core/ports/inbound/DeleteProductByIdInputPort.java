package br.com.eighteenburguers.core.ports.inbound;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;

public interface DeleteProductByIdInputPort {

    Product delete(Long id) throws BusinessException;
}
