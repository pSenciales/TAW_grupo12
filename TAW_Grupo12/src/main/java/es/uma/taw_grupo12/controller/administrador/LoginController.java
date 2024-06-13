package es.uma.taw_grupo12.controller.administrador;

import es.uma.taw_grupo12.controller.BaseController;
import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.dto.UsuarioDTO;
import es.uma.taw_grupo12.dto.ClienteDTO;
import es.uma.taw_grupo12.dto.AdministradorDTO;
import es.uma.taw_grupo12.service.AdministradorService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/")
    public String doLogin(HttpSession session, Model model) {
        String strTo = "/Administrador/login";
        if (estaAutenticado(session)) {
            String tipoUsuario = (String) session.getAttribute("tipo");
            strTo = "redirect:/" + tipoUsuario + "/inicio";
        } else {
            model.addAttribute("usuario", new UsuarioDTO());
        }
        return strTo;
    }

    @PostMapping("/autenticar")
    public String doAutenticar(@ModelAttribute("usuario") UsuarioDTO usuario,
                               Model model,
                               HttpSession session) {

        ClienteDTO cliente = this.administradorService.autenticarCliente(usuario.getEmail(), usuario.getContrasenya());
        TrabajadorDTO trabajador = this.administradorService.autenticarTrabajador(usuario.getEmail(), usuario.getContrasenya());
        AdministradorDTO administrador = this.administradorService.autenticarAdministrador(usuario.getEmail(), usuario.getContrasenya());

        String strTo = "redirect:/";

        if(cliente == null && administrador == null && trabajador == null) {
            model.addAttribute("errorLogin", "Usuario o contraseña incorrectos");
            strTo = this.doLogin(session, model);

        } else {

            if(cliente != null) {
                session.setAttribute("usuario", cliente);
                session.setAttribute("tipo", "cliente");
                strTo =  "redirect:/Cliente/inicio";

            } else if(trabajador != null) {
                session.setAttribute("usuario", trabajador);
                session.setAttribute("tipo", trabajador.getTipo());
                strTo = "redirect:/Trabajador/inicio";

            } else {
                session.setAttribute("usuario", administrador);
                session.setAttribute("tipo", "administrador");
                return "redirect:/Administrador/inicio";
            }
        }
        return strTo;
    }

    @PostMapping("/elegirRegistro")
    public String doElegirRegistro(HttpSession session,
                                   Model model) {
        return "/Administrador/registroTipoUsuario";
    }

    @PostMapping("/registro")
    public String doRegistro(@RequestParam("tipo") String tipo,
                             Model model) {

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

    @PostMapping("/guardarAdministrador")
    public String doRegistroAdministrador(@ModelAttribute("administrador") AdministradorDTO administrador,
                                          HttpSession session,
                                          Model model) {

        AdministradorDTO administradorDTO = this.administradorService.registrarAdministrador(administrador);
        String strTo = "";
        if(administradorDTO != null) {
            session.setAttribute("usuario", administradorDTO);
            session.setAttribute("tipo", "administrador");
            strTo = "redirect:/Administrador/inicio";
        } else {
            model.addAttribute("errorRegistro", "Ya existe un administrador con ese email");
            strTo = "/Administrador/registroAdministrador";
        }
        return strTo;
    }

    @PostMapping("/guardarCliente")
    public String doRegistroCliente(@ModelAttribute("cliente") ClienteDTO cliente,
                                          HttpSession session,
                                          Model model) throws IOException {

        ClienteDTO clienteDTO = this.administradorService.registrarCliente(cliente);
        String strTo = "";
        if(clienteDTO != null) {
            session.setAttribute("usuario", clienteDTO);
            session.setAttribute("tipo", "cliente");
            strTo = "redirect:/Administrador/inicio";
        } else {
            model.addAttribute("errorRegistro", "Ya existe un cliente con ese email");
            strTo = this.doRegistro("cliente", model);
        }
        return strTo;
    }

    @GetMapping("/cerrarSesion")
    public String doCerrarSesion (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
