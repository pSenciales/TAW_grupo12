/**
 * @author María Victoria Huesca Peláez
 * @author Pablo Senciales
 */

package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {

    //@Pablo
    @Query("select e from Ejercicio e where e.tipo = 'FUERZA'")
    List<Ejercicio> getFuerza();
    //@Pablo

    //@Victoria
    @Query("SELECT e FROM Ejercicio e WHERE e.nombre LIKE %:busqueda%")
    List<Ejercicio> findAllByNombre(@Param("busqueda") String busqueda);

    @Query("SELECT e FROM Ejercicio e WHERE e.nombre = :nombre")
    Optional<Ejercicio> findByNombre(@Param("nombre") String nombre);
    //@Victoria
}
