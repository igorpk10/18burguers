package br.com.eighteenburguers.product.configs;

import br.com.eighteenburguers.product.services.DeleteProductByIdServiceImpl;
import br.com.eighteenburguers.product.services.FindProductByIdServiceImpl;
import br.com.eighteenburguers.product.usecase.DeleteProductByIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteProductByIdConfig {

    @Bean
    public DeleteProductByIdUseCaseImpl deleteProductByIdUseCase(DeleteProductByIdServiceImpl deleteProductByIdAdapter, FindProductByIdServiceImpl findProductByIdAdapter) {
        return new DeleteProductByIdUseCaseImpl(deleteProductByIdAdapter, findProductByIdAdapter);
    }
}
