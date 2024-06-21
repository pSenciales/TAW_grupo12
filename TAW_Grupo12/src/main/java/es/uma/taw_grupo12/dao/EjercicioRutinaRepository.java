package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.EjercicioRutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EjercicioRutinaRepository extends JpaRepository<EjercicioRutina, Integer> {

    //@Pablo
    @Query("select er.ejercicioRutinaPK from EjercicioRutina er order by er.ejercicioRutinaPK")
    public int findOrderedById();

    @Query(value = "select er.orden from taw12.ejerciciorutina er where er.idrutina = ?1 and er.diassemana = ?2 order by er.orden desc limit 1", nativeQuery = true)
    public String findByOrden(Integer idRutina, String diassemana);

    @Query("select er from EjercicioRutina er where er.rutina.idrutina = ?1 order by er.orden, er.diassemana")
    List<EjercicioRutina> findAllByRutinaId(Integer id);


    @Query("select er from EjercicioRutina er where er.rutina.idrutina = ?1 and er.diassemana = ?2 and er.orden = ?3")
    Optional<EjercicioRutina> findSuperiorOrInferior(Integer idrutina, String diassemana, int i);

    @Query("select er from EjercicioRutina er where er.rutina.idrutina = ?1 and er.diassemana = ?2 and er.orden > ?3")
    List<EjercicioRutina> findMayoresOrden(int rutina, String diassemana, int orden);
    //@Pablo

    //@Victoria
    @Query("SELECT er FROM EjercicioRutina er WHERE er.ejercicio.idejercicio = :idEjercicio")
    List<EjercicioRutina> findEjerciciosRutina(@Param("idEjercicio")Integer idEjercicio);
    //@Victoria
}
