package br.com.eighteenburguers.order.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.order.repositories.OrderRepository;
import br.com.eighteenburguers.order.entitys.OrderEntity;
import br.com.eighteenburguers.order.entitys.OrderItemEntity;
import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderItem;
import br.com.eighteenburguers.product.entitys.Product;

@Component
public class FindOrderServiceImpl implements FindOrderService {

    private final OrderRepository repository;

    public FindOrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

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
        return new Order(entity.getId(), entity.getCustomerId().toString(), items, entity.getAmount(), entity.getStatus(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

    private OrderItem mapOrderItem(OrderItemEntity entity) {
        Product product = new Product(entity.getName(), entity.getCategory(), entity.getPrice(), entity.getDescription(), null);
        product.setId(entity.getId());
        return new OrderItem(product, entity.getQuantity(), entity.getObservation());
    }
    
}
