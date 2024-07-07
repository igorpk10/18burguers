package br.com.eighteenburguers.product.mappers;

import br.com.eighteenburguers.product.dtos.ProductRequest;
import br.com.eighteenburguers.product.dtos.ProductResponse;
import br.com.eighteenburguers.product.entitys.Product;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductRequest productRequest);

    ProductResponse toProductResponse(Product product);

    List<ProductResponse> toListResponse(List<Product> products);
}
