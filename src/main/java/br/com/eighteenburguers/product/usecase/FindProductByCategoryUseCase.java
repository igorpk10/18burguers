package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;

import java.util.List;

public interface FindProductByCategoryUseCase {

    List<Product> find(int codigo) throws BusinessException;
}
