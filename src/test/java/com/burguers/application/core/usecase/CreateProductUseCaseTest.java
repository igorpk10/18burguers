package com.burguers.application.core.usecase;

import br.com.eighteenburguers.core.domain.Product;
import br.com.eighteenburguers.core.enums.Category;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.product.CreateProductInputPort;
import br.com.eighteenburguers.core.ports.outbound.product.CreateProductOutputPort;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductByIdOutputPort;
import br.com.eighteenburguers.core.usecase.product.CreateProductUseCase;
import br.com.eighteenburguers.core.usecase.product.exceptions.ProductAlreadyExistsException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.File;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CreateProductUseCaseTest {

    @Mock
    FindProductByIdOutputPort findProductByIdOutputPort;

    @Mock
    CreateProductOutputPort createProductOutputPort;

    Faker faker;

    @BeforeAll
    void setup() {
        this.faker = Faker.instance();
    }

    // @Test
    // void shouldBeAbleToCreateANewProduct() throws BusinessException {
    //     Product product = new Product(faker.name().fullName(), Category.LANCHE, BigDecimal.valueOf(Math.random()),"Teste descrição", new File("teste"));

    //     when(findProductByIdOutputPort.find(any())).thenReturn(null);
    //     when(createProductOutputPort.insert(any())).thenReturn(product);

    //     CreateProductInputPort input = new CreateProductUseCase(findProductByIdOutputPort, createProductOutputPort);
    //     Product productResponse = input.insert(product);

    //     assertNotNull(productResponse);

    //     verify(findProductByIdOutputPort, times(1)).find(any());
    //     verify(createProductOutputPort, times(1)).insert(any());
    // }

    // @Test
    // void shouldBeNotAbleToCreateANewProductBecauseAlreadyExists() {
    //     Product product = new Product(faker.name().fullName(), Category.LANCHE, BigDecimal.valueOf(Math.random()),"Teste descrição", new File("teste"));

    //     when(findProductByIdOutputPort.find(any())).thenReturn(new Product(null, null, null, null, null));

    //     CreateProductInputPort input = new CreateProductUseCase(findProductByIdOutputPort, createProductOutputPort);
    //     assertThrows(ProductAlreadyExistsException.class, () -> input.insert(product));

    //     verify(findProductByIdOutputPort, times(1)).find(any());
    //     verify(createProductOutputPort, times(0)).insert(any());
    // }
}
