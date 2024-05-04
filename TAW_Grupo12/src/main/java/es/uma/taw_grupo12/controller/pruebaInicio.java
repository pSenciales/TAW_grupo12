package es.uma.taw_grupo12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pruebaInicio {

    @GetMapping("/")
    public String doInicio() {
        return "oculto";
    }

}
