package br.com.eighteenburguers.adapters.inbound.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eighteenburguers.adapters.inbound.controller.mappers.OrderMapper;
import br.com.eighteenburguers.adapters.inbound.controller.request.OrderRequest;
import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.domain.OrderItem;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.order.CreateOrderInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final CreateOrderInputPort createOrderInputPort;
    private final OrderMapper mapper;
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderRequest orderRequest) {
        try {
            log.info("order: {}", orderRequest);
            List<OrderItem> items = mapper.toOrderItem(orderRequest.getItems());
            Order order = createOrderInputPort.execute(orderRequest.getCustomerId(), items);
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
