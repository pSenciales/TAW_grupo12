package es.uma.taw_grupo12.repository;

import es.uma.taw_grupo12.entity.EjercicioRutina;
import es.uma.taw_grupo12.entity.EjercicioRutinaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EjercicioRutinaRepository extends JpaRepository<EjercicioRutina, EjercicioRutinaPK> {
    @Query("select er.ejercicioRutinaPK.idejerciciorutina from EjercicioRutina er order by er.ejercicioRutinaPK.idejerciciorutina")
    public int findOrderedById();

    @Query("select er.orden from EjercicioRutina er order by er.orden desc")
    public String findOrden();
}
