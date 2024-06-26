/**
 * @author María Victoria Huesca Peláez
 */

package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlatoRepository extends JpaRepository<Plato, Integer> {

    @Query("SELECT p FROM Plato p WHERE p.nombre LIKE %:busqueda%")
    public List<Plato> findAllByNombre(@Param("busqueda" )String busqueda);

    @Query("SELECT p FROM Plato p WHERE p.nombre = :nombre")
    List<Plato> findByNombre(@Param("nombre") String nombre);

}
