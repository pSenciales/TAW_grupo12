package es.uma.taw_grupo12.controller.administrador;

import es.uma.taw_grupo12.controller.BaseController;
import es.uma.taw_grupo12.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/")
    public String doLogin() {
        return "login";
    }

    @GetMapping("/")



}
