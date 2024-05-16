package br.com.eighteenburguers.configuration;

import br.com.eighteenburguers.adapters.outbound.DeleteProductByIdAdapter;
import br.com.eighteenburguers.adapters.outbound.FindProductByIdAdapter;
import br.com.eighteenburguers.core.usecase.product.DeleteProductByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteProductByIdConfig {

    @Bean
    public DeleteProductByIdUseCase deleteProductByIdUseCase(DeleteProductByIdAdapter deleteProductByIdAdapter, FindProductByIdAdapter findProductByIdAdapter) {
        return new DeleteProductByIdUseCase(deleteProductByIdAdapter, findProductByIdAdapter);
    }
}
