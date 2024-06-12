package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer>{
    @Query("SELECT c FROM Trabajador c WHERE c.email = :email AND c.contrasenya = :contrasenya")
    public Optional<Trabajador> findByEmailAndContrasenya(@Param("email")String email, @Param("contrasenya") String contrasenya);
}
