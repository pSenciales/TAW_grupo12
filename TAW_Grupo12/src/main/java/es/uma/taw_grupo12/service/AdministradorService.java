package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.AdministradorRepository;
import es.uma.taw_grupo12.dao.ClienteRepository;
import es.uma.taw_grupo12.dao.TrabajadorRepository;
import es.uma.taw_grupo12.dto.AdministradorDTO;
import es.uma.taw_grupo12.dto.ClienteDTO;
import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.dto.UsuarioDTO;
import es.uma.taw_grupo12.entity.Administrador;

import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.Trabajador;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

@Service
public class AdministradorService {

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

        Cliente cliente  = this.clienteRepository.findByEmail(clienteDTO.getEmail()).orElse(null);

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
}

