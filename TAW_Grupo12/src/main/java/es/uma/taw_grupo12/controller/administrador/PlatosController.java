package es.uma.taw_grupo12.controller.administrador;

import es.uma.taw_grupo12.controller.BaseController;
import es.uma.taw_grupo12.dto.ClienteDTO;
import es.uma.taw_grupo12.dto.PlatoDTO;
import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.service.PlatoService;
import es.uma.taw_grupo12.ui.Administrador.FiltroPlatos;
import es.uma.taw_grupo12.ui.Administrador.FiltroUsuarios;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/administrador/platos")
public class PlatosController extends BaseController {

    @Autowired
    private PlatoService platoService;

    @GetMapping("/gestionarPlatos")
    public String doGestionarPlatos(Model model, HttpSession session) {
        if (!estaAutenticado(session) || session.getAttribute("tipo") != "administrador"){
            return "redirect:/";
        }

        List<PlatoDTO> platos = this.platoService.listarPlatosDTO();

        model.addAttribute("platos", platos);
        model.addAttribute("filtroPlatos", new FiltroPlatos());
        model.addAttribute("platoModel", new PlatoDTO());
        model.addAttribute("tituloCabeceraAdmin", "Gestionar Platos");
        return "/Administrador/GestionarPlatos/gestionarPlatos";
    }

    @PostMapping("/filtrarGestionarPlatos")
    public String doFiltrarGestionarClientes(Model model, @ModelAttribute("filtroPlatos") FiltroPlatos filtroPlatos) {
        if (filtroPlatos.estaVacio()) {
            return "redirect:/administrador/platos/gestionarPlatos";
        }

        List<PlatoDTO> platosDTO = null;

        List<String> alergenos = filtroPlatos.getSinAlergenos();
        String busqueda = filtroPlatos.getBusqueda();

        if (alergenos.isEmpty()) {
            platosDTO = this.platoService.listarplatosDTO(busqueda);
        } else {
            platosDTO = this.platoService.listarPlatosDTOAlergenos(alergenos, busqueda);
        }

        model.addAttribute("platoModel", new PlatoDTO());
        model.addAttribute("platos", platosDTO);
        model.addAttribute("tituloCabeceraAdmin", "Gestionar Platos");
        model.addAttribute("filtroPlatos", filtroPlatos);

        return "/Administrador/GestionarPlatos/gestionarPlatos";
    }

}
