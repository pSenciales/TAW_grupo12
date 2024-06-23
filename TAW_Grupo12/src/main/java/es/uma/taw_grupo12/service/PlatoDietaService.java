/**
 * @author Ignacio Morillas Rossell (1/12)
 */
package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.PlatodietaRepository;
import es.uma.taw_grupo12.dto.PlatoDietaDTO;
import es.uma.taw_grupo12.entity.PlatoDieta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlatoDietaService {

    @Autowired
    PlatodietaRepository platodietaRepository;

    //Nacho
    public List<PlatoDietaDTO> findAllByDietaId(Integer id) {
        List<PlatoDieta> platosDieta = platodietaRepository.findByDietaId(id);
        List<PlatoDietaDTO> platoDietaDTOS = new ArrayList<>();

        for (PlatoDieta platoDieta : platosDieta) {
            platoDietaDTOS.add(platoDieta.toDTO());
        }
        return platoDietaDTOS;
    }
}
