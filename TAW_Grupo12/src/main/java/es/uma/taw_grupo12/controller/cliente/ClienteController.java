package es.uma.taw_grupo12.controller.cliente;

import es.uma.taw_grupo12.dto.*;
import es.uma.taw_grupo12.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/cliente")
@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TrabajadorService trabajadorService;
    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private EjercicioService ejercicioService;
    @Autowired
    private EjercicioRutinaService ejercicioRutinaService;
    @Autowired
    private SeguimientoObjetivosService seguimientoObjetivosService;

    @GetMapping("/")
    public String toInicio(HttpSession session) {
        ClienteDTO c = (ClienteDTO) session.getAttribute("usuario");

        if(c == null) {
            return "redirect:/cliente/error";
        }

        return "Cliente/pagPersonalCliente";
    }

    @GetMapping("/error")
    public String toAutenticar(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return ("Administrador/Autenticacion/login");
    }

    @GetMapping("/perfil")
    public String toPerfil(HttpSession session, Model model) {
        ClienteDTO c = (ClienteDTO) session.getAttribute("usuario");

        if(c == null)
            return "redirect:/cliente/error";

        List<TrabajadorDTO> trabajadorList = new ArrayList<>();
        for(Integer id : c.getTrabajadorList()) {
            trabajadorList.add(trabajadorService.findById(id));
        }

        model.addAttribute("cliente", c);
        model.addAttribute("trabajadores", trabajadorList);

        return "Cliente/perfil";
    }

    @PostMapping("/editarCliente")
    public String doEditarCliente(@ModelAttribute("cliente") ClienteDTO cliente, HttpSession session) {
        clienteService.actualizarCliente(cliente);
        // Actualizo el cliente del session
        session.setAttribute("usuario", cliente);

        return "redirect:/cliente/perfil";
    }

    @PostMapping("/salir")
    public String doSalir(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }

    @GetMapping("/redir/{url}")
    public String doClienteRedirect(@PathVariable String url) {
        return "Cliente/" + url;
    }
}
