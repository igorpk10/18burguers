package br.com.eighteenburguers.adapters.outbound.repository;

import java.util.Optional;

import br.com.eighteenburguers.adapters.outbound.repository.entity.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    
    Optional<CustomerEntity> findByDocumentNumber(String documentNumber);
}
