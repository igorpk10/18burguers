package br.com.eighteenburguers.adapters.inbound.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eighteenburguers.adapters.inbound.controller.mappers.OrderMapper;
import br.com.eighteenburguers.adapters.inbound.controller.request.OrderRequest;
import br.com.eighteenburguers.adapters.inbound.controller.response.ErrorResponses;
import br.com.eighteenburguers.adapters.inbound.controller.response.OrderResponse;
import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.domain.OrderItem;
import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.order.CreateOrderInputPort;
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

    private final CreateOrderInputPort createOrderInputPort;
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
}
