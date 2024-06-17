package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer>{

    //@Victoria
    @Query("SELECT c FROM Trabajador c WHERE c.email = :email AND c.contrasenya = :contrasenya")
    public Optional<Trabajador> findByEmailAndContrasenya(@Param("email")String email, @Param("contrasenya") String contrasenya);

    //devuelve una lista con los tipos de trabajadores
    @Query("SELECT DISTINCT c.tipo FROM Trabajador c")
    public List<String> findTipos();

    @Query("SELECT c FROM Trabajador c WHERE c.email = :email or c.nombre = :nombre")
    Optional<Trabajador> findByEmailorNombre(@Param("email") String email, @Param("nombre") String nombre);

    @Query("SELECT c FROM Trabajador c WHERE c.tipo = :tipo")
    List<Trabajador> findByTipo(@Param("tipo")String tipo);
    //@Victoria
}
