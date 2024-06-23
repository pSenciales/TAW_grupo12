/**
 * @author 	PABLO SENCIALES DE LA HIGUERA
 */
package es.uma.taw_grupo12.controller;

import es.uma.taw_grupo12.dto.*;
import es.uma.taw_grupo12.service.*;
import es.uma.taw_grupo12.dto.EjercicioDTO;
import es.uma.taw_grupo12.dto.RutinaDTO;
import es.uma.taw_grupo12.service.EjercicioService;
import es.uma.taw_grupo12.service.RutinaService;
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
@RequestMapping("/entrenador")
public class EntrenadorController {


    @Autowired
    private EjercicioService ejercicioService;
    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private EjercicioRutinaService ejercicioRutinaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private SeguimientoObjetivosService seguimientoObjetivosService;


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
            FiltroRutinas filtro = new FiltroRutinas();
            model.addAttribute("filtro", filtro);
            List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("clientes", clientes);
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
            RutinaDTO rutina = new RutinaDTO();
            model.addAttribute("rutina", rutina);
            return "/Entrenador/crearRutina";
        }
    }

    @PostMapping(value = {"/new-rutina/crear", "editar/new-rutina/crear"})
    public String doCrearRutina(HttpSession sesion, @ModelAttribute("rutina") RutinaDTO rutina) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            rutina.setIdtrabajador(trabajador.getIdtrabajador());
            rutinaService.save(rutina);

            return "redirect:/entrenador/rutinas";

        }
    }


    @GetMapping("/editar/{id}")
    public String doEditarRutina(HttpSession sesion, @PathVariable int id, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("clientes", clientes);
            RutinaDTO rutina = rutinaService.findById(id);
            model.addAttribute("rutina", rutina);

            return "/Entrenador/crearRutina";


        }
    }
    @GetMapping("/borrar/{id}")
    public String doBorrarRutina(HttpSession sesion, @PathVariable int id) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {

            rutinaService.deleteById(id);

            return "redirect:/entrenador/rutinas";

        }
    }



    @PostMapping("/filtrar")
    public String doFiltrar(HttpSession sesion, Model model, @ModelAttribute("filtro") FiltroRutinas filtro){
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            List<RutinaDTO> rutinas = rutinaService.findByFiltro(filtro, trabajador.getIdtrabajador());
            model.addAttribute("rutinas", rutinas);
            model.addAttribute("filtro", filtro);
            List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("clientes", clientes);
            return "/Entrenador/listarRutinas";
        }

    }





    /*
     *
     * VISUALIZAR CLIENTES
     *
     * */


    @GetMapping("/clientes")
    public String doClientes(HttpSession sesion, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("clientes", clientes);
            List<RutinaDTO> rutinas = rutinaService.findAllByTrabajador(trabajador.getIdtrabajador());
            model.addAttribute("rutinas", rutinas);
            return "/Entrenador/listarClientes";
        }
    }

    @PostMapping("/cliente/visualizar/{idcliente}")
    public String doClienteVisualizar(HttpSession sesion, Model model, @PathVariable("idcliente") Integer idcliente,
                                      @RequestParam(required = false) String idrutina ){
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            List<EjercicioDTO> ejercicioList = ejercicioService.getFuerza();
            model.addAttribute("ejercicioList", ejercicioList);

            ClienteDTO cliente = clienteService.buscarClienteId(idcliente);
            model.addAttribute("cliente", cliente);

            List<RutinaDTO> rutinas = rutinaService.findAllByTrabajadorAndCliente(trabajador.getIdtrabajador(), cliente.getIdcliente());
            model.addAttribute("rutinas", rutinas);

            RutinaDTO rutina = idrutina != null ? rutinaService.findById(Integer.parseInt(idrutina)) : !rutinas.isEmpty() ? rutinas.get(0) : null ;
            model.addAttribute("rutina", rutina);

            List<EjercicioRutinaDTO> ejerciciosRutina = rutina != null ?
                    ejercicioRutinaService.findAllByRutinaId(rutina.getIdrutina()):
                    new ArrayList<>();
            model.addAttribute("ejerciciosRutina", ejerciciosRutina);
            return "/Entrenador/visualizarCliente";
        }

    }

    @PostMapping("cliente/feedback/{idcliente}/{idrutina}")
    public String doFeedBack(HttpSession sesion, Model model, @PathVariable Integer idcliente, @PathVariable Integer idrutina,
                             @RequestParam(required = false) String nombre, @RequestParam(required = false) String fecha) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
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
            model.addAttribute("filtro",nombre );

            return "/Entrenador/visualizarFeedback";
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

    @GetMapping("/rutina/borrar/{id}")
    public String doBorrarEjercicio(HttpSession sesion, @PathVariable("id") Integer id) {
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

    @GetMapping("/rutina/editar/{id}")
    public String doEditarEjercicio(HttpSession sesion, Model model, @PathVariable("id") Integer id) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            EjercicioRutinaDTO ejercicioRutinaDTO = ejercicioRutinaService.findById(id);
            model.addAttribute("ejercicioRutinaDTO", ejercicioRutinaDTO);

            List<EjercicioDTO> ejercicioList = ejercicioService.getFuerza();
            model.addAttribute("ejercicioDTOList", ejercicioList);

            RutinaDTO rutina = rutinaService.findById(ejercicioRutinaDTO.getRutina());
            model.addAttribute("rutina", rutina);

            List<EjercicioRutinaDTO> ejerciciosRutina = ejercicioRutinaService.findAllByRutinaId(id);
            model.addAttribute("ejerciciosRutina", ejerciciosRutina);
            return "/Entrenador/editarEjercicioRutina";

        }
    }

    @PostMapping("/rutina/editar-process")
    public String doEditarEjercicio(HttpSession sesion, @ModelAttribute("ejercicioRutinaDTO") EjercicioRutinaDTO ejercicioRutinaDTO) {
        TrabajadorDTO trabajador = (TrabajadorDTO) sesion.getAttribute("usuario");
        if (trabajador == null ||
                !sesion.getAttribute("tipo").equals("entrenador")) {
            return "redirect:/";
        } else {
            ejercicioRutinaService.save(ejercicioRutinaDTO);

            return "redirect:/entrenador/visualizar/" + ejercicioRutinaDTO.getRutina();

        }
    }
}
