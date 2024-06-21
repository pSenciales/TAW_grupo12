package es.uma.taw_grupo12.controller.administrador;

import es.uma.taw_grupo12.controller.BaseController;
import es.uma.taw_grupo12.dto.EjercicioDTO;
import es.uma.taw_grupo12.service.EjercicioService;
import es.uma.taw_grupo12.ui.Administrador.FiltroEjercicios;
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
@RequestMapping("/administrador/ejercicios")
public class EjerciciosController extends BaseController {

    @Autowired
    private EjercicioService ejercicioService;

    @GetMapping("/gestionarEjercicios")
    public String doGestionarEjercicios(Model model, HttpSession session) {

        if(!estaAutenticado(session) || session.getAttribute("tipo") != "administrador"){
            return "redirect:/";
        }
        List<EjercicioDTO> ejerciciosDTO = ejercicioService.getAll();

        model.addAttribute("filtroEjercicios", new FiltroEjercicios());
        model.addAttribute("ejerciciosDTO", ejerciciosDTO);
        model.addAttribute("ejercicioModel", new EjercicioDTO());
        model.addAttribute("ejercicioNuevo", new EjercicioDTO());
        model.addAttribute("tituloCabeceraAdmin", "Gestión de ejercicios");

        return "/Administrador/Gestion/gestionarEjercicios";
    }

    @PostMapping("/filtrarGestionarEjercicios")
    public String doFiltrarGestionarEjercicios(@ModelAttribute("filtroEjercicios") FiltroEjercicios filtroEjercicios, Model model) {
        if (filtroEjercicios.estaVacio()) {
            return "redirect:/administrador/ejercicios/gestionarEjercicios";
        }

        List<EjercicioDTO> ejerciciosDTO = null;

        List<String> tipos = filtroEjercicios.getTipos();
        String busqueda = filtroEjercicios.getBusqueda();

        if (tipos.isEmpty()) {
            ejerciciosDTO = this.ejercicioService.listarEjerciciosDTO(busqueda);
        } else {
            ejerciciosDTO = this.ejercicioService.listarEjerciciosDTOTipos(tipos, busqueda);
        }

        model.addAttribute("ejercicioModel", new EjercicioDTO());
        model.addAttribute("ejercicioNuevo", new EjercicioDTO());
        model.addAttribute("ejerciciosDTO", ejerciciosDTO);
        model.addAttribute("tituloCabeceraAdmin", "Gestionar Platos");
        model.addAttribute("filtroEjercicios", filtroEjercicios);

        return "/Administrador/Gestion/gestionarEjercicios";
    }

}
