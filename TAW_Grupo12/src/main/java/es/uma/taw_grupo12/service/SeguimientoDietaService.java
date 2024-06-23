/**
 * @author Chen Chen Longxiang (1/3)
 * @author Ignacio morillas rosell (2/3)
 */

package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.ClienteRepository;
import es.uma.taw_grupo12.dao.DietaRepository;
import es.uma.taw_grupo12.dao.SeguimientoDietaRepository;
import es.uma.taw_grupo12.dto.SeguimientoDietaDTO;
import es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO;
import es.uma.taw_grupo12.entity.SeguimientoDieta;
import es.uma.taw_grupo12.entity.SeguimientoObjetivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeguimientoDietaService {

    @Autowired
    SeguimientoDietaRepository seguimientoDietaRepository;
    @Autowired
    DietaRepository dietaRepository;
    @Autowired
    ClienteRepository clienteRepository;

    public List<SeguimientoDietaDTO> findByIdClienteAndIdDieta(Integer idCliente, Integer idDieta){
        List<SeguimientoDieta> seguimientos = seguimientoDietaRepository.findByClienteIdAndDietaId(idCliente, idDieta);

        List<SeguimientoDietaDTO> seguimientoDietaDTOS = new ArrayList<>();
        for(SeguimientoDieta s : seguimientos){
            seguimientoDietaDTOS.add(s.toDTO());
        }

        return seguimientoDietaDTOS;
    }

    //Nacho
    public List<SeguimientoDietaDTO> findByNombrePlatoAndFecha(String nombre, Integer idcliente, Integer idDieta, Date fecha) {

        List<SeguimientoDieta> seguimientoDietas = fecha == null ?
                seguimientoDietaRepository.findByNombrePlato(nombre, idcliente, idDieta)
                : seguimientoDietaRepository.findByNombrePlatoAndFecha(nombre, idcliente, idDieta, fecha);
        List<SeguimientoDietaDTO> seguimientoDietaDTOS = new ArrayList<>();
        for(SeguimientoDieta s : seguimientoDietas){
            seguimientoDietaDTOS.add(s.toDTO());
        }
        return seguimientoDietaDTOS;
    }

    //Nacho
    public void guardarSeguimiento(SeguimientoDietaDTO seguimiento) {
        SeguimientoDieta s;
        List<SeguimientoDieta> ls = seguimientoDietaRepository.findByNombrePlatoAndFecha(
                seguimiento.getNombrePlato(), seguimiento.getIdCliente(),
                seguimiento.getIdDieta(), new Date(seguimiento.getFecha().getTime()));

        if(ls != null && !ls.isEmpty())
            s = ls.get(0); //El compilador no encuentra getFirst()?
        else
            s = new SeguimientoDieta();

        s.setDieta(dietaRepository.findById(seguimiento.getIdDieta()).orElse(null));
        s.setCliente(clienteRepository.findById(seguimiento.getIdCliente()).orElse(null));
        s.setFecha(seguimiento.getFecha());
        s.setComido(seguimiento.getComido());
        s.setCantidad(seguimiento.getCantidad());
        s.setObservaciones(seguimiento.getObservaciones());
        s.setCantidadobjetivo(seguimiento.getCantidadObjeto());
        s.setNombreplato(seguimiento.getNombrePlato());

        seguimientoDietaRepository.save(s);
    }
}
