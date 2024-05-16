package br.com.eighteenburguers.adapters.inbound.controller.mappers;

import br.com.eighteenburguers.adapters.inbound.controller.request.ProductRequest;
import br.com.eighteenburguers.adapters.inbound.controller.response.ProductResponse;
import br.com.eighteenburguers.core.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toProduct(ProductRequest productRequest);

    ProductResponse toProductResponse(Product product);
}
