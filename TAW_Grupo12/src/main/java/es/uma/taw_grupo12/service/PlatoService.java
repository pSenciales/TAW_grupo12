package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.PlatoRepository;
import es.uma.taw_grupo12.dto.PlatoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.uma.taw_grupo12.entity.Plato;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlatoService {

    @Autowired
    protected PlatoRepository platoRepository;

    public List<PlatoDTO> listarPlatosDTO() {
        List<Plato> platos =  this.platoRepository.findAll();
        List<PlatoDTO> platosDTO = new ArrayList<>();
        for(Plato p : platos){
            platosDTO.add(p.toDTO());
        }
        return platosDTO;
    }
}
