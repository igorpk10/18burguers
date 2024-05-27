package br.com.eighteenburguers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.eighteenburguers.adapters.outbound.FindCustomerAdapter;
import br.com.eighteenburguers.adapters.outbound.SaveCustomerAdapter;
import br.com.eighteenburguers.core.usecase.customer.CreateCustomerUseCase;

@Configuration
public class CreateUserConfig {

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(FindCustomerAdapter findCustomerAdapter, SaveCustomerAdapter saveCustomerAdapter){
        return new CreateCustomerUseCase(findCustomerAdapter , saveCustomerAdapter);
    }
    
}
