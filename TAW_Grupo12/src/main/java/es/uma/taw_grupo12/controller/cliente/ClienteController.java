package es.uma.taw_grupo12.controller.cliente;

import es.uma.taw_grupo12.dto.ClienteDTO;
import es.uma.taw_grupo12.dto.UsuarioDTO;
import es.uma.taw_grupo12.service.ClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cliente")
@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public String toInicio(HttpSession session, Model model) {
        ClienteDTO c = (ClienteDTO) session.getAttribute("usuario");

        if(c == null) {
            return "redirect:/cliente/error";
        }

        model.addAttribute("cliente", c);
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

        model.addAttribute("cliente", c);
        return "Cliente/perfil";
    }

    @PostMapping("/editarCliente")
    public String doEditarCliente(@ModelAttribute("cliente") ClienteDTO cliente, HttpSession session) {
        clienteService.actualizarCliente(cliente);
        // Actualizo el cliente del session
        session.setAttribute("usuario", cliente);

        return "redirect:/cliente/perfil";
    }

    @GetMapping("/redir/{url}")
    public String doClienteRedirect(@PathVariable String url) {
        return "Cliente/" + url;
    }
}
