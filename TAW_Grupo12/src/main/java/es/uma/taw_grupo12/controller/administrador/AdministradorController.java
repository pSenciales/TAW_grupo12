package es.uma.taw_grupo12.controller.administrador;

import es.uma.taw_grupo12.controller.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/administrador")
public class AdministradorController extends BaseController{

    @GetMapping("/inicio")
    public String doInicio(HttpSession session){
        String strTo = "/Administrador/inicioAdministrador";

        if (!estaAutenticado(session)) {
            strTo = "redirect:/";
        }

        return strTo;
    }
}
