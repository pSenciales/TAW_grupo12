package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.SeguimientoObjetivosRepository;
import es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO;
import es.uma.taw_grupo12.entity.SeguimientoObjetivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SegumientoObjetivosService {
    @Autowired
    SeguimientoObjetivosRepository seguimientoObjetivosRepository;

    public List<SeguimientoObjetivosDTO> findByClienteAndRutina(Integer idcliente, Integer idrutina) {
        List<SeguimientoObjetivos> seguimientoObjetivos = seguimientoObjetivosRepository.findByClienteAndRutina(idcliente, idrutina);
        List<SeguimientoObjetivosDTO> seguimientoObjetivosDTOS = new ArrayList<>();
        for(SeguimientoObjetivos s : seguimientoObjetivos){
            seguimientoObjetivosDTOS.add(s.toDTO());
        }
        return seguimientoObjetivosDTOS;
    }

    public List<SeguimientoObjetivosDTO> findByNombreEjercicio(String nombre, Integer idcliente, Integer idrutina) {
        List<SeguimientoObjetivos> seguimientoObjetivos = seguimientoObjetivosRepository.findByNombreEjercicio(nombre, idcliente, idrutina);
        List<SeguimientoObjetivosDTO> seguimientoObjetivosDTOS = new ArrayList<>();
        for(SeguimientoObjetivos s : seguimientoObjetivos){
            seguimientoObjetivosDTOS.add(s.toDTO());
        }
        return seguimientoObjetivosDTOS;
    }
}
