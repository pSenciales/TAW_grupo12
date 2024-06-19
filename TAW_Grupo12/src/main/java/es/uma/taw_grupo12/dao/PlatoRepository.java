package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Plato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatoRepository extends JpaRepository<Plato, Integer> {
}
