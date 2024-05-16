package br.com.eighteenburguers.configuration;

import br.com.eighteenburguers.adapters.outbound.FindProductByIdAdapter;
import br.com.eighteenburguers.core.usecase.product.FindProductByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindProductByIdConfig {

    @Bean
    public FindProductByIdUseCase findProductByIdUseCase(FindProductByIdAdapter findProductByIdAdapter) {
        return new FindProductByIdUseCase(findProductByIdAdapter);
    }
}
