package br.com.eighteenburguers.product.configs;

import br.com.eighteenburguers.product.services.FindProductByIdServiceImpl;
import br.com.eighteenburguers.product.usecase.FindProductByIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindProductByIdConfig {

    @Bean
    public FindProductByIdUseCaseImpl findProductByIdUseCase(FindProductByIdServiceImpl findProductByIdAdapter) {
        return new FindProductByIdUseCaseImpl(findProductByIdAdapter);
    }
}
