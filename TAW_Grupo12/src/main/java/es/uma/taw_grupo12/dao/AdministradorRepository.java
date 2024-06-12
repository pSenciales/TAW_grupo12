package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer>{
    @Query("select a from Administrador a where a.email = :email and a.contrasenya = :contrasenya")
    public Administrador autentica (@Param("email") String email, @Param("contrasenya")String contrasenya);
}