package es.uma.taw_grupo12.service;


import es.uma.taw_grupo12.dto.RutinaDTO;
import es.uma.taw_grupo12.entity.Rutina;
import es.uma.taw_grupo12.dao.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutinaService {
    @Autowired
    private RutinaRepository rutinaRepository;

    public RutinaDTO findById(Integer id){
        Rutina rutina = rutinaRepository.findById(id).orElse(null);
        assert rutina != null;

        RutinaDTO rutinaDTO = rutina.toDTO();
        return rutinaDTO;

    }


}
