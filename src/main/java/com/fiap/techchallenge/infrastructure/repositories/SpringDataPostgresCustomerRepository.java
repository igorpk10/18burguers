package com.fiap.techchallenge.infrastructure.repositories;

import com.fiap.techchallenge.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPostgresCustomerRepository extends JpaRepository<Customer, Integer> {
}
