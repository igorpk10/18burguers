package br.com.eighteenburguers.customers.repositories;

import java.util.Optional;

import br.com.eighteenburguers.customers.entitys.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    
    Optional<CustomerEntity> findByDocumentNumber(String documentNumber);
}
