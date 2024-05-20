package br.com.eighteenburguers.core.ports.inbound.product;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;

import java.util.List;

public interface FindProductByCategoryInputPort {

    List<Product> find(int codigo) throws BusinessException;
}
