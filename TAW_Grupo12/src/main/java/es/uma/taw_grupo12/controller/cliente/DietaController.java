


package es.uma.taw_grupo12.controller.cliente;

import es.uma.taw_grupo12.dto.*;
import es.uma.taw_grupo12.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cliente/dietas")
public class DietaController {

    @Autowired
    SeguimientoDietaService seguimientoDietaService;
    @Autowired
    DietaService dietaService;
    @Autowired
    PlatoDietaService platoDietaService;
    @Autowired
    PlatoService platoService;

    @GetMapping("/")
    public String doSeleccionarRutina(HttpSession session, Model model) {
        ClienteDTO c = (ClienteDTO) session.getAttribute("usuario");
        if(c == null)
            return "redirect:/cliente/error";

        model.addAttribute("dietas", c.getDietaList());
        model.addAttribute("dia", null);
        model.addAttribute("listaEjerciciosRutina", null);
        model.addAttribute("listaEjercicios", null);
        model.addAttribute("listaSeguimientos", null);
        return "Cliente/detallesDieta";
    }

    @PostMapping("/")
    public String doMostrarRutina(HttpSession session, @RequestParam("rutinaId") Integer id,
                                  @RequestParam("dia") String dia, Model model) {
        ClienteDTO c = (ClienteDTO) session.getAttribute("usuario");
        if(c == null)
            return "redirect:/cliente/error";

        List<PlatoDTO> platoDTOList = new ArrayList<>();
        List<PlatoDietaDTO> platoDietaDTOList = new ArrayList<>();

        for(PlatoDietaDTO platoDietaDTO : platoDietaService.findAllByRutinaId(id)) {
            if(platoDietaDTO.getDiasSemana() != null && platoDietaDTO.getDiasSemana().equalsIgnoreCase(dia)) {
                platoDietaDTOList.add(platoDietaDTO);
                platoDTOList.add(platoService.findById(platoDietaDTO.getIdPlato()));
            }
        }

        /* Devuelve los platos de la dieta y sus platos base asociados en el mismo orden */
        model.addAttribute("dietas", c.getDietaList());
        model.addAttribute("dia", dia);
        model.addAttribute("listaPlatosDieta", platoDietaDTOList);
        model.addAttribute("listaPlatos", platoDTOList);
        model.addAttribute("listaSeguimientos",
                seguimientoDietaService.findByIdClienteAndIdDieta(c.getIdcliente(), id));
        return "Cliente/detallesDieta";
    }

    @PostMapping("/guardarSeguimiento")
    public String guardarSeguimiento(
            @RequestParam("idDieta") Integer idDieta,
            @RequestParam("dia") String dia,
            @RequestParam("nombrePlato") String nombrePlato,
            @RequestParam("cantOb") String cantOb,
            @RequestParam(value = "comido", required = false, defaultValue = "0") short comido,
            @RequestParam(value = "cantidadComida", required = false, defaultValue = "0") Integer cantidadComida,
            @RequestParam("obs") String observaciones,
            HttpSession session,
            Model model)
    {
        SeguimientoDietaDTO seguimiento = new SeguimientoDietaDTO();
        ClienteDTO c = (ClienteDTO) session.getAttribute("usuario");
        if(c == null)
            return "redirect:/cliente/error";

        seguimiento.setIdDieta(idDieta);
        seguimiento.setIdCliente(c.getIdcliente());
        seguimiento.setFecha(new Date());
        seguimiento.setNombrePlato(nombrePlato);
        seguimiento.setCantidadObjeto(cantOb);
        seguimiento.setComido(comido);
        seguimiento.setCantidad(cantidadComida);
        seguimiento.setObservaciones(observaciones);

        seguimientoDietaService.guardarSeguimiento(seguimiento);

        List<PlatoDTO> platoDTOList = new ArrayList<>();
        List<PlatoDietaDTO> platoDietaDTOList = new ArrayList<>();

        for(PlatoDietaDTO platoDietaDTO : platoDietaService.findAllByRutinaId(idDieta)) {
            if(platoDietaDTO.getDiasSemana() != null && platoDietaDTO.getDiasSemana().equalsIgnoreCase(dia)) {
                platoDietaDTOList.add(platoDietaDTO);
                platoDTOList.add(platoService.findById(platoDietaDTO.getIdPlato()));
            }
        }

        /* Devuelve los platos de la dieta y sus platos base asociados en el mismo orden */
        model.addAttribute("dietas", c.getDietaList());
        model.addAttribute("dia", dia);
        model.addAttribute("listaPlatosDieta", platoDietaDTOList);
        model.addAttribute("listaPlatos", platoDTOList);
        model.addAttribute("listaSeguimientos",
                seguimientoDietaService.findByIdClienteAndIdDieta(c.getIdcliente(), idDieta));
        return "Cliente/detallesDieta";
    }
}
