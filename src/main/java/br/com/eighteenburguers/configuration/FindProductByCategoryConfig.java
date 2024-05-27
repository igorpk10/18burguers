package br.com.eighteenburguers.configuration;

import br.com.eighteenburguers.adapters.outbound.FindProductByCategoryAdapter;
import br.com.eighteenburguers.core.usecase.product.FindProductByCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindProductByCategoryConfig {

    @Bean
    public FindProductByCategoryUseCase findProductByCategoryUseCase(FindProductByCategoryAdapter findProductByCategoryAdapter) {
        return new FindProductByCategoryUseCase(findProductByCategoryAdapter);
    }
}
