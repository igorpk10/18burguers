package br.com.eighteenburguers.order.controllers;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.order.dtos.OrderRequest;
import br.com.eighteenburguers.order.dtos.OrderResponse;
import br.com.eighteenburguers.order.entitys.OrderPaymentResponse;
import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderStatus;
import br.com.eighteenburguers.order.mappers.OrderMapper;
import br.com.eighteenburguers.order.usecases.CheckoutOrderUseCase;
import br.com.eighteenburguers.order.usecases.CreateOrderUseCase;
import br.com.eighteenburguers.order.usecases.FindAllOrdersUseCase;
import br.com.eighteenburguers.order.usecases.FindOrderByIdUseCase;
import br.com.eighteenburguers.order.usecases.PaymentUpdateUseCase;
import br.com.eighteenburguers.order.usecases.UpdateOrderStatus;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderControllerImpl implements OrderController {

    private CreateOrderUseCase createOrderUseCase;
    private CheckoutOrderUseCase checkoutOrderUseCase;
    private FindOrderByIdUseCase findOrderByIdUseCase;
    private FindAllOrdersUseCase findAllOrdersUseCase;
    private PaymentUpdateUseCase paymentUpdateUseCase;
    private UpdateOrderStatus updateOrderStatus;
    private OrderMapper orderMapper;

    @Override
    public OrderResponse create(OrderRequest orderRequest) throws BusinessException {
        var items = orderMapper.toOrderItem(orderRequest.getItems());
        var customerId = orderRequest.getCustomerId();
        var order = createOrderUseCase.execute(customerId, items);
        return orderMapper.toResponse(order);
    }

    @Override
    public void checkout(Long orderId) throws BusinessException {
        checkoutOrderUseCase.execute(orderId);
    }

    @Override
    public OrderResponse findOrderById(Long id) throws BusinessException {
        var order = findOrderByIdUseCase.execute(id);
        return orderMapper.toResponse(order);
    }

    public List<OrderResponse> findAllOrders() {
        var orders = findAllOrdersUseCase.execute();
        return orderMapper.toResponse(orders);
    }

    @Override
    public OrderPaymentResponse checkPaymentStatus(Long orderId) throws BusinessException {
        var order = findOrderByIdUseCase.execute(orderId);
        return new OrderPaymentResponse(order.getId(), order.getStatus().name());
    }
    
}
    
    @Override
    public void paymentUpdate(Long orderId) throws BusinessException {
    	paymentUpdateUseCase.execute(orderId);
    }
    
    @Override
    public OrderResponse updateStatus(Long orderId, OrderStatus status) throws BusinessException {
    	Order order = updateOrderStatus.execute(orderId, status);
    	return orderMapper.toResponse(order);
    }
}
