/**
 * @author María Victoria Huesca Peláez
 */

package es.uma.taw_grupo12.controller.administrador;

import es.uma.taw_grupo12.controller.BaseController;

import es.uma.taw_grupo12.dto.AdministradorDTO;
import es.uma.taw_grupo12.dto.ClienteDTO;
import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.service.AdministradorService;
import es.uma.taw_grupo12.service.ClienteService;
import es.uma.taw_grupo12.service.TrabajadorService;
import es.uma.taw_grupo12.ui.Administrador.FiltroClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

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
            return "/Administrador/AsignarClientes/asignarEntrenadorCrosstrainning";
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
}
