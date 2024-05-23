package br.com.eighteenburguers.adapters.outbound;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.adapters.outbound.repository.OrderRepository;
import br.com.eighteenburguers.adapters.outbound.repository.entity.order.OrderEntity;
import br.com.eighteenburguers.adapters.outbound.repository.entity.order.OrderItemEntity;
import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.domain.OrderItem;
import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.ports.outbound.order.FindOrderOutputPort;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FindOrderAdapter implements FindOrderOutputPort {

    private final OrderRepository repository;

    @Override
    public Optional<Order> findById(Long id) {
        Optional<OrderEntity> optional = repository.findById(id);
        if(optional.isEmpty()) return Optional.empty();
        OrderEntity entity = optional.get();
        return Optional.of(mapOrder(entity));
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll().stream().map(this::mapOrder).collect(Collectors.toList());
    }

    private Order mapOrder(OrderEntity entity) {
        List<OrderItem> items = entity.getItems().stream().map(this::mapOrderItem).collect(Collectors.toList());
        return new Order(entity.getId(), entity.getCustomerId(), items, entity.getAmount(), entity.getStatus(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

    private OrderItem mapOrderItem(OrderItemEntity entity) {
        Product product = new Product(entity.getName(), entity.getCategory(), entity.getPrice(), entity.getDescription(), null);
        product.setId(entity.getId());
        return new OrderItem(product, entity.getQuantity(), entity.getObservation());
    }
    
}
