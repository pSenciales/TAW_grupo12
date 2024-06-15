package es.uma.taw_grupo12.controller;

import es.uma.taw_grupo12.dto.EjercicioDTO;
import es.uma.taw_grupo12.dto.EjercicioRutinaDTO;
import es.uma.taw_grupo12.dto.RutinaDTO;
import es.uma.taw_grupo12.service.EjercicioRutinaService;
import es.uma.taw_grupo12.service.EjercicioService;
import es.uma.taw_grupo12.service.RutinaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EntrenadorController {


    @Autowired
    private EjercicioService ejercicioService;
    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private EjercicioRutinaService ejercicioRutinaService;


    @GetMapping("/entrenador/new-rutina")
    public String doCreacion(HttpSession sesion){
      /*  if(sesion.getAttribute("id") == null ||
                sesion.getAttribute("tipo").equals("entrenador")){
            return "redirect :/";
        }*/
        return "/Entrenador/crearRutina";
    }

    @PostMapping("/entrenador/new-rutina/crear")
    public String doCrearRutina(HttpSession sesion, @RequestParam("nombre") String nombre){
        RutinaDTO rutinaDTO = new RutinaDTO(nombre);

        RutinaDTO recuperada = rutinaService.save(rutinaDTO);

        return "redirect:/entrenador/rutina/"+recuperada.getIdrutina();
    }




    @GetMapping("/entrenador/rutina/{id}")
    public String doTabla(HttpSession sesion, Model model, @PathVariable Integer id){
      /*  if(sesion.getAttribute("id") == null ||
                sesion.getAttribute("tipo").equals("entrenador")){
            return "redirect :/";
        }*/

        List<EjercicioDTO> ejercicioList = ejercicioService.getFuerza();
        model.addAttribute("ejercicioList", ejercicioList);
        RutinaDTO rutinaDTO = rutinaService.findById(id);
        model.addAttribute("rutina",rutinaDTO);
        EjercicioRutinaDTO ejercicioRutinaDTO = new EjercicioRutinaDTO();
        ejercicioRutinaDTO.setRutina(id);
        model.addAttribute("ejercicioRutinaDTO", ejercicioRutinaDTO);
        return "/Entrenador/tableRutinas";
    }

    @PostMapping("/addEjercicio")
    public String doAddEjercicio(HttpSession sesion, @ModelAttribute EjercicioRutinaDTO ejercicioRutinaDTO){
        ejercicioRutinaService.save(ejercicioRutinaDTO);

        return "redirect:entrenador/rutina/"+ejercicioRutinaDTO.getRutina();
    }






}
