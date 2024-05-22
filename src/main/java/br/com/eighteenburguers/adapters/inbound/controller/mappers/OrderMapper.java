package br.com.eighteenburguers.adapters.inbound.controller.mappers;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.eighteenburguers.adapters.inbound.controller.request.OrderItemRequest;
import br.com.eighteenburguers.adapters.inbound.controller.response.OrderItemResponse;
import br.com.eighteenburguers.adapters.inbound.controller.response.OrderResponse;
import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.domain.OrderItem;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE, nullValueCheckStrategy = ALWAYS)
public interface OrderMapper {

    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "quantity", source = "quantity")
    OrderItem toOrderItem(OrderItemRequest request);

    List<OrderItem> toOrderItem(List<OrderItemRequest> request);

    @Mapping(target = "statusDescription", source = "status.description")
    OrderResponse toResponse(Order order);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "category", source = "product.category")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "observation", source = "observation")
    OrderItemResponse toResponse(OrderItem item);
}
