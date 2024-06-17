package es.uma.taw_grupo12.controller;

import es.uma.taw_grupo12.dto.*;
import es.uma.taw_grupo12.entity.Trabajador;
import es.uma.taw_grupo12.service.*;
import es.uma.taw_grupo12.dto.EjercicioDTO;
import es.uma.taw_grupo12.dto.RutinaDTO;
import es.uma.taw_grupo12.service.EjercicioService;
import es.uma.taw_grupo12.service.RutinaService;
import es.uma.taw_grupo12.dto.*;
import es.uma.taw_grupo12.entity.Rutina;
import es.uma.taw_grupo12.entity.Trabajador;
import es.uma.taw_grupo12.service.*;
import es.uma.taw_grupo12.ui.Entrenador.FiltroRutinas;

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
    @Autowired
    private ClienteService clienteService;


    @GetMapping("/")
    public String perfil(HttpSession sesion, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            model.addAttribute("trabajador", trabajador);
            return "/Entrenador/perfilEntrenador";
        }

    }

    //Listar las rutinas
    @GetMapping("/rutinas")
    public String doListarRutinas(HttpSession sesion, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            List<RutinaDTO> rutinas = rutinaService.findAllByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("rutinas", rutinas);
            return "/Entrenador/listarRutinas";
        }
    }

    @GetMapping("/new-rutina")
    public String doCreacion(HttpSession sesion, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("clientes", clientes);
            return "/Entrenador/crearRutina";
        }
    }

    @PostMapping("/new-rutina/crear")
    public String doCrearRutina(HttpSession sesion, @RequestParam("nombre") String nombre, @RequestParam("cliente") Integer idcliente) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            RutinaDTO rutinaDTO = new RutinaDTO(nombre, trabajador.getIdtrabajador(), idcliente);

            rutinaService.save(rutinaDTO);

            return "redirect:/entrenador/rutinas";

        }
    }

    @GetMapping("/borrar/{id}")
    public String borrarRutina(HttpSession sesion, @PathVariable int id) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {

            rutinaService.deleteById(id);

            return "redirect:/entrenador/rutinas";

        }
    }












    /*
    *
    * VISUALIZAR RUTINAS
    * AÑADIR EJERCICIOS
    *
     * */


    @GetMapping("/visualizar/{id}")
    public String doTabla(HttpSession sesion, Model model, @PathVariable Integer id) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            List<EjercicioDTO> ejercicioList = ejercicioService.getFuerza();
            model.addAttribute("ejercicioList", ejercicioList);

            RutinaDTO rutinaDTO = rutinaService.findById(id);
            model.addAttribute("rutina", rutinaDTO);

            EjercicioRutinaDTO ejercicioRutinaDTO = new EjercicioRutinaDTO();
            ejercicioRutinaDTO.setRutina(id);
            model.addAttribute("ejercicioRutinaDTO", ejercicioRutinaDTO);

            List<EjercicioRutinaDTO> ejerciciosRutina = ejercicioRutinaService.findAllByRutinaId(id);
            model.addAttribute("ejerciciosRutina", ejerciciosRutina);

            return "/Entrenador/tableRutinas";

        }

    }


    @PostMapping("/addEjercicio")
    public String doAddEjercicio(HttpSession sesion, @ModelAttribute EjercicioRutinaDTO ejercicioRutinaDTO) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            ejercicioRutinaService.save(ejercicioRutinaDTO);

            return "redirect:/entrenador/visualizar/" + ejercicioRutinaDTO.getRutina();

        }

    }

    @PostMapping("/rutina/subir")
    public String doSubirEjercicio(HttpSession sesion, @RequestParam("id") Integer id) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            //true significa que lo subirá
            ejercicioRutinaService.cambiarOrden(id, true);
            EjercicioRutinaDTO ejercicioRutinaDTO = ejercicioRutinaService.findById(id);
            return "redirect:/entrenador/visualizar/" + ejercicioRutinaDTO.getRutina();

        }
    }

    @PostMapping("/rutina/bajar")
    public String doBajarEjercicio(HttpSession sesion, @RequestParam("id") Integer id) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            ejercicioRutinaService.cambiarOrden(id, false);
            EjercicioRutinaDTO ejercicioRutinaDTO = ejercicioRutinaService.findById(id);
            return "redirect:/entrenador/visualizar/" + ejercicioRutinaDTO.getRutina();

        }
    }

    @GetMapping("/rutina/borrar")
    public String doBorrarEjercicio(HttpSession sesion, @RequestParam("id") Integer id) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            EjercicioRutinaDTO ejercicioRutinaDTO = ejercicioRutinaService.findById(id);
            ejercicioRutinaService.deleteById(id);
            return "redirect:/entrenador/visualizar/" + ejercicioRutinaDTO.getRutina();

        }
    }

    @PostMapping("/rutina/editar")
    public String doEditarEjercicio(HttpSession sesion, @RequestParam("id") Integer id) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            return "redirect:/entrenador/visualizar/" + id;

        }
    }
}
