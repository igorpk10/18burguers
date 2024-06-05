package br.com.eighteenburguers.product.configs;

import br.com.eighteenburguers.product.services.CreateProductServiceImpl;
import br.com.eighteenburguers.product.usecase.CreateProductUseCaseImpl;
import br.com.eighteenburguers.product.usecase.CreateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateProductConfig {
    
    @Bean
    public CreateProductUseCase createProductUseCase(CreateProductServiceImpl createProductAdapter) {
        return new CreateProductUseCaseImpl(createProductAdapter);
    }
}
