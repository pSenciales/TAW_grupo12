/**
 * @author Ignacio Morillas Rossell (2/5)
 * @author Chen Chen Longxiang (3/5)
 */
package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.SeguimientoDieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface SeguimientoDietaRepository extends JpaRepository<SeguimientoDieta, Integer> {
    @Query("select d from SeguimientoDieta d where d.dieta.iddieta = ?1")
    Optional<SeguimientoDieta> findByDietaId(Integer id);

    @Query("select d from SeguimientoDieta d where d.cliente.idcliente = ?1")
    Optional<SeguimientoDieta> findByClienteId(Integer id);

    @Query("select d from SeguimientoDieta d where d.cliente.idcliente = ?1 and d.dieta.iddieta = ?2")
    List<SeguimientoDieta> findByClienteIdAndDietaId(Integer idCliente, Integer idDieta);

    //Nacho
    @Query("select s from SeguimientoDieta s where s.nombreplato like concat('%',?1,'%') and s.cliente.idcliente = ?2 and s.dieta.iddieta = ?3 order by s.fecha")
    List<SeguimientoDieta> findByNombrePlato(String nombre, Integer idcliente, Integer idDieta);

    //Nacho
    @Query("select s from SeguimientoDieta s where s.nombreplato like concat('%',?1,'%') and s.cliente.idcliente = ?2 and s.dieta.iddieta = ?3 and s.fecha = ?4 order by s.fecha")
    List<SeguimientoDieta> findByNombrePlatoAndFecha(String nombre, Integer idcliente, Integer idDieta, Date fecha);
}
