// @Author Pablo

package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.SeguimientoObjetivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface SeguimientoObjetivosRepository extends JpaRepository<SeguimientoObjetivos, Integer> {

    @Query("select s from SeguimientoObjetivos s where s.cliente.idcliente = ?1 and s.rutina.idrutina = ?2 order by s.fecha")
    List<SeguimientoObjetivos> findByClienteAndRutina(Integer idcliente, Integer idrutina);

    @Query("select s from SeguimientoObjetivos s where s.nombreejercicio like concat('%',?1,'%') and s.cliente.idcliente = ?2 and s.rutina.idrutina = ?3 order by s.fecha")
    List<SeguimientoObjetivos> findByNombreEjercicio(String nombre, Integer idcliente, Integer idrutina);
    @Query("select s from SeguimientoObjetivos s where s.nombreejercicio like concat('%',?1,'%') and s.cliente.idcliente = ?2 and s.rutina.idrutina = ?3 and s.fecha = ?4 order by s.fecha")
    List<SeguimientoObjetivos> findByNombreEjercicioAndFecha(String nombre, Integer idcliente, Integer idrutina, Date fecha);
}
