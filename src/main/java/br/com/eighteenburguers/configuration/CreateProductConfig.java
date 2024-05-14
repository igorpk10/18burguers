package br.com.eighteenburguers.configuration;

import br.com.eighteenburguers.adapters.outbound.CreateProductAdapter;
import br.com.eighteenburguers.adapters.outbound.FindProductByIdAdapter;
import br.com.eighteenburguers.core.usecase.product.CreateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateProductConfig {
    @Bean
    public CreateProductUseCase createProductUseCase(FindProductByIdAdapter findProductByIdAdapter, CreateProductAdapter createProductAdapter) {
        return new CreateProductUseCase(findProductByIdAdapter, createProductAdapter);
    }
}
