package br.com.eighteenburguers.order.mappers;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.eighteenburguers.order.entitys.OrderEntity;
import br.com.eighteenburguers.order.entitys.OrderItemEntity;
import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderItem;

@Mapper(unmappedTargetPolicy = IGNORE, nullValueCheckStrategy = ALWAYS, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderEntityMapper {
    
    @Mapping(target = "items", source = "items")
    OrderEntity toEntity(Order order);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "category", source = "product.category")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "quantity", source = "quantity")
    OrderItemEntity toEntity(OrderItem item);

    List<OrderItemEntity> toEntity(List<OrderItem> items);

}
