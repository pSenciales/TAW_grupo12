/**
 * @author María Victoria Huesca Peláez
 */

package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.AdministradorRepository;
import es.uma.taw_grupo12.dao.ClienteRepository;
import es.uma.taw_grupo12.dao.TrabajadorRepository;
import es.uma.taw_grupo12.dto.AdministradorDTO;
import es.uma.taw_grupo12.dto.ClienteDTO;
import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.entity.Administrador;
import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.Trabajador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class AdministradorService {
    //@Victoria
    @Autowired
    protected AdministradorRepository administradorRepository;

    @Autowired
    protected TrabajadorRepository trabajadorRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    public ClienteDTO autenticarCliente(String email, String contrasenya) {
        Cliente cliente = this.clienteRepository.findByEmailAndContrasenya(email, contrasenya).orElse(null);
        if (cliente != null) {
            return cliente.toDTO();
        } else {
            return null;
        }
    }

    public TrabajadorDTO autenticarTrabajador(String email, String contrasenya) {
        Trabajador trabajador = this.trabajadorRepository.findByEmailAndContrasenya(email, contrasenya).orElse(null);
        if (trabajador != null) {
            return trabajador.toDTO();
        } else {
            return null;
        }
    }

    public AdministradorDTO autenticarAdministrador(String email, String contrasenya) {
        Administrador administrador = this.administradorRepository.findByEmailAndContrasenya(email, contrasenya).orElse(null);
        if (administrador != null) {
            return administrador.toDTO();
        } else {
            return null;
        }
    }

    public AdministradorDTO registrarAdministrador(AdministradorDTO administrador) {

        Administrador admin = this.administradorRepository.findByEmail(administrador.getEmail()).orElse(null);

        if(admin == null) {
            Administrador nuevoAdmin = new Administrador();
            nuevoAdmin.setEmail(administrador.getEmail());
            nuevoAdmin.setContrasenya(administrador.getContrasenya());
            this.administradorRepository.save(nuevoAdmin);
            return nuevoAdmin.toDTO();
        } else {
            return null;
        }
    }

    public ClienteDTO registrarCliente(ClienteDTO clienteDTO) throws IOException {

        Cliente cliente  = this.clienteRepository.findByEmailorNombre(clienteDTO.getEmail(), clienteDTO.getNombre()).orElse(null);

        if(cliente == null){
            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setNombre(clienteDTO.getNombre());
            nuevoCliente.setEmail(clienteDTO.getEmail());
            nuevoCliente.setContrasenya(clienteDTO.getContrasenya());

            if (clienteDTO.getImagenperfilFile() != null && !clienteDTO.getImagenperfilFile().isEmpty()) {
                MultipartFile myFile = clienteDTO.getImagenperfilFile();
                byte[] imagenBytes = myFile.getBytes();
                nuevoCliente.setImagenperfil(imagenBytes);
            } else {
                nuevoCliente.setImagenperfil(null);
            }
            nuevoCliente.setPeso(clienteDTO.getPeso());
            nuevoCliente.setAltura(clienteDTO.getAltura());
            nuevoCliente.setAlergias(clienteDTO.getAlergias());

            this.clienteRepository.save(nuevoCliente);
            return nuevoCliente.toDTO();

        } else {
            return null;
        }
    }

    public TrabajadorDTO registrarTrabajador(TrabajadorDTO trabajadorDTO) throws IOException {

        Trabajador trabajador  = this.trabajadorRepository.findByEmailorNombre(trabajadorDTO.getEmail(), trabajadorDTO.getNombre()).orElse(null);

        if(trabajador == null){
            Trabajador nuevoTrabajador = new Trabajador();
            nuevoTrabajador.setNombre(trabajadorDTO.getNombre());
            nuevoTrabajador.setEmail(trabajadorDTO.getEmail());
            nuevoTrabajador.setContrasenya(trabajadorDTO.getContrasenya());
            nuevoTrabajador.setTipo(trabajadorDTO.getTipo());

            if (trabajadorDTO.getImagenperfilFile() != null && !trabajadorDTO.getImagenperfilFile().isEmpty()) {
                MultipartFile myFile = trabajadorDTO.getImagenperfilFile();
                byte[] imagenBytes = myFile.getBytes();
                nuevoTrabajador.setImagenperfil(imagenBytes);
            } else {
                nuevoTrabajador.setImagenperfil(null);
            }

            this.trabajadorRepository.save(nuevoTrabajador);
            return nuevoTrabajador.toDTO();

        } else {
            return null;
        }
    }

    public List<String> getTiposTrabajador() {
        return this.trabajadorRepository.findTipos();
    }

    public void asignarTrabajador(Integer idCliente, Integer idTrabajador) {
        Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
        Trabajador trabajador = this.trabajadorRepository.findById(idTrabajador).orElse(null);

        if(trabajador != null && cliente != null) {
            List<Trabajador> trabajadores = cliente.getTrabajadorList();

            // Busca si ya existe un Trabajador del mismo tipo asignado al Cliente
            Trabajador trabajadorExistente = trabajadores.stream()
                    .filter(t -> t.getTipo().equals(trabajador.getTipo()))
                    .findFirst()
                    .orElse(null);

            // Si existe, lo elimina de la lista
            if (trabajadorExistente != null) {
                trabajadores.remove(trabajadorExistente);
                List<Cliente> clientesExistente = trabajadorExistente.getClienteList();
                clientesExistente.remove(cliente);
                trabajadorRepository.save(trabajadorExistente);
            }

            // Añade el nuevo Trabajador a la lista
            trabajadores.add(trabajador);
            List<Cliente> clientes = trabajador.getClienteList();
            clientes.add(cliente);

            clienteRepository.save(cliente);
            trabajadorRepository.save(trabajador);
        }
    }

    public void desasignarTrabajador(Integer idCliente, Integer idTrabajador) {
        Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
        Trabajador trabajador = this.trabajadorRepository.findById(idTrabajador).orElse(null);

        if(trabajador != null && cliente != null) {
            List<Trabajador> trabajadores = cliente.getTrabajadorList();
            trabajadores.remove(trabajador);

            List<Cliente> clientes = trabajador.getClienteList();
            clientes.remove(cliente);

            clienteRepository.save(cliente);
            trabajadorRepository.save(trabajador);
        }
    }
    //@Victoria
}

