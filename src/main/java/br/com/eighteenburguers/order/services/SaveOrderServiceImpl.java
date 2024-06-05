package br.com.eighteenburguers.order.services;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.order.repositories.OrderRepository;
import br.com.eighteenburguers.order.entitys.OrderEntity;
import br.com.eighteenburguers.order.mappers.OrderEntityMapper;
import br.com.eighteenburguers.order.entitys.Order;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SaveOrderServiceImpl implements SaveOrderService {

    private final OrderRepository repository;
    private final OrderEntityMapper mapper;

    @Override
    public Order save(Order order) {
        OrderEntity entity = mapper.toEntity(order);
        repository.save(entity);
        order.setId(entity.getId());
        order.setCreatedAt(entity.getCreatedAt());
        order.setUpdatedAt(entity.getUpdatedAt());
        return order;
    }
    
}
