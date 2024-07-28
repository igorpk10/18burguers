package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.services.FindProductByCategoryService;

import java.util.List;

public class FindProductByCategoryUseCaseImpl implements br.com.eighteenburguers.product.usecase.FindProductByCategoryUseCase {

    private final FindProductByCategoryService findProductByCategoryService;

    public FindProductByCategoryUseCaseImpl(FindProductByCategoryService findProductByCategoryService) {
        this.findProductByCategoryService = findProductByCategoryService;
    }

    @Override
    public List<Product> find(int codigo) throws BusinessException {
        List<Product> productList = findProductByCategoryService.find(codigo);
        return productList;
    }
}
