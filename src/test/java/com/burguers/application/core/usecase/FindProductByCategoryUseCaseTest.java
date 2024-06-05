package com.burguers.application.core.usecase;

import br.com.eighteenburguers.product.entitys.Product;
import br.com.eighteenburguers.category.model.Category;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.services.FindProductByCategoryService;
import br.com.eighteenburguers.product.usecase.FindProductByCategoryUseCaseImpl;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class FindProductByCategoryUseCaseTest {

    @Mock
    private FindProductByCategoryService findProductByCategoryOutputPort;

    Faker faker;

    @BeforeAll
    void setup() {
        this.faker = Faker.instance();
    }

    @Test
    void shouldReturnAProductSearchedByCategoryId() throws BusinessException {
        var findProductByCategoryUseCase = new FindProductByCategoryUseCaseImpl(findProductByCategoryOutputPort);
        int codigoCategoria = Category.LANCHE.getCodigo();
        Product product = new Product(faker.name().fullName(), Category.LANCHE, BigDecimal.valueOf(Math.random()),"Teste descrição", "teste");
        List<Product> productList= new ArrayList<>();
        productList.add(product);

        when(findProductByCategoryOutputPort.find(codigoCategoria)).thenReturn(productList);

        List<Product> productsReturned = findProductByCategoryUseCase.find(codigoCategoria);

        assertNotNull(productsReturned);
        assertEquals(productsReturned.get(0).getCategory().getCodigo(), codigoCategoria);
    }

    @Test
    void shouldReturnNotFoundWhenProductDoesNotExists() throws BusinessException{
        var findProductByCategoryUseCase = new FindProductByCategoryUseCaseImpl(findProductByCategoryOutputPort);
        int codigoCategoria = Category.LANCHE.getCodigo();
        List<Product> productList = Collections.emptyList();

        when(findProductByCategoryOutputPort.find(codigoCategoria)).thenReturn(productList);

        List<Product> list = findProductByCategoryUseCase.find(codigoCategoria);
        assertNotNull(list);
    }
}
