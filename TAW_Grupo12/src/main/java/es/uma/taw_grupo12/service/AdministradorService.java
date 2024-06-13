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

    /*
    public String autenticarUsuario(UsuarioDTO usuario, HttpSession session, Model model) {

        String email = usuario.getEmail();
        String contrasenya = usuario.getContrasenya();
        Cliente cliente = this.clienteRepository.findByEmailAndContrasenya(email, contrasenya).orElse(null);
        Trabajador trabajador = this.trabajadorRepository.findByEmailAndContrasenya(email, contrasenya).orElse(null);
        Administrador administrador = this.administradorRepository.findByEmailAndContrasenya(email, contrasenya).orElse(null);

        String strTo = "redirect:/";
        if (cliente == null && trabajador == null && administrador == null) {
            //OPCIÓN: MOSTRAR MENSAJES DIFERENTES
            model.addAttribute("errorLogin", "Usuario o contraseña incorrectos");
            strTo = "/Administrador/login";
        } else {

            if (cliente != null) {
                session.setAttribute("usuario", cliente);
                session.setAttribute("tipo", "cliente");
                strTo = "/Cliente/inicio";

            } else if (trabajador != null) {
                String tipoTrabajador = trabajador.getTipo();
                session.setAttribute("usuario", trabajador);
                session.setAttribute("tipo", tipoTrabajador);
                strTo = "/Trabajador/inicio";

            } else if(administrador != null) {
                session.setAttribute("usuario", administrador);
                session.setAttribute("tipo", "administrador");
                strTo = "/Administrador/inicio";
            }
        }

        return strTo;
    }
    */

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

    /*
    public String redirigirRegistro(String tipo,
                                    Model model){
        String strTo = "";

        if(tipo.equals("administrador")) {
            model.addAttribute("administrador", new AdministradorDTO());
            strTo = "/Administrador/registroAdministrador";

        } else if(tipo.equals("cliente")) {
            model.addAttribute("cliente", new ClienteDTO());
            strTo = "/Administrador/registroCliente";

        } else if(tipo.equals("trabajador")) {
            model.addAttribute("trabajador", new TrabajadorDTO());
            strTo = "/Administrador/registroTrabajador";
        }

        return strTo;
    }
    */

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
            if(clienteDTO.getImagenperfilFile() != null){
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

