/**
 * @author María Victoria Huesca
 */

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            //strTo = "redirect:/" + tipoUsuario + "/inicio";
            strTo = "redirect:/administrador/inicio";
        } else {
            model.addAttribute("usuario", new UsuarioDTO());
        }
        return strTo;
    }

    @PostMapping("/autenticar")
    public String doAutenticar(@ModelAttribute("usuario") UsuarioDTO usuario,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {

        ClienteDTO cliente = this.administradorService.autenticarCliente(usuario.getEmail(), usuario.getContrasenya());
        TrabajadorDTO trabajador = this.administradorService.autenticarTrabajador(usuario.getEmail(), usuario.getContrasenya());
        AdministradorDTO administrador = this.administradorService.autenticarAdministrador(usuario.getEmail(), usuario.getContrasenya());

        String strTo = "redirect:/";

        if(cliente == null && administrador == null && trabajador == null) {
            //OPCIÓN: AÑADIR QUE SE MUESTREN DISTINTOS MENSAJES DE ERROR
            redirectAttributes.addFlashAttribute("errorLogin", "Email o contraseña incorrectos");
            return "redirect:/";

        } else {

            if(cliente != null) {
                session.setAttribute("usuario", cliente);
                session.setAttribute("tipo", "cliente");
                //strTo = "redirect:/cliente/inicio";          DESCOMENTAR CUANDO ESTE IMPLEMENTADO EL INICIO DE CLIENTE
                strTo =  "redirect:/administrador/inicio";

            } else if(trabajador != null) {
                session.setAttribute("usuario", trabajador);
                session.setAttribute("tipo", trabajador.getTipo());
                //strTo = "redirect:/" + trabajador.getTipo() + "/inicio";          DESCOMENTAR CUANDO ESTE IMPLEMENTADO EL INICIO DE TRABAJADOR
                strTo = "redirect:/administrador/inicio";

            } else {
                session.setAttribute("usuario", administrador);
                session.setAttribute("tipo", "administrador");

                return "redirect:/administrador/inicio";
            }
        }
        return strTo;
    }

    @PostMapping("/elegirRegistro")
    public String doElegirRegistro(HttpSession session,
                                   Model model) {
        return "/Administrador/registroTipoUsuario";
    }

    //ESte getMapping es para la redirección de la página de registro
    @GetMapping("/registro")
    public String mostrarRegistro(@RequestParam("tipo") String tipo, Model model) {
        String strTo = "";

        if(tipo.equals("administrador")) {
            model.addAttribute("administrador", new AdministradorDTO());
            strTo = "/Administrador/registroAdministrador";

        } else if(tipo.equals("cliente")) {
            model.addAttribute("cliente", new ClienteDTO());
            strTo = "/Administrador/registroCliente";

        } else if(tipo.equals("trabajador")) {
            model.addAttribute("trabajador", new TrabajadorDTO());
            model.addAttribute("tipos", this.administradorService.getTiposTrabajador() );
            strTo = "/Administrador/registroTrabajador";
        }

        return strTo;
    }

    @PostMapping("/guardarAdministrador")
    public String doRegistroAdministrador(@ModelAttribute("administrador") AdministradorDTO administrador,
                                          HttpSession session,
                                          RedirectAttributes redirectAttributes) throws IOException {

        AdministradorDTO administradorDTO = this.administradorService.registrarAdministrador(administrador);
        String strTo = "";
        if(administradorDTO != null) {
            session.setAttribute("usuario", administradorDTO);
            session.setAttribute("tipo", "administrador");
            strTo = "redirect:/administrador/inicio";
        } else {
            redirectAttributes.addFlashAttribute("errorRegistro", "Ya existe un administrador con ese email");
            return "redirect:/registro?tipo=administrador";
        }
        return strTo;
    }

    @PostMapping("/guardarCliente")
    public String doRegistroCliente(@ModelAttribute("cliente") ClienteDTO cliente,
                                          HttpSession session,
                                    RedirectAttributes redirectAttributes) throws IOException {

        ClienteDTO clienteDTO = this.administradorService.registrarCliente(cliente);
        String strTo = "";
        if(clienteDTO != null) {
            session.setAttribute("usuario", clienteDTO);
            session.setAttribute("tipo", "cliente");
            //strTo = "redirect:/Cliente/inicio";
            strTo = "redirect:/administrador/inicio";
        } else {
            //OPCIÓN: AÑADIR QUE SE MUESTREN DISTINTOS MENSAJES DE ERROR
            redirectAttributes.addFlashAttribute("errorRegistro", "Ya existe un cliente con ese email o nombre");
            return "redirect:/registro?tipo=cliente";
        }
        return strTo;
    }

    @PostMapping("/guardarTrabajador")
    public String doRegistroTrabajador(@ModelAttribute("trabajador") TrabajadorDTO trabajador,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes,
                                    Model model) throws IOException {

        TrabajadorDTO trabajadorDTO = this.administradorService.registrarTrabajador(trabajador);
        String strTo = "";
        if(trabajadorDTO != null) {
            session.setAttribute("usuario", trabajadorDTO);
            session.setAttribute("tipo", trabajadorDTO.getTipo());
            //strTo = "redirect:/trabajador/inicio";       DESCOMENTAR CUANDO ESTE IMPLEMENTADO EL INICIO DE TRABAJADOR
            strTo = "redirect:/administrador/inicio";
        } else {
            //OPCIÓN: AÑADIR QUE SE MUESTREN DISTINTOS MENSAJES DE ERROR
            redirectAttributes.addFlashAttribute("errorRegistro", "Ya existe un trabajador con ese email o nombre");
            model.addAttribute("trabajador", new TrabajadorDTO());
            model.addAttribute("tipos", this.administradorService.getTiposTrabajador() );
            return "redirect:/registro?tipo=trabajador";
        }
        return strTo;
    }

    @GetMapping("/cerrarSesion")
    public String doCerrarSesion (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
