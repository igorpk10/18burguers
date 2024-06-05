package com.burguers.application.core.usecase;

import br.com.eighteenburguers.product.services.DeleteProductByIdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class DeleteProductUseCaseTest {

    @Mock
    private DeleteProductByIdService deleteProductByIdOutputPort;

    @Test
    void shouldDeleteAProductById() {
        verify(deleteProductByIdOutputPort, times(0)).delete(any());
    }

}
