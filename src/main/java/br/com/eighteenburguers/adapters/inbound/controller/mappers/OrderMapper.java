package br.com.eighteenburguers.adapters.inbound.controller.mappers;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.eighteenburguers.adapters.inbound.controller.request.OrderItemRequest;
import br.com.eighteenburguers.core.domain.OrderItem;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE, nullValueCheckStrategy = ALWAYS)
public interface OrderMapper {

    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "quantity", source = "quantity")
    OrderItem toOrderItem(OrderItemRequest request);

    List<OrderItem> toOrderItem(List<OrderItemRequest> request);
}
