package es.uma.taw_grupo12.controller;

import es.uma.taw_grupo12.dto.EjercicioDTO;
import es.uma.taw_grupo12.dto.RutinaDTO;
import es.uma.taw_grupo12.service.EjercicioService;
import es.uma.taw_grupo12.service.RutinaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EntrenadorController {


    @Autowired
    private EjercicioService ejercicioService;
    @Autowired
    private RutinaService rutinaService;

    @GetMapping("/entrenador")
    public String doTabla(HttpSession sesion, Model model){
      /*  if(sesion.getAttribute("id") == null ||
                sesion.getAttribute("tipo").equals("entrenador")){
            return "redirect :/";
        }*/

        List<EjercicioDTO> ejercicioList = ejercicioService.getFuerza();
        model.addAttribute("ejercicioList", ejercicioList);
        RutinaDTO rutinaDTO = rutinaService.findById(1);

        return "/Entrenador/tableRutinas";

    }
}
