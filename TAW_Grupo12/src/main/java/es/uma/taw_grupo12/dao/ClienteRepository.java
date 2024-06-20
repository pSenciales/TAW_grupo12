package es.uma.taw_grupo12.dao;

import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //@Victoria
    @Query("SELECT c FROM Cliente c WHERE c.email = :email AND c.contrasenya = :contrasenya")
    public Optional<Cliente> findByEmailAndContrasenya(@Param("email")String email, @Param("contrasenya") String contrasenya);

    @Query("SELECT c FROM Cliente c WHERE c.email = :email OR c.nombre = :nombre")
    public Optional<Cliente> findByEmailorNombre(@Param("email")String email, @Param("nombre") String nombre);

    @Query("SELECT DISTINCT c FROM Cliente c WHERE c.email LIKE %:busqueda% OR c.nombre LIKE %:busqueda%")
    public List<Cliente> findByEmailorNombre(@Param("busqueda") String busqueda);

    @Query("SELECT c FROM Cliente c WHERE c.email = :email OR c.nombre = :nombre and c.idcliente != :idcliente")
    public List<Cliente> findAllByEmailorNombre(@Param("email")String email, @Param("nombre") String nombre, @Param("idcliente") Integer idcliente);
    @Query(value = "select c.* from cliente c join cliente_trabajador ct on ct.idcliente = c.idcliente where ct.idtrabajador = ?1",
            nativeQuery = true)
    List<Cliente> findByTrabajador(Integer idtrabajador);

    @Query("SELECT c FROM Cliente c JOIN c.trabajadorList t WHERE t.idtrabajador = :idTrabajador")
    List<Cliente> findClientesAsociados(@Param("idTrabajador") Integer idTrabajador);
    //@Victoria
}
