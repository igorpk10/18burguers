package br.com.eighteenburguers.adapters.outbound;

import br.com.eighteenburguers.adapters.outbound.repository.ProductRepository;
import br.com.eighteenburguers.adapters.outbound.repository.entity.product.ProductEntity;
import br.com.eighteenburguers.adapters.outbound.repository.mapper.ProductEntityMapper;
import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductByCategoryOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindProductByCategoryAdapter implements FindProductByCategoryOutputPort {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEntityMapper productEntityMapper;

    @Override
    public List<Product> find(int codigo) {
        List<ProductEntity> productList = productRepository.findByCategory(codigo);
        return (List<Product>) productList.stream().map(productEntityMapper::toProduct);
    }
}
