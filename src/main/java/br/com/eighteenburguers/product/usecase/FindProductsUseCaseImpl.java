package br.com.eighteenburguers.product.usecase;

import java.util.List;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.services.FindProductService;

public class FindProductsUseCaseImpl implements br.com.eighteenburguers.product.usecase.FindProductsUseCase {

    private final FindProductService findProductService;

    public FindProductsUseCaseImpl(FindProductService findProductService) {
        this.findProductService = findProductService;
    }

    @Override
    public List<Product> execute() {
        return findProductService.findAll();
    }
    
}
