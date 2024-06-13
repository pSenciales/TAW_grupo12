package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {

    //@Pablo
    @Query("select e from Ejercicio e where e.tipo = 'FUERZA'")
    List<Ejercicio> getFuerza();
    //@Pablo

}
