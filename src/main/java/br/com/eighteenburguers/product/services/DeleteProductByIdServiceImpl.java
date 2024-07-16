package br.com.eighteenburguers.product.services;

import br.com.eighteenburguers.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductByIdServiceImpl implements DeleteProductByIdService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
