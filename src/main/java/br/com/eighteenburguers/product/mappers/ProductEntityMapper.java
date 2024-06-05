package br.com.eighteenburguers.product.mappers;

import br.com.eighteenburguers.product.entitys.ProductEntity;
import br.com.eighteenburguers.product.entitys.Product;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

import java.util.List;


@Mapper(unmappedTargetPolicy = IGNORE, nullValueCheckStrategy = ALWAYS)
public interface ProductEntityMapper {

    Product toProduct(ProductEntity entity);

    ProductEntity toEntity(Product product);

    List<Product> toProduct(List<ProductEntity> entities);
}
