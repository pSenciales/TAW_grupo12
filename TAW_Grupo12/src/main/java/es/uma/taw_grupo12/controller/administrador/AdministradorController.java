/**
 * @author María Victoria Huesca Peláez
 */

package es.uma.taw_grupo12.controller.administrador;

import es.uma.taw_grupo12.controller.BaseController;

import es.uma.taw_grupo12.dto.AdministradorDTO;
import es.uma.taw_grupo12.dto.ClienteDTO;
import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.Trabajador;
import es.uma.taw_grupo12.service.AdministradorService;
import es.uma.taw_grupo12.service.ClienteService;
import es.uma.taw_grupo12.service.TrabajadorService;
import es.uma.taw_grupo12.ui.Administrador.FiltroClientes;
import es.uma.taw_grupo12.ui.Administrador.FiltroUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/administrador")
public class AdministradorController extends BaseController{

    @Autowired
    protected ClienteService clienteService;

    @Autowired
    protected TrabajadorService trabajadorService;

    @Autowired
    protected AdministradorService administradorService;


    @GetMapping("/inicio")
    public String doInicio(HttpSession session, Model model){
        String strTo = "/Administrador/inicioAdministrador";

        if (!estaAutenticado(session) || session.getAttribute("tipo") != "administrador"){
            strTo = "redirect:/";
        } else {
            AdministradorDTO miAdmin = (AdministradorDTO) session.getAttribute("usuario");
            model.addAttribute("correoAdministrador", miAdmin.getEmail() );
            model.addAttribute("tituloCabeceraAdmin", "");
        }

        return strTo;
    }

    @PostMapping("/verPerfil")
    public String doVerPerfil(HttpSession session){
        return "/Administrador/perfilAdministrador";
    }

    @GetMapping("/asignarClientes")
    public String doAsignarClientes(Model model, HttpSession session){

        if (!estaAutenticado(session) || session.getAttribute("tipo") != "administrador"){
            return "redirect:/";
        }

        List<ClienteDTO> clientes = this.clienteService.listarClientesDTO();
        List<TrabajadorDTO> trabajadoresDTO = this.trabajadorService.listarTrabajadoresDTO();

        model.addAttribute("filtroClientes", new FiltroClientes());
        model.addAttribute("trabajadores", trabajadoresDTO );
        model.addAttribute("clientes", clientes);
        model.addAttribute("tituloCabeceraAdmin", "Asignar Clientes");
        return "/Administrador/AsignarClientes/asignarClientes";
    }

    @PostMapping("/filtrarAsignarClientes")
    public String doFiltrarAsignarClientes(Model model, HttpSession session, @ModelAttribute("filtroClientes") FiltroClientes filtroClientes) {
        if (filtroClientes.estaVacio()) {
            return "redirect:/administrador/asignarClientes";
        }

        List<ClienteDTO> clientes = this.clienteService.listarClientesDTO(filtroClientes.getBusqueda());
        List<TrabajadorDTO> trabajadoresDTO = this.trabajadorService.listarTrabajadoresDTO();

        model.addAttribute("filtroClientes",filtroClientes);
        model.addAttribute("trabajadores", trabajadoresDTO);
        model.addAttribute("clientes", clientes);
        model.addAttribute("tituloCabeceraAdmin", "Asignar Clientes");
        return "/Administrador/AsignarClientes/asignarClientes";
    }

    @PostMapping("/asignarCliente")
    public String doAsignarCliente(Model model, HttpSession session, @RequestParam("tipoTrabajador") String tipoTrabajador, @RequestParam("clienteId") Integer idCliente){

        List<TrabajadorDTO> trabajadoresDTO = this.trabajadorService.listarTrabajadoresDTOTipo(tipoTrabajador);
        ClienteDTO cliente = this.clienteService.buscarClienteId(idCliente);
        model.addAttribute("trabajadores", trabajadoresDTO);
        model.addAttribute("cliente", cliente);
        model.addAttribute("tituloCabeceraAdmin", "Asignación Cliente");

        if(tipoTrabajador.equals("ENTRENADOR FUERZA")){
            return "/Administrador/AsignarClientes/asignarEntrenadorFuerza";
        }
        if(tipoTrabajador.equals("ENTRENADOR CROSSTRAINNING")){
            return "/Administrador/AsignarClientes/asignarEntrenadorCrosstraining";
        }
        if(tipoTrabajador.equals("DIETISTA")){
            return "/Administrador/AsignarClientes/asignarDietista";
        }

        return "redirect:/administrador/asignarClientes";
    }

    @PostMapping("/guardarAsignacionClienteTrabajador")
    public String doAsignarTrabajador(@RequestParam("idTrabajador") Integer idTrabajador, @RequestParam("idCliente") Integer idCliente){
        this.administradorService.asignarTrabajador(idCliente, idTrabajador);
        return "redirect:/administrador/asignarClientes";
    }

    @PostMapping("/desasignarTrabajador")
    public String doDesasignarTrabajador(@RequestParam("idClienteDes") Integer idCliente, @RequestParam("idTrabajadorDes") Integer idTrabajador){
        this.administradorService.desasignarTrabajador(idCliente, idTrabajador);
        return "redirect:/administrador/asignarClientes";
    }

