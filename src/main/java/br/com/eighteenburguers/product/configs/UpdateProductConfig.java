package br.com.eighteenburguers.product.configs;

import br.com.eighteenburguers.product.services.FindProductByIdServiceImpl;
import br.com.eighteenburguers.product.services.UpdateProductServiceImpl;
import br.com.eighteenburguers.product.usecase.UpdateProductUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateProductConfig {

    @Bean
    public UpdateProductUseCaseImpl updateProductUseCase(FindProductByIdServiceImpl findProductByIdAdapter, UpdateProductServiceImpl updateProductAdapter) {
        return new UpdateProductUseCaseImpl(findProductByIdAdapter, updateProductAdapter);
    }
}
