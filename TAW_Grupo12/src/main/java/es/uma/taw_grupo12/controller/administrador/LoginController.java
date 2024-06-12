package es.uma.taw_grupo12.controller.administrador;

import es.uma.taw_grupo12.controller.BaseController;
import es.uma.taw_grupo12.dto.UsuarioDTO;
import es.uma.taw_grupo12.entity.Administrador;
import es.uma.taw_grupo12.service.AdministradorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/")
    public String doLogin(HttpSession session, Model model) {
        String strTo = "/Administrador/login";
        if (estaAutenticado(session)) {
            strTo = "redirect:/inicio/";
        } else {
            model.addAttribute("usuario", new UsuarioDTO());
        }
        return strTo;
    }

    @PostMapping("/registro")
    public String doRegistro(HttpSession session, Model model) {
        String strTo = "/Administrador/registro";
        if (estaAutenticado(session)) {
            strTo = "redirect:/inicio/";
        } else {
            model.addAttribute("usuario", new UsuarioDTO());
        }
        return strTo;
    }

    @PostMapping("/autenticar")
    public String doAutenticar(@ModelAttribute("usuario") UsuarioDTO usuario,
                               Model model,
                               HttpSession session) {
        return administradorService.autenticarUsuario(usuario, session, model);
    }




}
