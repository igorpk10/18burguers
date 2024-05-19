package br.com.eighteenburguers.adapters.outbound.repository.mapper;

import br.com.eighteenburguers.adapters.outbound.repository.entity.product.ProductEntity;
import br.com.eighteenburguers.core.domain.Product;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, nullValueCheckStrategy = ALWAYS, injectionStrategy = CONSTRUCTOR)
public interface ProductEntityMapper {

    Product toProduct(ProductEntity entity);

    ProductEntity toEntity(Product product);
}
