package br.com.eighteenburguers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.eighteenburguers.core.ports.outbound.customer.FindCustomerByFederalIdOutputPort;
import br.com.eighteenburguers.core.usecase.customer.FindCustomerUseCase;

@Configuration
public class FindUserConfig {

    @Bean
    public FindCustomerUseCase findCustomerUseCase(FindCustomerByFederalIdOutputPort findByFederalIdCustomerAdapterPort){
        return new FindCustomerUseCase(findByFederalIdCustomerAdapterPort);
    }

}
