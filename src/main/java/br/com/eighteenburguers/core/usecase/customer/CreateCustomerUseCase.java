package br.com.eighteenburguers.core.usecase.customer;

import java.util.Optional;

import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.CreateCustomerUseCasePort;
import br.com.eighteenburguers.core.ports.outbound.FindCustomerAdapterPort;
import br.com.eighteenburguers.core.ports.outbound.SaveCustomerAdapterPort;

public class CreateCustomerUseCase implements CreateCustomerUseCasePort {

    private final FindCustomerAdapterPort findCustomerAdapterPort;
    private final SaveCustomerAdapterPort saveCustomerAdapterPort;

    public CreateCustomerUseCase(FindCustomerAdapterPort findCustomerAdapterPort,
            SaveCustomerAdapterPort saveCustomerAdapterPort) {
        this.findCustomerAdapterPort = findCustomerAdapterPort;
        this.saveCustomerAdapterPort = saveCustomerAdapterPort;
    }

    @Override
    public Customer execute(Customer customer) throws BusinessException {
        Optional<Customer> optional = findCustomerAdapterPort.findByDocumentNumber(customer.getDocument().getNumber());

        if (optional.isPresent()) {
            throw new CustomerAlreadyExistsException();
        }

        return saveCustomerAdapterPort.save(customer);
    }

}
