package br.com.eighteenburguers.product.usecase;

import java.util.List;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.services.FindProductService;

public class FindProductsUseCaseImpl implements br.com.eighteenburguers.product.usecase.FindProductsUseCase {

    private final FindProductService findProductOutputPort;

    public FindProductsUseCaseImpl(FindProductService findProductOutputPort) {
        this.findProductOutputPort = findProductOutputPort;
    }

    @Override
    public List<Product> execute() {
        return findProductOutputPort.findAll();
    }
    
}
