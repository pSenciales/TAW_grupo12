package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.entity.Trabajador;
import es.uma.taw_grupo12.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorService {

    @Autowired
    TrabajadorRepository trabajadorRepository;

    public TrabajadorDTO findById(Integer id) {
        Trabajador trabajador = trabajadorRepository.findById(id).orElse(null);
        assert trabajador != null;

        return trabajador.toDTO();
    }
}
