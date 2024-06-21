package es.uma.taw_grupo12.service;


import es.uma.taw_grupo12.dto.EjercicioDTO;
import es.uma.taw_grupo12.entity.Ejercicio;
import es.uma.taw_grupo12.dao.EjercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    //@Guillermo
    public List<EjercicioDTO> getAll() {
        List<Ejercicio> ejerciciosList = ejercicioRepository.findAll();
        List<EjercicioDTO> ejerciciosDTO = new ArrayList<>();
        for (Ejercicio ej : ejerciciosList) {
            EjercicioDTO aux = ej.toDTO();
            ejerciciosDTO.add(aux);
        }
        return ejerciciosDTO;
    }

    public List<String> getTipos() {
        List<String> tipos = new ArrayList<>();
        List<Ejercicio> ejerciciosList = ejercicioRepository.findAll();

        for (Ejercicio ej : ejerciciosList) {
            if (!tipos.contains(ej.getTipo())) {
                tipos.add(ej.getTipo());
            }
        }
        return tipos;
    }

    public List<EjercicioDTO> getByTipos(List<String> filtrotipos) {
        List<Ejercicio> ejerciciosList = ejercicioRepository.findAll();
        List<EjercicioDTO> ejerciciosDTO = new ArrayList<>();

        for (Ejercicio ej : ejerciciosList) {
            if (filtrotipos.contains(ej.getTipo())) {
                EjercicioDTO aux = ej.toDTO();
                ejerciciosDTO.add(aux);
            }

        }

        return ejerciciosDTO;
    }

}
