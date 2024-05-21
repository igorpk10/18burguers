package br.com.eighteenburguers.adapters.outbound.repository.mapper;

import br.com.eighteenburguers.adapters.outbound.repository.entity.product.ProductEntity;
import br.com.eighteenburguers.core.domain.Product;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    Product toProduct(ProductEntity entity);

    ProductEntity toEntity(Product product);

    List<Product> toProduct(List<ProductEntity> entities);
}
