package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RutinaRepository extends JpaRepository<Rutina, Integer> {

    @Query("select r from Rutina r where r.idcliente.idcliente = ?1 and r.nombre = ?2")
    Rutina findByClienteAndName(Integer idcliente, String nombre);
}
