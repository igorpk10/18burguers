package com.burguers.application.core.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.domain.OrderItem;
import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.enums.Category;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.order.CreateOrderInputPort;
import br.com.eighteenburguers.core.ports.outbound.order.SaveOrderOutputPort;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductOutputPort;
import br.com.eighteenburguers.core.usecase.order.CreateOrderUseCase;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {
    
    @Mock
    FindProductOutputPort findProductOutputPort;
    
    @Mock
    SaveOrderOutputPort saveOrderOutputPort;

    Faker faker;

    @BeforeEach
    void setup() {
        this.faker = Faker.instance();
    }

    @Test
    void shouldBeCreateOrder() throws BusinessException {
        Mockito.when(findProductOutputPort.findByIds(Mockito.anyList())).thenReturn(mockProducts());
        Mockito.when(saveOrderOutputPort.save(Mockito.any())).thenReturn(new Order(null, null));

        CreateOrderInputPort usecase = new CreateOrderUseCase(findProductOutputPort, saveOrderOutputPort);
        Order order = usecase.execute(faker.random().nextLong(), List.of(new OrderItem(mockProduct(), 1, null)));
        assertNotNull(order);
        Mockito.verify(saveOrderOutputPort, Mockito.times(1)).save(Mockito.any());
    }

    List<Product> mockProducts() {
        return List.of(mockProduct());
    }

    Product mockProduct() {
        Product product = new Product(faker.commerce().productName(), Category.LANCHE, BigDecimal.ZERO, "", "");
        product.setId(1L);
        return product;
    }
}
