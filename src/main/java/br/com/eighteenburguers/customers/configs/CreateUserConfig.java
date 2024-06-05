package br.com.eighteenburguers.customers.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.eighteenburguers.customers.services.FindCustomerByDocumentServiceImpl;
import br.com.eighteenburguers.customers.services.SaveCustomerServiceImpl;
import br.com.eighteenburguers.customers.usecases.CreateCustomerUseCaseImpl;

@Configuration
public class CreateUserConfig {

    @Bean
    public CreateCustomerUseCaseImpl createCustomerUseCase(FindCustomerByDocumentServiceImpl findCustomerAdapter, SaveCustomerServiceImpl saveCustomerAdapter){
        return new CreateCustomerUseCaseImpl(findCustomerAdapter , saveCustomerAdapter);
    }
    
}
