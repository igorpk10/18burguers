package br.com.eighteenburguers.customers.services;

import br.com.eighteenburguers.customers.entitys.Customer;

public interface FindCustomerByDocumentService {
    
    Customer findByDocumentNumber(String documentNumber);
}
