package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer>{
}
