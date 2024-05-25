package br.com.eighteenburguers.adapters.outbound;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.adapters.outbound.repository.ProductRepository;
import br.com.eighteenburguers.adapters.outbound.repository.entity.product.ProductEntity;
import br.com.eighteenburguers.adapters.outbound.repository.mapper.ProductEntityMapper;
import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductOutputPort;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FindProductAdapter implements FindProductOutputPort{

    private final ProductRepository repository;
    private final ProductEntityMapper mapper;

    @Override
    public List<Product> findByIds(List<Long> ids) {    
        List<ProductEntity> products = ids.stream().map(id -> repository.findById(id))
            .filter(Optional::isPresent)
            .map(Optional::get)
        .collect(Collectors.toList());

        return mapper.toProduct(products);
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> entities = repository.findAll();
        return mapper.toProduct(entities);
    }
    
}
