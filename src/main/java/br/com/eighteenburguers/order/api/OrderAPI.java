package br.com.eighteenburguers.order.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eighteenburguers.application.ApiV1;
import br.com.eighteenburguers.configuration.ErrorResponse;
import br.com.eighteenburguers.configuration.ErrorResponses;
import br.com.eighteenburguers.order.controllers.OrderController;
import br.com.eighteenburguers.order.dtos.OrderRequest;
import br.com.eighteenburguers.order.dtos.OrderResponse;
import br.com.eighteenburguers.order.dtos.OrderUpdateStatusRequest;
import br.com.eighteenburguers.product.exceptions.BusinessException;
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
public class OrderAPI implements ApiV1 {

	@Autowired
	private OrderController orderController;

	@PostMapping
	@Transactional
	@ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderResponse.class)))
	public ResponseEntity<?> create(@RequestBody OrderRequest orderRequest) {
		try {
			var createdOrder = orderController.create(orderRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
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
			orderController.checkout(orderId);
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
			var order = orderController.findOrderById(orderId);
			return ResponseEntity.status(HttpStatus.OK).body(order);
		} catch (BusinessException e) {
			log.error("Error on try checkout order: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponses(e));
		}
	}

    @GetMapping
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = OrderResponse.class))))
    public ResponseEntity<?> findOrders() {
        try{
            var orders = orderController.findAllOrders();
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/payment-status/{orderId}")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = OrderResponse.class))))
    public ResponseEntity<?> checkPaymentStatus(@PathVariable("orderId") Long orderId) {
        try {
            var response = orderController.checkPaymentStatus(orderId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

	@GetMapping("/{orderId}/payment-update")
	@ApiResponse(responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
	public ResponseEntity<?> paymentUpdate(@PathVariable("orderId") Long orderId) {
		try {
			orderController.paymentUpdate(orderId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/{orderId}/status")
	public ResponseEntity<?> updateStatus(@PathVariable("orderId") Long orderId,
			@RequestBody OrderUpdateStatusRequest request) {
		try {
			OrderResponse response = orderController.updateStatus(orderId, request.getStatus());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(ErrorResponse.builder().code(e.getCode()).message(e.getMessage()).build());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
