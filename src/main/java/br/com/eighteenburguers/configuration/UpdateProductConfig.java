package br.com.eighteenburguers.configuration;

import br.com.eighteenburguers.adapters.outbound.FindProductByIdAdapter;
import br.com.eighteenburguers.adapters.outbound.UpdateProductAdapter;
import br.com.eighteenburguers.core.usecase.product.UpdateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateProductConfig {

    @Bean
    public UpdateProductUseCase updateProductUseCase(FindProductByIdAdapter findProductByIdAdapter, UpdateProductAdapter updateProductAdapter) {
        return new UpdateProductUseCase(findProductByIdAdapter, updateProductAdapter);
    }
}
