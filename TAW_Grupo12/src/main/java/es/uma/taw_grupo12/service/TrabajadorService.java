package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.ClienteRepository;
import es.uma.taw_grupo12.dao.TrabajadorRepository;
import es.uma.taw_grupo12.dto.ClienteDTO;
import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.Trabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrabajadorService {

    @Autowired
    TrabajadorRepository trabajadorRepository;

    @Autowired
    ClienteRepository clienteRepository;

    //@Victoria
    public List<TrabajadorDTO> listarTrabajadoresDTO () {
        List<Trabajador> trabajadores = this.trabajadorRepository.findAll();

        List<TrabajadorDTO> trabajadoresDTO = new ArrayList<>();
        for(Trabajador t : trabajadores){
            trabajadoresDTO.add(t.toDTO());
        }

        return trabajadoresDTO;
    }

    public List<TrabajadorDTO> listarTrabajadoresDTO (String busqueda) {
        List<Trabajador> trabajadores = this.trabajadorRepository.findByEmailorNombre(busqueda);

        List<TrabajadorDTO> trabajadoresDTO = new ArrayList<>();
        for(Trabajador t : trabajadores){
            trabajadoresDTO.add(t.toDTO());
        }

        return trabajadoresDTO;
    }

    public List<TrabajadorDTO> listarTrabajadoresDTOTipo(String tipo) {
        List<Trabajador> trabajadores = this.trabajadorRepository.findByTipo(tipo);

        List<TrabajadorDTO> trabajadoresDTO = new ArrayList<>();
        for(Trabajador t : trabajadores){
            trabajadoresDTO.add(t.toDTO());
        }

        return trabajadoresDTO;
    }

    public List<TrabajadorDTO> listarTrabajadoresDTOTipo(String tipo, String busqueda) {
        List<Trabajador> trabajadores = this.trabajadorRepository.findByTipoAndNombreorEmail(tipo, busqueda);

        List<TrabajadorDTO> trabajadoresDTO = new ArrayList<>();
        for(Trabajador t : trabajadores){
            trabajadoresDTO.add(t.toDTO());
        }

        return trabajadoresDTO;
    }

    public void eliminarTrabajador(Integer idTrabajador) {
        Trabajador trabajador = this.trabajadorRepository.findById(idTrabajador).orElse(null);
        List<Cliente> clientes = this.clienteRepository.findClientesAsociados(idTrabajador);
        for(Cliente c : clientes){
            List<Trabajador> trabajadores = c.getTrabajadorList();
            trabajadores.remove(trabajador);
            this.clienteRepository.save(c);
        }
        this.trabajadorRepository.deleteById(idTrabajador);
    }
    //@Victoria
}
