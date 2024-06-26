/**
 * @author LONGXIANG CHEN CHEN	 (3/5)
 * @author Ignacio Morillas Rosell (2/5)
 */

package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DietaRepository extends JpaRepository<Dieta, Integer> {
    @Query("select d from Dieta d where d.idcliente = ?1")
    Optional<Dieta> findByClienteId(Integer id);

    @Query("select d from Dieta d where d.idcliente.idcliente = ?1")
    List<Dieta> findAllByClienteId(Integer id);

    @Query("select d from Dieta d where d.nombre like concat('%',?1,'%') and d.idcliente.idcliente = ?2")
    List<Dieta> findByNombreAndClienteID(String nombre, Integer idCliente);

    @Query("select d from Dieta d where d.nombre = ?1")
    Optional<Dieta> findByNombre(String nombre);

    @Query("SELECT d FROM Dieta d WHERE d.nombre LIKE %:nombre%")
    List<Dieta> findByName(@Param("nombre") String nombre);
}
