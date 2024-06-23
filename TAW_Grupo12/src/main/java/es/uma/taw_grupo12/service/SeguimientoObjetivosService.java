/**
 * @author Ignacio Morillas Rossell (1/3)
 * @author 	PABLO SENCIALES DE LA HIGUERA (2/3)
 */

package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.ClienteRepository;
import es.uma.taw_grupo12.dao.RutinaRepository;
import es.uma.taw_grupo12.dao.SeguimientoObjetivosRepository;
import es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO;
import es.uma.taw_grupo12.entity.Cliente;
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
    @Autowired
    RutinaRepository rutinaRepository;
    @Autowired
    ClienteRepository clienteRepository;

    //@Pablo
    public List<SeguimientoObjetivosDTO> findByClienteAndRutina(Integer idcliente, Integer idrutina) {
        List<SeguimientoObjetivos> seguimientoObjetivos = seguimientoObjetivosRepository.findByClienteAndRutina(idcliente, idrutina);
        List<SeguimientoObjetivosDTO> seguimientoObjetivosDTOS = new ArrayList<>();
        for(SeguimientoObjetivos s : seguimientoObjetivos){
            seguimientoObjetivosDTOS.add(s.toDTO());
        }
        return seguimientoObjetivosDTOS;
    }


    //@Pablo
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

    //Nacho
    public void guardarSeguimiento(SeguimientoObjetivosDTO seguimiento) {
        SeguimientoObjetivos s;
        List<SeguimientoObjetivos> ls = seguimientoObjetivosRepository.findByNombreEjercicioAndFecha(
                seguimiento.getNombreejercicio(), seguimiento.getCliente(),
                seguimiento.getRutina(), new Date(seguimiento.getFecha().getTime()));

        if(ls != null && !ls.isEmpty())
            s = ls.get(0); //El compilador no encuentra getFirst()?
        else
            s = new SeguimientoObjetivos();

        s.setRutina(rutinaRepository.findById(seguimiento.getRutina()).orElse(null));
        s.setCliente(clienteRepository.findById(seguimiento.getCliente()).orElse(null));
        s.setFecha(seguimiento.getFecha());
        s.setRealizado(seguimiento.getRealizado());
        s.setPesorealizado(seguimiento.getPesorealizado());
        s.setRepeticionesrealizadas(seguimiento.getRepeticionesrealizadas());
        s.setSeriesrealizadas(seguimiento.getSeriesrealizadas());
        s.setObservaciones(seguimiento.getObservaciones());
        s.setPesoobjetivo(seguimiento.getPesoobjetivo());
        s.setSeriesobjetivo(seguimiento.getSeriesobjetivo());
        s.setRepeticionesobjetivo(seguimiento.getRepeticionesobjetivo());
        s.setNombreejercicio(seguimiento.getNombreejercicio());

        seguimientoObjetivosRepository.save(s);
    }
}
