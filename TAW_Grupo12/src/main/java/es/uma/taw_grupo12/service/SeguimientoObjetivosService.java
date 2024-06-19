package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.SeguimientoObjetivosRepository;
import es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO;
import es.uma.taw_grupo12.entity.SeguimientoObjetivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeguimientoObjetivosService {
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


    public List<SeguimientoObjetivosDTO> findByNombreEjercicioAndFecha(String nombre, Integer idcliente, Integer idrutina, Date fecha) {

        List<SeguimientoObjetivos> seguimientoObjetivos = fecha == null ?
                seguimientoObjetivosRepository.findByNombreEjercicio(nombre, idcliente, idrutina)
                : seguimientoObjetivosRepository.findByNombreEjercicioAndFecha(nombre, idcliente, idrutina, fecha);
        List<SeguimientoObjetivosDTO> seguimientoObjetivosDTOS = new ArrayList<>();
        for(SeguimientoObjetivos s : seguimientoObjetivos){
            seguimientoObjetivosDTOS.add(s.toDTO());
        }
        return seguimientoObjetivosDTOS;
    }
}
