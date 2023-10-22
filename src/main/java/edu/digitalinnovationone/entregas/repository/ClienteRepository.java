package edu.digitalinnovationone.entregas.repository;

import edu.digitalinnovationone.entregas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
