package br.com.eighteenburguers.order.mappers;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.eighteenburguers.order.dtos.OrderItemRequest;
import br.com.eighteenburguers.order.dtos.OrderItemResponse;
import br.com.eighteenburguers.order.dtos.OrderResponse;
import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderItem;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE, nullValueCheckStrategy = ALWAYS)
public interface OrderMapper {

    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "quantity", source = "quantity")
    OrderItem toOrderItem(OrderItemRequest request);

    List<OrderItem> toOrderItem(List<OrderItemRequest> request);

    @Mapping(target = "statusDescription", source = "status.description")
    OrderResponse toResponse(Order order);

    List<OrderResponse> toResponse(List<Order> orders);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "category", source = "product.category")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "observation", source = "observation")
    OrderItemResponse toResponse(OrderItem item);
}
