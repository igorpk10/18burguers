package br.com.eighteenburguers.product.configs;

import br.com.eighteenburguers.product.services.FindProductByCategoryServiceImpl;
import br.com.eighteenburguers.product.usecase.FindProductByCategoryUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindProductByCategoryConfig {

    @Bean
    public FindProductByCategoryUseCaseImpl findProductByCategoryUseCase(FindProductByCategoryServiceImpl findProductByCategoryAdapter) {
        return new FindProductByCategoryUseCaseImpl(findProductByCategoryAdapter);
    }
}
