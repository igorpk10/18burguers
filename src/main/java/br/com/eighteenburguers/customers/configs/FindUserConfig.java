package br.com.eighteenburguers.customers.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.eighteenburguers.customers.services.FindCustomerByDocumentService;
import br.com.eighteenburguers.customers.usecases.FindCustomerUseCaseImpl;

@Configuration
public class FindUserConfig {

    @Bean
    public FindCustomerUseCaseImpl findCustomerUseCase(FindCustomerByDocumentService findByFederalIdCustomerAdapterPort){
        return new FindCustomerUseCaseImpl(findByFederalIdCustomerAdapterPort);
    }

}
