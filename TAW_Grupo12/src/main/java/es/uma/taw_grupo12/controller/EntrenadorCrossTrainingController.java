package es.uma.taw_grupo12.controller;

import es.uma.taw_grupo12.dto.*;
import es.uma.taw_grupo12.service.*;
import es.uma.taw_grupo12.ui.Entrenador.FiltroRutinas;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
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
    @Autowired
    private SeguimientoObjetivosService seguimientoObjetivosService;

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
    public String doCrearRutina(HttpSession sesion, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("clientes", clientes);
            RutinaDTO rutina = new RutinaDTO();
            model.addAttribute("rutina", rutina);
            return "/EntrenadorCrosstraining/crearRutina";
        }

    }

    //Método post (guardar) de la Rutina nueva
    @PostMapping(value = {"/nuevarutina/guardar", "editar/nuevarutina/guardar"})
    public String doCrearRutina(HttpSession sesion, @ModelAttribute("rutina") RutinaDTO rutina) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            rutina.setIdtrabajador(trabajador.getIdtrabajador());
            rutinaService.save(rutina);

            return "redirect:/entrenadorcross/rutinas";

        }
    }

    //Editar una Rutina
    @GetMapping("/editar/{idrutina}")
    public String doEditarRutina(HttpSession sesion, @PathVariable("idrutina") Integer idrutina, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("clientes", clientes);
            RutinaDTO rutina = rutinaService.findById(idrutina);
            model.addAttribute("rutina", rutina);

            return "/EntrenadorCrosstraining/crearRutina";


        }
    }

    //Eliminar una rutina (idrutina)
    @GetMapping("/eliminar/{idrutina}")
    public String eliminarRutina(HttpSession sesion, @PathVariable("idrutina") int idrutina) {
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
            List<RutinaDTO> rutinas = rutinaService.findByFiltro(filtro, trabajador.getIdtrabajador());
            model.addAttribute("rutinas", rutinas);
            model.addAttribute("filtro", filtro);
            List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("clientes", clientes);
            return "/EntrenadorCrosstraining/listarRutina";
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
            List<EjercicioDTO> ejercicioList = ejercicioService.getAll();
            model.addAttribute("ejercicioList", ejercicioList);
            model.addAttribute("ejercicioListFiltrado", ejercicioList);

            List<String> tipos = ejercicioService.getTipos();
            model.addAttribute("tipos", tipos);

            RutinaDTO rutinaDTO = rutinaService.findById(idrutina);
            model.addAttribute("rutina", rutinaDTO);

            EjercicioRutinaDTO ejercicioRutinaDTO = new EjercicioRutinaDTO();
            ejercicioRutinaDTO.setRutina(idrutina);
            model.addAttribute("ejercicioRutinaDTO", ejercicioRutinaDTO);

            List<EjercicioRutinaDTO> ejerciciosRutina = ejercicioRutinaService.findAllByRutinaId(idrutina);
            model.addAttribute("ejerciciosRutina", ejerciciosRutina);

            return "/EntrenadorCrosstraining/tablaRutina";

        }

    }

    //Filtrar el tipo de Ejercicios
    @PostMapping("/filtrarTipoEjercicio/{idrutina}")
    public String doFiltrarTipoEjercicios(@PathVariable("idrutina") Integer idrutina, HttpSession sesion, @RequestParam("filtrotipos") List<String> filtrotipos, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            List<EjercicioDTO> ejercicioListFiltrado = ejercicioService.getByTipos(filtrotipos);
            model.addAttribute("ejercicioListFiltrado", ejercicioListFiltrado);

            List<EjercicioDTO> ejercicioList = ejercicioService.getAll();
            model.addAttribute("ejercicioList", ejercicioList);

            List<String> tipos = ejercicioService.getTipos();
            model.addAttribute("tipos", tipos);

            RutinaDTO rutinaDTO = rutinaService.findById(idrutina);
            model.addAttribute("rutina", rutinaDTO);

            EjercicioRutinaDTO ejercicioRutinaDTO = new EjercicioRutinaDTO();
            ejercicioRutinaDTO.setRutina(idrutina);
            model.addAttribute("ejercicioRutinaDTO", ejercicioRutinaDTO);

            List<EjercicioRutinaDTO> ejerciciosRutina = ejercicioRutinaService.findAllByRutinaId(idrutina);
            model.addAttribute("ejerciciosRutina", ejerciciosRutina);

            return "/EntrenadorCrosstraining/tablaRutina";

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
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            //true significa que lo subirá
            ejercicioRutinaService.cambiarOrden(id, true);
            EjercicioRutinaDTO ejercicioRutinaDTO = ejercicioRutinaService.findById(id);
            return "redirect:/entrenadorcross/mostrar/" + ejercicioRutinaDTO.getRutina();

        }
    }

    //(Bajar) Aumentar orden del EjercicioRutina
    @PostMapping("/rutina/bajar")
    public String doBajarEjercicio(HttpSession sesion, @RequestParam("id") Integer id) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            ejercicioRutinaService.cambiarOrden(id, false);
            EjercicioRutinaDTO ejercicioRutinaDTO = ejercicioRutinaService.findById(id);
            return "redirect:/entrenadorcross/mostrar/" + ejercicioRutinaDTO.getRutina();

        }
    }

    //Borrar Ejercicio de una Rutina (eliminamos la relación EjercicioRutina)
    @GetMapping("/rutina/borrar/{idEjercicioRutina}")
    public String doBorrarEjercicio(HttpSession sesion, @PathVariable("idEjercicioRutina") Integer idEjercicioRutina) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            EjercicioRutinaDTO ejercicioRutinaDTO = ejercicioRutinaService.findById(idEjercicioRutina);
            ejercicioRutinaService.deleteById(idEjercicioRutina);
            return "redirect:/entrenadorcross/mostrar/" + ejercicioRutinaDTO.getRutina();

        }
    }

    @GetMapping("/rutina/editar/{idEjercicioRutina}")
    public String doEditarEjercicio(HttpSession sesion, Model model, @PathVariable("idEjercicioRutina") Integer idEjercicioRutina) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            EjercicioRutinaDTO ejercicioRutinaDTO = ejercicioRutinaService.findById(idEjercicioRutina);
            model.addAttribute("ejercicioRutinaDTO", ejercicioRutinaDTO);

            List<EjercicioDTO> ejercicioList = ejercicioService.getAll();
            model.addAttribute("ejercicioDTOList", ejercicioList);

            RutinaDTO rutina = rutinaService.findById(ejercicioRutinaDTO.getRutina());
            model.addAttribute("rutina", rutina);

            List<EjercicioRutinaDTO> ejerciciosRutina = ejercicioRutinaService.findAllByRutinaId(idEjercicioRutina);
            model.addAttribute("ejerciciosRutina", ejerciciosRutina);
            return "/EntrenadorCrosstraining/editarEjercicioRutina";

        }
    }

    @PostMapping("/rutina/editar-process")
    public String doEditarEjercicio(HttpSession sesion, @ModelAttribute("ejercicioRutinaDTO") EjercicioRutinaDTO ejercicioRutinaDTO) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            ejercicioRutinaService.save(ejercicioRutinaDTO);

            return "redirect:/entrenadorcross/mostrar/" + ejercicioRutinaDTO.getRutina();

        }
    }

    //Ver lista de Clientes asignados
    @GetMapping("/clientes")
    public String doClientes(HttpSession sesion, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("clientes", clientes);
            List<RutinaDTO> rutinas = rutinaService.findAllByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("rutinas", rutinas);
            return "/EntrenadorCrosstraining/listaClientes";
        }
    }

    //Mostrar Información y Rutinas del cliente
    @PostMapping("/cliente/mostrar/{idcliente}")
    public String doClienteVisualizar(HttpSession sesion, Model model, @PathVariable("idcliente") Integer idcliente,
                                      @RequestParam(required = false) String idrutina) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            List<EjercicioDTO> ejercicioList = ejercicioService.getAll();
            model.addAttribute("ejercicioList", ejercicioList);

            ClienteDTO cliente = clienteService.buscarClienteId(idcliente);
            model.addAttribute("cliente", cliente);

            List<RutinaDTO> rutinas = rutinaService.findAllByTrabajadorAndCliente(trabajador.getIdtrabajador(), cliente.getIdcliente());
            model.addAttribute("rutinas", rutinas);

            RutinaDTO rutina = idrutina != null ? rutinaService.findById(Integer.parseInt(idrutina)) : !rutinas.isEmpty() ? rutinas.get(0) : null;
            model.addAttribute("rutina", rutina);

            List<EjercicioRutinaDTO> ejerciciosRutina = rutina != null ?
                    ejercicioRutinaService.findAllByRutinaId(rutina.getIdrutina()) :
                    new ArrayList<>();
            model.addAttribute("ejerciciosRutina", ejerciciosRutina);
            return "/EntrenadorCrosstraining/cliente";
        }

    }

    //Mostrar feedback del cliente
    @PostMapping("cliente/feedback/{idcliente}/{idrutina}")
    public String doFeedBack(HttpSession sesion, Model model, @PathVariable Integer idcliente, @PathVariable Integer idrutina,
                             @RequestParam(required = false) String nombre, @RequestParam(required = false) String fecha) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenadorcross")) {
            return "redirect:/";
        } else {
            ClienteDTO cliente = clienteService.buscarClienteId(idcliente);
            model.addAttribute("cliente", cliente);
            RutinaDTO rutina = rutinaService.findById(idrutina);
            model.addAttribute("rutina", rutina);
            Date fechaDate = fecha != null && !fecha.isEmpty() ? Date.valueOf(fecha) : null;
            List<SeguimientoObjetivosDTO> seguimientoObjetivos =
                    (nombre != null && !nombre.isEmpty()) || fechaDate != null ?
                            seguimientoObjetivosService.findByNombreEjercicioAndFecha(nombre, idcliente, idrutina, fechaDate)
                            : seguimientoObjetivosService.findByClienteAndRutina(idcliente, idrutina);

            model.addAttribute("feedback", seguimientoObjetivos);
            model.addAttribute("filtro", nombre);

            return "/EntrenadorCrosstraining/feedbackCliente";
        }
    }

}
