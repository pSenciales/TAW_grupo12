package es.uma.taw_grupo12.repository;

import es.uma.taw_grupo12.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
