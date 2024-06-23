package es.uma.taw_grupo12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    @GetMapping("/testCliente")
    public String doInicio() {
        return "Cliente/pagPersonalCliente";
    }
}
