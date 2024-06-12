package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.AdministradorRepository;
import es.uma.taw_grupo12.dao.ClienteRepository;
import es.uma.taw_grupo12.dao.TrabajadorRepository;
import es.uma.taw_grupo12.dto.UsuarioDTO;
import es.uma.taw_grupo12.entity.Administrador;

import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.Trabajador;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdministradorService {

    @Autowired
    protected AdministradorRepository administradorRepository;

    @Autowired
    protected TrabajadorRepository trabajadorRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    public Administrador autenticarAdministrador (String email, String contrasenya) {
        Administrador admin = this.administradorRepository.autentica(email, contrasenya);
        if (admin != null) {
            return admin.toDTO();
        } else {
            return null;
        }
    }

    public String autenticarUsuario(UsuarioDTO usuario, HttpSession session, Model model) {

        String email = usuario.getEmail();
        String contrasenya = usuario.getContrasenya();
        Cliente cliente = this.clienteRepository.findByEmailAndContrasenya(email, contrasenya).orElse(null);
        Trabajador trabajador = this.trabajadorRepository.findByEmailAndContrasenya(email, contrasenya).orElse(null);
        Administrador administrador = this.administradorRepository.findByEmailAndContrasenya(email, contrasenya).orElse(null);

        if(cliente == null && trabajador == null && administrador == null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/";
        } else {
            if(cliente != null) {
                session.setAttribute("usuario", cliente);
                return "redirect:/cliente/inicio";
            } else if(trabajador != null) {
                String tipoTrabajador = trabajador.getTipo();

                session.setAttribute("usuario", trabajador);
                session.setAttribute("tipo", tipoTrabajador);
                return "redirect:/trabajador/" + tipoTrabajador + "/inicio";
            } else {
                session.setAttribute("usuario", administrador);
                return "redirect:/administrador/inicio";
            }
        }

    }
}
