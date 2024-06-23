/**
 * @author Chen Chen Longxiang
 */
package es.uma.taw_grupo12.controller;

import es.uma.taw_grupo12.dto.*;
import es.uma.taw_grupo12.service.ClienteService;
import es.uma.taw_grupo12.service.DietaService;
import es.uma.taw_grupo12.service.PlatoService;
import es.uma.taw_grupo12.service.SeguimientoDietaService;
import es.uma.taw_grupo12.ui.Dietista.FiltroDietas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dietista")
public class DiestistaController {

    @Autowired
    protected DietaService dietaService;
    @Autowired
    protected PlatoService platoService;
    @Autowired
    protected ClienteService clienteService;
    @Autowired
    protected SeguimientoDietaService seguimientoDietaService;

    @GetMapping("/")
    public String inicio(HttpSession session, Model model) {
        TrabajadorDTO trabajador = (TrabajadorDTO) session.getAttribute("usuario");
        if (trabajador == null ||
                !session.getAttribute("tipo").equals("dietista")) {
            return "redirect:/";
        }

        FiltroDietas filtro = new FiltroDietas();
        List<DietaDTO> dietas = dietaService.findAllDieta();

        model.addAttribute("filtro", filtro);
        model.addAttribute("trabajador", trabajador);
        model.addAttribute("dietas", dietas);

        return "/Dietista/inicioDietista";
    }

    @PostMapping("/filtrar")
    public String filtrar(HttpSession session, Model model, @ModelAttribute("filtro") FiltroDietas filtro){
        TrabajadorDTO trabajador = (TrabajadorDTO) session.getAttribute("usuario");
        if (trabajador == null ||
                !session.getAttribute("tipo").equals("dietista")) {
            return "redirect:/";
        }

        List<DietaDTO> dietas = dietaService.findByName(filtro.getBusqueda());

        model.addAttribute("filtro", filtro);
        model.addAttribute("trabajador", trabajador);
        model.addAttribute("dietas", dietas);

        return "/Dietista/inicioDietista";
    }

    @GetMapping("/verSeguimiento/{idCliente}/{idDieta}")
    public String verSeguimiento(HttpSession session, Model model, @PathVariable Integer idCliente, @PathVariable Integer idDieta){
        TrabajadorDTO trabajador = (TrabajadorDTO) session.getAttribute("usuario");
        if (trabajador == null ||
                !session.getAttribute("tipo").equals("dietista")) {
            return "redirect:/";
        }

        ClienteDTO cliente = clienteService.buscarClienteId(idCliente);
        List<SeguimientoDietaDTO> seguimientos = seguimientoDietaService.findByIdClienteAndIdDieta(idCliente, idDieta);

        model.addAttribute("trabajador", trabajador);
        model.addAttribute("cliente", cliente);
        model.addAttribute("seguimientos", seguimientos);

        return "Dietista/verSeguimiento";
    }

    @GetMapping("/verDieta/{id}")
    public String verDieta(HttpSession session, Model model, @PathVariable Integer id){
        TrabajadorDTO trabajador = (TrabajadorDTO) session.getAttribute("usuario");
        if (trabajador == null ||
                !session.getAttribute("tipo").equals("dietista")) {
            return "redirect:/";
        }

        DietaDTO dieta = dietaService.findDietaById(id);
        List<PlatoDTO> platos = platoService.listarPlatosDTO();
        List<String> franjas = this.inicializarFranjas();
        List<String> dias = this.inicializarDias();
        List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());

        List<PlatoDietaDTO> platoDietasAux = new ArrayList<>();
        List<PlatoDietaDTO> platoDietas = dieta.getPlatoDietaList();
        int count = 0;
        for(String f : franjas){
            for(String d : dias){
                if(count < platoDietas.size() && platoDietas.get(count).getFranjaHoraria().equals(f) && platoDietas.get(count).getDiasSemana().equals(d)){
                    //System.out.println(f + "," + d);
                    platoDietasAux.add(platoDietas.get(count));
                    count++;
                }else{
                    PlatoDietaDTO platoDieta = new PlatoDietaDTO();
                    platoDieta.setFranjaHoraria(f);
                    platoDieta.setDiasSemana(d);
                    platoDieta.setIdPlato(null);
                    platoDietasAux.add(platoDieta);
                }

            }
        }
        dieta.setPlatoDietaList(platoDietasAux);

        model.addAttribute("dieta", dieta);
        model.addAttribute("platos", platos);
        model.addAttribute("dias", dias);
        model.addAttribute("franjas", franjas);
        model.addAttribute("clientes", clientes);

        return "/Dietista/crearDieta";
    }

    @GetMapping("/crearDieta/")
    public String crearDieta(HttpSession session, Model model){
        TrabajadorDTO trabajador = (TrabajadorDTO) session.getAttribute("usuario");
        if (trabajador == null ||
                !session.getAttribute("tipo").equals("dietista")) {
            return "redirect:/";
        }

        DietaDTO dieta = new DietaDTO();
        List<PlatoDTO> platos = platoService.listarPlatosDTO();
        List<ClienteDTO> clientes = clienteService.findByTrabajador(trabajador.getIdtrabajador());

        List<String> franjas = this.inicializarFranjas();
        List<String> dias = this.inicializarDias();

        List<PlatoDietaDTO> platoDietas = new ArrayList<>();
        for(String f : franjas){
            for(String d : dias){
                PlatoDietaDTO platoDieta = new PlatoDietaDTO();
                platoDieta.setFranjaHoraria(f);
                platoDieta.setDiasSemana(d);
                platoDieta.setIdPlato(null);
                platoDietas.add(platoDieta);
            }
        }
        dieta.setPlatoDietaList(platoDietas);

        model.addAttribute("dias", dias);
        model.addAttribute("franjas", franjas);
        model.addAttribute("dieta", dieta);
        model.addAttribute("platos", platos);
        model.addAttribute("clientes", clientes);

        return "/Dietista/crearDieta";
    }

    @PostMapping("/postDieta/")
    public String postDieta(HttpSession session, @ModelAttribute("dieta") DietaDTO dieta){
        TrabajadorDTO trabajador = (TrabajadorDTO) session.getAttribute("usuario");
        if (trabajador == null ||
                !session.getAttribute("tipo").equals("dietista")) {
            return "redirect:/";
        }

        dietaService.createDieta(dieta);
        return "redirect:/dietista/";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteDieta(HttpSession session, @PathVariable Integer id){
        TrabajadorDTO trabajador = (TrabajadorDTO) session.getAttribute("usuario");
        if (trabajador == null ||
                !session.getAttribute("tipo").equals("dietista")) {
            return "redirect:/";
        }

        dietaService.deleteById(id);

        return "redirect:/dietista/";
    }

    private List<String> inicializarFranjas(){
        List<String> franjas = new ArrayList<>();
        franjas.add("Desayuno");
        franjas.add("Mediodia");
        franjas.add("Almuerzo");
        franjas.add("Merienda");
        franjas.add("Cena");

        return franjas;
    }

    private List<String> inicializarDias(){
        List<String> dias = new ArrayList<>();
        dias.add("Lunes");
        dias.add("Martes");
        dias.add("Miercoles");
        dias.add("Jueves");
        dias.add("Viernes");
        dias.add("Sabado");
        dias.add("Domingo");

        return dias;
    }
}
