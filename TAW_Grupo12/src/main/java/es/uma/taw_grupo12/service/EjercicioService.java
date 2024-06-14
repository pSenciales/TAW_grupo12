package es.uma.taw_grupo12.service;


import es.uma.taw_grupo12.dto.EjercicioDTO;
import es.uma.taw_grupo12.entity.Ejercicio;
import es.uma.taw_grupo12.dao.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;


    //@Pablo
    public List<EjercicioDTO> getFuerza() {
        List<Ejercicio> ejerciciosList = ejercicioRepository.getFuerza();
        List<EjercicioDTO> ejerciciosDTO = new ArrayList<>();
        for(Ejercicio ej : ejerciciosList){
            EjercicioDTO aux = ej.toDTO();
            ejerciciosDTO.add(aux);
        }
        return ejerciciosDTO;
    }
    //@Pablo
}
