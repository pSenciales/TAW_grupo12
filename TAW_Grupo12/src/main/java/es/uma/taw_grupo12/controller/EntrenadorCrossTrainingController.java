package es.uma.taw_grupo12.controller;

import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entrenadorcrosstraining")
public class EntrenadorCrossTrainingController {

    @Autowired
    EjercicioService ejercicioService;
    @Autowired
    RutinaService rutinaService;
    @Autowired
    EjercicioRutinaService ejercicioRutinaService;
    @Autowired
    TrabajadorService trabajadorService;
    @Autowired
    ClienteService clienteService;

    @GetMapping("/")
    public String perfil(HttpSession sesion, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");

        if (trabajador == null || !sesion.getAttribute("tipo").equals("ENTRENADOR CROSSTRAINING")) {
            return "redirect:/";
        } else {
            model.addAttribute("trabajador", trabajador);
            return "/Entrenador/inicioEntrenadorCrosstraining";
        }

    }


}
