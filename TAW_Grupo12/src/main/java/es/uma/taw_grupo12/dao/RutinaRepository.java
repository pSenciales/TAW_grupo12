package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina, Integer> {

    @Query("select r from Rutina r where r.idtrabajador.idtrabajador = ?1")
    List<Rutina> findAllByTrabajador(Integer idtrabajador);
}
