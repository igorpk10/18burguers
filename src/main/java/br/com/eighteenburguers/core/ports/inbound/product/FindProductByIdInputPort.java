package br.com.eighteenburguers.core.ports.inbound.product;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;

import java.util.Optional;

public interface FindProductByIdInputPort {

    Product find(Long id) throws BusinessException;
}
