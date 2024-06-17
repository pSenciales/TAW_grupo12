package es.uma.taw_grupo12.controller;

import es.uma.taw_grupo12.dto.EjercicioDTO;
import es.uma.taw_grupo12.dto.EjercicioRutinaDTO;
import es.uma.taw_grupo12.dto.RutinaDTO;
import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.service.EjercicioRutinaService;
import es.uma.taw_grupo12.service.EjercicioService;
import es.uma.taw_grupo12.service.RutinaService;
import es.uma.taw_grupo12.service.TrabajadorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorController {


    @Autowired
    private EjercicioService ejercicioService;
    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private EjercicioRutinaService ejercicioRutinaService;
    @Autowired
    private TrabajadorService trabajadorService;


    @GetMapping("/")
    public String perfil(HttpSession sesion, Model model) {
          if(sesion.getAttribute("id") == null ||
                !sesion.getAttribute("tipo").equals("entrenador")){
            return "redirect :/";
        }else{
              TrabajadorDTO trabajador = trabajadorService.findById((Integer) sesion.getAttribute("id"));
              model.addAttribute("trabajador", trabajador);
              return "/Entrenador/perfilEntrenador";
          }

    }
    @GetMapping("/new-rutina")
    public String doCreacion(HttpSession sesion){
      /*  if(sesion.getAttribute("id") == null ||
                sesion.getAttribute("tipo").equals("entrenador")){
            return "redirect :/";
        }*/
        return "/Entrenador/crearRutina";
    }

    @PostMapping("/new-rutina/crear")
    public String doCrearRutina(HttpSession sesion, @RequestParam("nombre") String nombre){
        RutinaDTO rutinaDTO = new RutinaDTO(nombre);

        RutinaDTO recuperada = rutinaService.save(rutinaDTO);

        return "redirect:/entrenador/rutina/"+recuperada.getIdrutina();
    }




    @GetMapping("/rutina/{id}")
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

        List<EjercicioRutinaDTO> ejerciciosRutina = ejercicioRutinaService.findAllByRutinaId(id);
        model.addAttribute("ejerciciosRutina", ejerciciosRutina);

        return "/Entrenador/tableRutinas";
    }

    @PostMapping("/addEjercicio")
    public String doAddEjercicio(HttpSession sesion, @ModelAttribute EjercicioRutinaDTO ejercicioRutinaDTO){
        ejercicioRutinaService.save(ejercicioRutinaDTO);

        return "redirect:/entrenador/rutina/"+ejercicioRutinaDTO.getRutina();
    }

    @PostMapping("/rutina/subir")
    public String doSubirEjercicio(HttpSession sesion, @RequestParam("id") Integer id){

        //true significa que lo subirá
        ejercicioRutinaService.cambiarOrden(id, true);
        EjercicioRutinaDTO ejercicioRutinaDTO = ejercicioRutinaService.findById(id);
        return "redirect:/entrenador/rutina/"+ejercicioRutinaDTO.getRutina();
    }

    @PostMapping("/rutina/bajar")
    public String doBajarEjercicio(HttpSession sesion, @RequestParam("id") Integer id){
        ejercicioRutinaService.cambiarOrden(id, false);
        EjercicioRutinaDTO ejercicioRutinaDTO = ejercicioRutinaService.findById(id);
        return "redirect:/entrenador/rutina/"+ejercicioRutinaDTO.getRutina();
    }

    @GetMapping("/rutina/borrar")
    public String doBorrarEjercicio(HttpSession sesion, @RequestParam("id") Integer id){
        EjercicioRutinaDTO ejercicioRutinaDTO = ejercicioRutinaService.findById(id);
        ejercicioRutinaService.deleteById(id);
        return "redirect:/entrenador/rutina/"+ejercicioRutinaDTO.getRutina();
    }

    @PostMapping("/rutina/editar")
    public String doEditarEjercicio(HttpSession sesion, @RequestParam("id") Integer id){
        return "redirect:/entrenador/rutina/"+id;
    }
}
