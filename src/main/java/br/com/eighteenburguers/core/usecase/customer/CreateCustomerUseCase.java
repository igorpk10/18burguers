package br.com.eighteenburguers.core.usecase.customer;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.customer.CreateCustomerUseCasePort;
import br.com.eighteenburguers.core.ports.outbound.customer.FindByFederalIdCustomerAdapterPort;
import br.com.eighteenburguers.core.ports.outbound.customer.SaveCustomerAdapterPort;
import br.com.eighteenburguers.core.usecase.customer.exceptions.CustomerAlreadyExistsException;

@Component
public class CreateCustomerUseCase implements CreateCustomerUseCasePort {

    private final FindByFederalIdCustomerAdapterPort findCustomerAdapterPort;
    private final SaveCustomerAdapterPort saveCustomerAdapterPort;

    public CreateCustomerUseCase(FindByFederalIdCustomerAdapterPort findCustomerAdapterPort,
            SaveCustomerAdapterPort saveCustomerAdapterPort) {
        this.findCustomerAdapterPort = findCustomerAdapterPort;
        this.saveCustomerAdapterPort = saveCustomerAdapterPort;
    }

    @Override
    public Customer execute(Customer customer) throws BusinessException {
        var createdCustomer = findCustomerAdapterPort.findByDocumentNumber(customer.getDocument().getNumber());

        if (createdCustomer != null) {
            throw new CustomerAlreadyExistsException();
        }

        return saveCustomerAdapterPort.save(createdCustomer);
    }

}
