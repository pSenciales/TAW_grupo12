/**
 * @author María Victoria Huesca Peláez
 */

package es.uma.taw_grupo12.controller.administrador;

import es.uma.taw_grupo12.controller.BaseController;

import es.uma.taw_grupo12.dto.AdministradorDTO;
import es.uma.taw_grupo12.dto.ClienteDTO;
import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.entity.Trabajador;
import es.uma.taw_grupo12.service.ClienteService;
import es.uma.taw_grupo12.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/administrador")
public class AdministradorController extends BaseController{

    @Autowired
    protected ClienteService clienteService;

    @Autowired
    protected TrabajadorService trabajadorService;

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
        if (!estaAutenticado(session)) {
            return "redirect:/";
        }
        return "/Administrador/perfilAdministrador";
    }

    @GetMapping("/asignarClientes")
    public String doAsignarClientes(Model model, HttpSession session){

        if (!estaAutenticado(session) || session.getAttribute("tipo") != "administrador"){
            return "redirect:/";
        }

        List<ClienteDTO> clientes = this.clienteService.listarClientesDTO();
        List<TrabajadorDTO> trabajadoresDTO = this.trabajadorService.listarTrabajadoresDTO();

        model.addAttribute("trabajadores", trabajadoresDTO );
        model.addAttribute("clientes", clientes);
        model.addAttribute("tituloCabeceraAdmin", "Asignar Clientes");
        return "/Administrador/AsignarClientes/asignarClientes";
    }

    @GetMapping("/asignarCliente")
    public String doAsignarCliente(Model model, HttpSession session){
        if (!estaAutenticado(session) || session.getAttribute("tipo") != "administrador"){
            return "redirect:/";
        }


        model.addAttribute("tituloCabeceraAdmin", "Asignar Cliente");
        return "/Administrador/AsignarClientes/asignarCliente";
    }

}