    @GetMapping("/gestionarUsuarios")
    public String doGestionarUsuarios(HttpSession session, Model model){

        if (!estaAutenticado(session) || session.getAttribute("tipo") != "administrador"){
            return "redirect:/";
        }

        List<ClienteDTO> clientesDTO = this.clienteService.listarClientesDTO();
        List<TrabajadorDTO> trabajadoresDTO = this.trabajadorService.listarTrabajadoresDTO();
        model.addAttribute("filtroUsuarios", new FiltroUsuarios());
        model.addAttribute("clienteModel", new ClienteDTO());
        model.addAttribute("trabajadorModel", new TrabajadorDTO());
        model.addAttribute("clientes", clientesDTO);
        model.addAttribute("trabajadores", trabajadoresDTO);
        model.addAttribute("tituloCabeceraAdmin", "Gestionar Usuarios");

        return "/Administrador/GestionarUsuarios/gestionarUsuarios";
    }

    @PostMapping("/filtrarGestionarUsuarios")
    public String doFiltrarGestionarClientes(Model model, @ModelAttribute("filtroUsuarios") FiltroUsuarios filtroUsuarios) {
        if (filtroUsuarios.estaVacio()) {
            return "redirect:/administrador/gestionarUsuarios";
        }
        List<ClienteDTO> clientesDTO = null;
        List<TrabajadorDTO> trabajadoresDTO = null;

        List<String> tipoUsuario = filtroUsuarios.getTipoUsuario();
        String tipoTrabajador = filtroUsuarios.getTipoTrabajador();
        String busqueda = filtroUsuarios.getBusqueda();

        if (tipoUsuario.isEmpty() || tipoUsuario.contains("Cliente")) {
            clientesDTO = this.clienteService.listarClientesDTO(busqueda);
        }

        if (tipoUsuario.isEmpty() || tipoUsuario.contains("Trabajador")) {
            if(tipoTrabajador.isEmpty()) {
                trabajadoresDTO = this.trabajadorService.listarTrabajadoresDTO(busqueda);
            } else {
                if(tipoTrabajador.contains("Entrenador fuerza")){
                    trabajadoresDTO = this.trabajadorService.listarTrabajadoresDTOTipo("ENTRENADOR FUERZA", busqueda);
                }
                if(tipoTrabajador.contains("Entrenador crosstraining")){
                    trabajadoresDTO = this.trabajadorService.listarTrabajadoresDTOTipo("ENTRENADOR CROSSTRAINING", busqueda);
                }
                if(tipoTrabajador.contains("Dietista")){
                    trabajadoresDTO = this.trabajadorService.listarTrabajadoresDTOTipo("DIETISTA", busqueda);
                }
            }
        }

        model.addAttribute("filtroUsuarios", filtroUsuarios);
        model.addAttribute("clienteModel", new ClienteDTO());
        model.addAttribute("trabajadorModel", new TrabajadorDTO());
        model.addAttribute("trabajadores", trabajadoresDTO);
        model.addAttribute("clientes", clientesDTO);
        model.addAttribute("tituloCabeceraAdmin", "Gestionar Clientes");
        return "/Administrador/GestionarUsuarios/gestionarUsuarios";
    }

    @PostMapping("/eliminarCliente")
    public String doEliminarCliente(@RequestParam("idCliente") Integer idCliente){
        this.clienteService.eliminarCliente(idCliente);
        return "redirect:/administrador/gestionarUsuarios";
    }

    @PostMapping("/eliminarTrabajador")
    public String doEliminarTrabajador(@RequestParam("idTrabajador") Integer idTrabajador){
        this.trabajadorService.eliminarTrabajador(idTrabajador);
        return "redirect:/administrador/gestionarUsuarios";
    }

    @PostMapping("/guardarCliente")
    public String doGuardarCliente(@ModelAttribute("clienteModel") ClienteDTO cliente, RedirectAttributes redirectAttributes) throws IOException {
        List<Cliente> existe = this.clienteService.buscarClienteNombreoEmail(cliente);
        if(existe != null && !existe.isEmpty()){
            redirectAttributes.addFlashAttribute("errorGestionarUsuario", "Se ha intentado guardar un cliente con un email o nombre ya existente, los cambios no se han guardado");            return "redirect:/administrador/gestionarUsuarios";
        }
        this.clienteService.guardarCliente(cliente);
        return "redirect:/administrador/gestionarUsuarios";
    }

    @PostMapping("/guardarTrabajador")
    public String doGuardarTrabajador(@ModelAttribute("trabajadorModel") TrabajadorDTO trabajador, RedirectAttributes redirectAttributes) throws IOException {
        List<Trabajador> existe = this.trabajadorService.buscarTrabajadorNombreoEmail(trabajador);
        if(existe != null && !existe.isEmpty()){
            redirectAttributes.addFlashAttribute("errorGestionarUsuario", "Se ha intentado guardar un trabajador con un email o nombre ya existente, los cambios no se han guardado");            return "redirect:/administrador/gestionarUsuarios";
        }
        this.trabajadorService.guardarTrabajador(trabajador);
        return "redirect:/administrador/gestionarUsuarios";
    }
}
