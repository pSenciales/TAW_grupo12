package es.uma.taw_grupo12.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntrenadorController {

    @GetMapping("/entrenador")
    public String doPrueba(HttpSession sesion){
      /*  if(sesion.getAttribute("id") == null ||
                sesion.getAttribute("tipo").equals("entrenador")){
            return "redirect :/";
        }*/

        return "/Entrenador/tableRutinas";

    }






}
