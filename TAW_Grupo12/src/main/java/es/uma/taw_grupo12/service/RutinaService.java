/**
 * @author 	PABLO SENCIALES DE LA HIGUERA (1/7)
 * @author Ignacio morillas rosell (1/7)
 */

package es.uma.taw_grupo12.service;


import es.uma.taw_grupo12.dao.TrabajadorRepository;
import es.uma.taw_grupo12.dto.RutinaDTO;
import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.Rutina;
import es.uma.taw_grupo12.dao.ClienteRepository;
import es.uma.taw_grupo12.dao.RutinaRepository;
import es.uma.taw_grupo12.entity.Trabajador;
import es.uma.taw_grupo12.ui.Entrenador.FiltroRutinas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RutinaService {
    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TrabajadorRepository trabajadorRepository;

    //@Pablo
    public RutinaDTO findById(Integer id){
        Rutina rutina = rutinaRepository.findById(id).orElse(null);
        assert rutina != null;

        RutinaDTO rutinaDTO = rutina.toDTO();
        return rutinaDTO;

    }
    //@Pablo

    public void save(RutinaDTO rutinaDTO){
            Rutina rutina = new Rutina();
            Cliente cliente = clienteRepository.findById(rutinaDTO.getIdcliente()).orElse(null);
            assert cliente != null;
            Trabajador trabajador = trabajadorRepository.findById(rutinaDTO.getIdtrabajador()).orElse(null);
            assert trabajador != null;
            rutina.setNombre(rutinaDTO.getNombre());
            rutina.setIdcliente(cliente);
            rutina.setIdtrabajador(trabajador);

            rutina.setIdrutina(rutinaDTO.getIdrutina());

            rutinaRepository.saveAndFlush(rutina);
    }

    //@Pablo
    public List<RutinaDTO> findAllByTrabajador(Integer id) {
        List<RutinaDTO> lista = new ArrayList<>();
        List<Rutina> rutinas = rutinaRepository.findAllByTrabajador(id);
        for(Rutina rutina : rutinas){
            RutinaDTO rutinaDTO = rutina.toDTO();
            lista.add(rutinaDTO);
        }
        return lista;
    }

    //@Pablo
    public void deleteById(int id) {
        rutinaRepository.deleteById(id);
    }

    //@Pablo
    public List<RutinaDTO> findByFiltro(FiltroRutinas filtro, Integer idTrabajador) {
        String nombre = filtro.getNombre() == null ? "" : filtro.getNombre();

        List<Rutina> lista = filtro.getIdcliente() == null || filtro.getIdcliente().equals("-1") ?  rutinaRepository.findByFiltroNombre(nombre, idTrabajador) :
                rutinaRepository.findByFiltroNombreAndId(nombre, filtro.getIdcliente(), idTrabajador);

        List<RutinaDTO> listaDTO = new ArrayList<>();

        for(Rutina rutina : lista){
             listaDTO.add(rutina.toDTO());
        }

        return listaDTO;
    }

    //@Pablo
    public List<RutinaDTO> findAllByTrabajadorAndCliente(Integer idtrabajador, Integer idcliente) {
        List<Rutina> rutinas = rutinaRepository.findAllByTrabajadorAndCliente(idtrabajador, idcliente);
        List<RutinaDTO> listaDTO = new ArrayList<>();
        for(Rutina rutina : rutinas){
            listaDTO.add(rutina.toDTO());
        }
        return listaDTO;
    }

    //Nacho
    public List<RutinaDTO> findByName(String nombre, Integer idCliente) {
        List<Rutina> r = rutinaRepository.findByNombreAndClienteID(nombre, idCliente);
        List<RutinaDTO> listaDTO = new ArrayList<>();
        for(Rutina rutina : r){
            listaDTO.add(rutina.toDTO());
        }

        return listaDTO;
    }
}
