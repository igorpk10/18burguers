package br.com.eighteenburguers.order.controllers;

import java.util.List;

import br.com.eighteenburguers.order.dtos.OrderRequest;
import br.com.eighteenburguers.order.dtos.OrderResponse;
import br.com.eighteenburguers.order.entitys.OrderStatus;
import br.com.eighteenburguers.order.entitys.OrderPaymentResponse;
import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface OrderController {

    public OrderResponse create(OrderRequest orderRequest) throws BusinessException;

    public void checkout(Long orderId) throws BusinessException;

    public OrderResponse findOrderById(Long id) throws BusinessException;

    public List<OrderResponse> findAllOrders();
    
    void paymentUpdate(Long orderId) throws BusinessException;
    
    OrderResponse updateStatus(Long orderId, OrderStatus status) throws BusinessException;

    public OrderPaymentResponse checkPaymentStatus(Long orderId) throws BusinessException;
}
