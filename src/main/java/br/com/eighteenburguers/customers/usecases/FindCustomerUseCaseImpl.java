package br.com.eighteenburguers.customers.usecases;

import br.com.eighteenburguers.customers.entitys.Customer;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.customers.services.FindCustomerByDocumentService;
import br.com.eighteenburguers.customers.exceptions.CustomerNotFound;

public class FindCustomerUseCaseImpl implements FindCustomerUseCase {

    private FindCustomerByDocumentService findCustomerByDocumentService;

    public FindCustomerUseCaseImpl(FindCustomerByDocumentService findCustomerByDocumentService){
        this.findCustomerByDocumentService = findCustomerByDocumentService;
    }

    @Override
    public Customer execute(String id) throws BusinessException {
        var customer = findCustomerByDocumentService.findByDocumentNumber(id);

        if(customer == null){
            throw new CustomerNotFound();
        }

        return findCustomerByDocumentService.findByDocumentNumber(id);
    }
}