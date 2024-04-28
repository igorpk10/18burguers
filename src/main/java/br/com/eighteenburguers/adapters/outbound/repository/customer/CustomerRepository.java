package br.com.eighteenburguers.adapters.outbound.repository.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    
    Optional<CustomerEntity> findByDocumentNumber(String documentNumber);
}
