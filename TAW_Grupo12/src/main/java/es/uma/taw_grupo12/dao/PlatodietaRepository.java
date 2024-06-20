package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.PlatoDieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlatodietaRepository extends JpaRepository<PlatoDieta, Integer>{
    @Query("SELECT pd FROM PlatoDieta pd WHERE pd.plato.idplato = :idplato")
    List<PlatoDieta> findPlatosDieta(@Param("idplato") Integer idplato);
}
