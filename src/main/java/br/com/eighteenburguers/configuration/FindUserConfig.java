package br.com.eighteenburguers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.eighteenburguers.core.ports.outbound.customer.FindByFederalIdCustomerAdapterPort;
import br.com.eighteenburguers.core.usecase.customer.FindCustomerUseCase;

@Configuration
public class FindUserConfig {

    @Bean
    public FindCustomerUseCase findCustomerUseCase(FindByFederalIdCustomerAdapterPort findByFederalIdCustomerAdapterPort){
        return new FindCustomerUseCase(findByFederalIdCustomerAdapterPort);
    }

}
