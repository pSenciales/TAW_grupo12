package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer>{
    @Query("select a from Administrador a where a.email = :email and a.contrasenya = :contrasenya")
    public Optional<Administrador> findByEmailAndContrasenya (@Param("email") String email, @Param("contrasenya")String contrasenya);

    @Query("select a from Administrador a where a.email = :email")
    public Optional<Administrador> findByEmail (@Param("email") String email);
}
