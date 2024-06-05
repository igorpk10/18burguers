package br.com.eighteenburguers.order.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eighteenburguers.application.ApiV1;
import br.com.eighteenburguers.order.mappers.OrderMapper;
import br.com.eighteenburguers.order.dtos.OrderRequest;
import br.com.eighteenburguers.configuration.ErrorResponses;
import br.com.eighteenburguers.order.dtos.OrderResponse;
import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderItem;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.order.usecases.CheckoutOrderUseCase;
import br.com.eighteenburguers.order.usecases.CreateOrderUseCase;
import br.com.eighteenburguers.order.usecases.FindAllOrdersUseCase;
import br.com.eighteenburguers.order.usecases.FindOrderByIdUseCase;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Orders")
@Slf4j
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController implements ApiV1 {

    private final CreateOrderUseCase createOrderInputPort;
    private final CheckoutOrderUseCase checkoutOrderInputPort;
    private final FindOrderByIdUseCase findOrderByIdInputPort;
    private final FindAllOrdersUseCase findAllOrdersInputPort;
    private final OrderMapper mapper;
    
    @PostMapping
    @Transactional
    @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = OrderResponse.class)))
    public ResponseEntity<?> create(@RequestBody OrderRequest orderRequest) {
        try {
            log.info("order: {}", orderRequest);
            List<OrderItem> items = mapper.toOrderItem(orderRequest.getItems());
            Order order = createOrderInputPort.execute(orderRequest.getCustomerId(), items);
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(order));
        } catch (BusinessException e) {
            log.error("Error on try create new order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponses(e));
        }
    }

    @Transactional
    @PostMapping("/{orderId}/checkout")
    @ApiResponse(responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    public ResponseEntity<?> checkout(@PathVariable("orderId") Long orderId) {
        try {
            checkoutOrderInputPort.execute(orderId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (BusinessException e) {
            log.error("Error on try checkout order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponses(e));
        }
    }

    @GetMapping("/{orderId}")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderResponse.class)))
    public ResponseEntity<?> findById(@PathVariable("orderId") Long orderId) {
        try {
            Order order = findOrderByIdInputPort.execute(orderId);
            return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(order));
        } catch (BusinessException e) {
            log.error("Error on try checkout order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponses(e));
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = OrderResponse.class))))
    public ResponseEntity<?> list() {
        List<Order> orders = findAllOrdersInputPort.execute();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(orders));
    }
}
