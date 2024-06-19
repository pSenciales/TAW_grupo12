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

    //Pagina de Inicio
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

    //Página y lista de Rutinas
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

    //Formulario para crear una Rutina nueva
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

    //Método post (guardar) de la Rutina nueva
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

    //Borrar una rutina (idrutina)
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

    //Aplicar filtro (nombre de la Rutina y Cliente)
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

    //Mostrar una Rutina (idrutina)
    @GetMapping("/mostrar/{idrutina}")
    public String doTabla(HttpSession sesion, Model model, @PathVariable("idrutina") Integer idrutina) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            List<EjercicioDTO> ejercicioList = ejercicioService.getFuerza();
            model.addAttribute("ejercicioList", ejercicioList);

            RutinaDTO rutinaDTO = rutinaService.findById(idrutina);
            model.addAttribute("rutina", rutinaDTO);

            EjercicioRutinaDTO ejercicioRutinaDTO = new EjercicioRutinaDTO();
            ejercicioRutinaDTO.setRutina(idrutina);
            model.addAttribute("ejercicioRutinaDTO", ejercicioRutinaDTO);

            List<EjercicioRutinaDTO> ejerciciosRutina = ejercicioRutinaService.findAllByRutinaId(idrutina);
            model.addAttribute("ejerciciosRutina", ejerciciosRutina);

            return "/EntrenadorCrosstraining/tableRutinas";

        }

    }

    //Añadir/Guardar un Ejercicio en una Rutina (se crea la entidad-relación EjercicioRutina)
    @PostMapping("/addEjercicio")
    public String doGuardarEjercicio(HttpSession sesion, @ModelAttribute EjercicioRutinaDTO ejercicioRutinaDTO) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            ejercicioRutinaService.save(ejercicioRutinaDTO);

            return "redirect:/entrenadorcross/mostrar/" + ejercicioRutinaDTO.getRutina();

        }

    }

    //(Subir) Disminuir orden del EjercicioRutina
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

    //(Bajar) Aumentar orden del EjercicioRutina
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

    //Borrar Ejercicio de una Rutina (eliminamos la relación EjercicioRutina)
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
