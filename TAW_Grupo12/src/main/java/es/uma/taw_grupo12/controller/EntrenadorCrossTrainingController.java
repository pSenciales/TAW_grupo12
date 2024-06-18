package es.uma.taw_grupo12.controller;

import es.uma.taw_grupo12.dto.*;
import es.uma.taw_grupo12.service.*;
import es.uma.taw_grupo12.ui.Entrenador.FiltroRutinas;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/entrenadorcross")
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

        if (trabajador == null || !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            model.addAttribute("trabajador", trabajador);
            return "/EntrenadorCrosstraining/inicioEntrenadorCrosstraining";
        }
    }

    @GetMapping("/rutinas")
    public String doListarRutinas(HttpSession sesion, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            List<RutinaDTO> rutinas = rutinaService.findAllByTrabajador(trabajador.getIdtrabajador());
            FiltroRutinas filtro = new FiltroRutinas();
            List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());

            model.addAttribute("rutinas", rutinas);
            model.addAttribute("filtro", filtro);
            model.addAttribute("clientes", clientes);
            return "/EntrenadorCrosstraining/listarRutina";
        }
    }


    @GetMapping("/nuevarutina")
    public String doCreacion(HttpSession sesion, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("clientes", clientes);
            return "/EntrenadorCrosstraining/crearRutina";
        }
    }

    @PostMapping("/nuevarutina/guardar")
    public String doGuardarRutina(HttpSession sesion, @RequestParam("nombre") String nombre, @RequestParam("cliente") Integer idcliente) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            RutinaDTO rutinaDTO = new RutinaDTO(nombre, trabajador.getIdtrabajador(), idcliente);

            rutinaService.save(rutinaDTO);

            return "redirect:/entrenadorcross/rutinas";

        }
    }

    @GetMapping("/borrar/{idrutina}")
    public String borrarRutina(HttpSession sesion, @PathVariable("idrutina") int idrutina) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {

            rutinaService.deleteById(idrutina);

            return "redirect:/entrenadorcross/rutinas";

        }
    }

    @PostMapping("/filtrar")
    public String doFiltrar(HttpSession sesion, Model model, @ModelAttribute("filtro") FiltroRutinas filtro) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            List<RutinaDTO> rutinas = rutinaService.findByFiltro(filtro);
            model.addAttribute("rutinas", rutinas);
            model.addAttribute("filtro", filtro);
            List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("clientes", clientes);
            return "/EntrenadorCrosstraining/listarRutinas";
        }

    }

    @GetMapping("/visualizar/{id}")
    public String doTabla(HttpSession sesion, Model model, @PathVariable Integer id) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
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

            return "/EntrenadorCrosstraining/tableRutinas";

        }

    }


}
