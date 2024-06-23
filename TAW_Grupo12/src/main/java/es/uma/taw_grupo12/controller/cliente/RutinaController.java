package es.uma.taw_grupo12.controller.cliente;

import es.uma.taw_grupo12.dto.*;
import es.uma.taw_grupo12.service.EjercicioRutinaService;
import es.uma.taw_grupo12.service.EjercicioService;
import es.uma.taw_grupo12.service.RutinaService;
import es.uma.taw_grupo12.service.SeguimientoObjetivosService;
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

//Nacho
@Controller
@RequestMapping("/cliente/rutinas")
public class RutinaController {

    @Autowired
    RutinaService rutinaService;
    @Autowired
    EjercicioRutinaService ejercicioRutinaService;
    @Autowired
    EjercicioService ejercicioService;
    @Autowired
    SeguimientoObjetivosService seguimientoObjetivosService;

    @GetMapping("/")
    public String doSeleccionarRutina(HttpSession session, Model model) {
        ClienteDTO c = (ClienteDTO) session.getAttribute("usuario");
        if(c == null)
            return "redirect:/cliente/error";

        model.addAttribute("dietas", c.getRutinaList());
        model.addAttribute("dia", null);
        model.addAttribute("listaEjerciciosRutina", null);
        model.addAttribute("listaEjercicios", null);
        model.addAttribute("listaSeguimientos", null);
        return "Cliente/detallesRutina";
    }

    @PostMapping("/")
    public String doMostrarRutina(HttpSession session, @RequestParam("rutinaId") Integer id,
                                  @RequestParam("dia") String dia, Model model) {
        ClienteDTO c = (ClienteDTO) session.getAttribute("usuario");
        if(c == null)
            return "redirect:/cliente/error";

        List<EjercicioDTO> ejercicioDTOList = new ArrayList<>();
        List<EjercicioRutinaDTO> ejercicioRutinaDTOList = new ArrayList<>();

        for(EjercicioRutinaDTO ejercicioRutinaDTO : ejercicioRutinaService.findAllByRutinaId(id)) {
            if(ejercicioRutinaDTO.getDiassemana() != null && ejercicioRutinaDTO.getDiassemana().equalsIgnoreCase(dia)) {
                ejercicioRutinaDTOList.add(ejercicioRutinaDTO);
                ejercicioDTOList.add(ejercicioService.findById(ejercicioRutinaDTO.getEjercicio()));
            }
        }

        /* Devuelve los ejercicios de la rutina y sus ejercicios base asociados en el mismo orden */
        model.addAttribute("rutinas", c.getRutinaList());
        model.addAttribute("dia", dia);
        model.addAttribute("listaEjerciciosRutina", ejercicioRutinaDTOList);
        model.addAttribute("listaEjercicios", ejercicioDTOList);
        model.addAttribute("listaSeguimientos",
                seguimientoObjetivosService.findByClienteAndRutina(c.getIdcliente(), id));
        return "Cliente/detallesRutina";
    }

    @PostMapping("/guardarSeguimiento")
    public String guardarSeguimiento(
            @RequestParam("idRutina") Integer idRutina,
            @RequestParam("dia") String dia,
            @RequestParam("nombreEj") String nombreEj,
            @RequestParam("pesoOb") String pesoOb,
            @RequestParam("repOb") Integer repOb,
            @RequestParam("seriesOb") Integer seriesOb,
            @RequestParam(value = "hecho", required = false, defaultValue = "0") short hecho,
            @RequestParam(value = "ser", required = false, defaultValue = "0") Integer seriesRealizadas,
            @RequestParam(value = "rep",required = false, defaultValue = "0") Integer repeticionesRealizadas,
            @RequestParam("cant") String cantidadRealizada,
            @RequestParam("obs") String observaciones,
            HttpSession session,
            Model model)
    {
        SeguimientoObjetivosDTO seguimiento = new SeguimientoObjetivosDTO();
        ClienteDTO c = (ClienteDTO) session.getAttribute("usuario");
        if(c == null)
            return "redirect:/cliente/error";

        seguimiento.setRutina(idRutina);
        seguimiento.setCliente(c.getIdcliente());
        seguimiento.setFecha(new Date());
        seguimiento.setNombreejercicio(nombreEj);
        seguimiento.setPesoobjetivo(pesoOb);
        seguimiento.setRepeticionesobjetivo(repOb);
        seguimiento.setSeriesobjetivo(seriesOb);
        seguimiento.setRealizado(hecho);
        seguimiento.setSeriesrealizadas(seriesRealizadas);
        seguimiento.setRepeticionesrealizadas(repeticionesRealizadas);
        seguimiento.setPesorealizado(String.valueOf(cantidadRealizada));
        seguimiento.setObservaciones(observaciones);

        seguimientoObjetivosService.guardarSeguimiento(seguimiento);

        List<EjercicioDTO> ejercicioDTOList = new ArrayList<>();
        List<EjercicioRutinaDTO> ejercicioRutinaDTOList = new ArrayList<>();

        for(EjercicioRutinaDTO ejercicioRutinaDTO : ejercicioRutinaService.findAllByRutinaId(idRutina)) {
            if(ejercicioRutinaDTO.getDiassemana() != null && ejercicioRutinaDTO.getDiassemana().equalsIgnoreCase(dia)) {
                ejercicioRutinaDTOList.add(ejercicioRutinaDTO);
                ejercicioDTOList.add(ejercicioService.findById(ejercicioRutinaDTO.getEjercicio()));
            }
        }

        model.addAttribute("rutinas", c.getRutinaList());
        model.addAttribute("dia", dia);
        model.addAttribute("listaEjerciciosRutina", ejercicioRutinaDTOList);
        model.addAttribute("listaEjercicios", ejercicioDTOList);
        model.addAttribute("listaSeguimientos",
                seguimientoObjetivosService.findByClienteAndRutina(c.getIdcliente(), idRutina));
        return "Cliente/detallesRutina";
    }
}
