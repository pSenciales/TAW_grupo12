package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Dieta;
import es.uma.taw_grupo12.entity.SeguimientoDieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeguimientoDietaRepository extends JpaRepository<SeguimientoDieta, Integer> {
    @Query("select d from SeguimientoDieta d where d.dieta.iddieta = ?1")
    Optional<SeguimientoDieta> findByDietaId(Integer id);

    @Query("select d from SeguimientoDieta d where d.cliente.idcliente = ?1")
    Optional<SeguimientoDieta> findByClienteId(Integer id);

    @Query("select d from SeguimientoDieta d where d.cliente.idcliente = ?1 and d.dieta.iddieta = ?2")
    List<SeguimientoDieta> findByClienteIdAndDietaId(Integer idCliente, Integer idDieta);
}
