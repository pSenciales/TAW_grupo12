package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT c FROM Cliente c WHERE c.email = :email AND c.contrasenya = :contrasenya")
    public Optional<Cliente> findByEmailAndContrasenya(@Param("email")String email, @Param("contrasenya") String contrasenya);

    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    public Optional<Cliente> findByEmail(@Param("email")String email);
}
