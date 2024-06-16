package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.TrabajadorRepository;
import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.entity.Trabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrabajadorService {

    @Autowired
    TrabajadorRepository trabajadorRepository;

    //@Victoria
    public List<TrabajadorDTO> listarTrabajadoresDTO () {
        List<Trabajador> trabajadores = this.trabajadorRepository.findAll();

        List<TrabajadorDTO> trabajadoresDTO = new ArrayList<>();
        for(Trabajador t : trabajadores){
            trabajadoresDTO.add(t.toDTO());
        }

        return trabajadoresDTO;
    }
}
