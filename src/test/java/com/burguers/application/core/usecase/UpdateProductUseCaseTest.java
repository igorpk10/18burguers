package com.burguers.application.core.usecase;

import br.com.eighteenburguers.core.ports.outbound.product.UpdateProductOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UpdateProductUseCaseTest {

    @Mock
    private UpdateProductOutputPort updateProductOutputPort;

    @Test
    void shouldUpdateAProductById() {
        verify(updateProductOutputPort, times(0)).update(any());
    }
}
