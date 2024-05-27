package br.com.eighteenburguers.adapters.outbound;

import br.com.eighteenburguers.adapters.outbound.repository.ProductRepository;
import br.com.eighteenburguers.adapters.outbound.repository.entity.product.ProductEntity;
import br.com.eighteenburguers.adapters.outbound.repository.mapper.ProductEntityMapper;
import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.enums.Category;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductByCategoryOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindProductByCategoryAdapter implements FindProductByCategoryOutputPort {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEntityMapper productEntityMapper;

    @Override
    public List<Product> find(int codigo) {

        List<ProductEntity> productList = productRepository.findByCategory(Category.ofCodigo(codigo));
        return productList.stream().map(productEntityMapper::toProduct).collect(Collectors.toList());
    }
}
