package br.com.eighteenburguers.order.usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderItem;
import br.com.eighteenburguers.order.entitys.OrderStatus;
import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.order.services.SaveOrderService;
import br.com.eighteenburguers.product.services.FindProductService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final FindProductService findProductService;
    private final SaveOrderService saveOrderService;

    @Override
    public Order execute(final String customerId, final List<OrderItem> items) throws BusinessException {

        List<OrderItem> orderItems = this.mapProducts(items);

        Order order = new Order(customerId, orderItems);
        order.calculateAmount();
        order.setStatus(OrderStatus.AWAITING_PAYMENT);
        return saveOrderService.save(order);
    }

    private List<OrderItem> mapProducts(List<OrderItem> items) {
        List<OrderItem> orderItems = new ArrayList<>();

        List<Long> ids = items.stream().map(OrderItem::getProduct)
            .map(Product::getId)    
            .collect(Collectors.toList());

        List<Product> products = findProductService.findByIds(ids);

        for(OrderItem item: items) {
            Optional<Product> optional = products.stream()
                .filter(product -> product.getId().equals(item.getProduct().getId()))
                .findFirst();
            
            if(optional.isPresent()) {
                orderItems.add(new OrderItem(optional.get(), item.getQuantity(), item.getObservation()));
            }
        }

        return orderItems;
    }

}
