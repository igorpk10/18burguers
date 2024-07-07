package br.com.eighteenburguers.order.controllers;

import java.util.List;

import br.com.eighteenburguers.order.dtos.OrderRequest;
import br.com.eighteenburguers.order.dtos.OrderResponse;
import br.com.eighteenburguers.order.mappers.OrderMapper;
import br.com.eighteenburguers.order.usecases.CheckoutOrderUseCase;
import br.com.eighteenburguers.order.usecases.CreateOrderUseCase;
import br.com.eighteenburguers.order.usecases.FindAllOrdersUseCase;
import br.com.eighteenburguers.order.usecases.FindOrderByIdUseCase;
import br.com.eighteenburguers.product.exceptions.BusinessException;

public class OrderControllerImpl implements OrderController {

    private CreateOrderUseCase createOrderUseCase;
    private CheckoutOrderUseCase checkoutOrderUseCase;
    private FindOrderByIdUseCase findOrderByIdUseCase;
    private FindAllOrdersUseCase findAllOrdersUseCase;

    private OrderMapper orderMapper;

    public OrderControllerImpl(CreateOrderUseCase createOrderUseCase, CheckoutOrderUseCase checkoutOrderUseCase,
            FindOrderByIdUseCase findOrderByIdUseCase, FindAllOrdersUseCase findAllOrdersUseCase,
            OrderMapper orderMapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.checkoutOrderUseCase = checkoutOrderUseCase;
        this.findAllOrdersUseCase = findAllOrdersUseCase;
        this.orderMapper = orderMapper;
    }

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
}
