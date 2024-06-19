package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina, Integer> {

    @Query("select r from Rutina r where r.idtrabajador.idtrabajador = ?1")
    List<Rutina> findAllByTrabajador(Integer idtrabajador);

    @Query("select r from Rutina r where r.nombre like concat('%',?1,'%')")
    List<Rutina> findByFiltroNombre(String nombre);
    @Query("select r from Rutina r where r.nombre like concat('%',?1,'%')  and r.idcliente.idcliente = ?2")
    List<Rutina> findByFiltroNombreAndId(String nombre, String idcliente);

    @Query("select r from Rutina r where r.idtrabajador.idtrabajador = ?1 and r.idcliente.idcliente = ?2")
    List<Rutina> findAllByTrabajadorAndCliente(Integer idtrabajador, Integer idcliente);
}
