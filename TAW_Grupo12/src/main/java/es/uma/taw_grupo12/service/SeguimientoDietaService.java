package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.SeguimientoDietaRepository;
import es.uma.taw_grupo12.dto.SeguimientoDietaDTO;
import es.uma.taw_grupo12.entity.SeguimientoDieta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeguimientoDietaService {

    @Autowired
    SeguimientoDietaRepository seguimientoDietaRepository;

    public List<SeguimientoDietaDTO> findByIdClienteAndIdDieta(Integer idCliente, Integer idDieta){
        List<SeguimientoDieta> seguimientos = seguimientoDietaRepository.findByClienteIdAndDietaId(idCliente, idDieta);

        List<SeguimientoDietaDTO> seguimientoDietaDTOS = new ArrayList<>();
        for(SeguimientoDieta s : seguimientos){
            seguimientoDietaDTOS.add(s.toDTO());
        }

        return seguimientoDietaDTOS;
    }
}
