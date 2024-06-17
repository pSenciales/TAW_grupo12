package es.uma.taw_grupo12.controller.cliente;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClienteController {

    @GetMapping("/redir/{url}")
    public String doClienteRedirect(@PathVariable String url) {
        return "Cliente/" + url;
    }
}
