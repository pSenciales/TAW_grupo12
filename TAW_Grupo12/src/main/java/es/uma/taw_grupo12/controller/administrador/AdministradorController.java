/**
 * @author María Victoria Huesca Peláez
 */

package es.uma.taw_grupo12.controller.administrador;

import es.uma.taw_grupo12.controller.BaseController;

import es.uma.taw_grupo12.dto.AdministradorDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/administrador")
public class AdministradorController extends BaseController{

    @GetMapping("/inicio")
    public String doInicio(HttpSession session, Model model){
        String strTo = "/Administrador/inicioAdministrador";

        if (!estaAutenticado(session) || session.getAttribute("tipo") != "administrador"){
            strTo = "redirect:/";
        } else {
            AdministradorDTO miAdmin = (AdministradorDTO) session.getAttribute("usuario");
            model.addAttribute("correoAdministrador", miAdmin.getEmail() );
            model.addAttribute("tituloCabeceraAdmin", "");
        }

        return strTo;
    }

    @PostMapping("/verPerfil")
    public String doVerPerfil(HttpSession session){
        String strTo = "/Administrador/perfilAdministrador";

        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        }

        return strTo;
    }
}
