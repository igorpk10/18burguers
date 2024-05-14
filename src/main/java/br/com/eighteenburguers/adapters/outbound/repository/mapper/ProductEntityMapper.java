package br.com.eighteenburguers.adapters.outbound.repository.mapper;

import br.com.eighteenburguers.adapters.inbound.controller.request.ProductRequest;
import br.com.eighteenburguers.adapters.inbound.controller.response.ProductResponse;
import br.com.eighteenburguers.core.domain.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    Product toProduct(ProductRequest productRequest);

    ProductResponse toProductResponse(Product product);
}
