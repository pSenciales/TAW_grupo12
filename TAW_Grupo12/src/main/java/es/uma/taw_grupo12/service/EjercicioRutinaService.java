package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dto.EjercicioRutinaDTO;
import es.uma.taw_grupo12.entity.Ejercicio;
import es.uma.taw_grupo12.entity.EjercicioRutina;
import es.uma.taw_grupo12.entity.EjercicioRutinaPK;
import es.uma.taw_grupo12.entity.Rutina;
import es.uma.taw_grupo12.repository.EjercicioRepository;
import es.uma.taw_grupo12.repository.EjercicioRutinaRepository;
import es.uma.taw_grupo12.repository.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EjercicioRutinaService {

    @Autowired
    private EjercicioRutinaRepository ejercicioRutinaRepository;

    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;

    public void save(EjercicioRutinaDTO ejercicioRutinaDTO) {
        /*int ejercicioRutinaId = ejercicioRutinaRepository.findOrderedById();
        EjercicioRutinaPK pk = new EjercicioRutinaPK();
        pk.setIdrutina(ejercicioRutinaDTO.getRutina());
        pk.setIdejercicio(ejercicioRutinaDTO.getEjercicio());
        pk.setIdejerciciorutina(ejercicioRutinaId);


        ejercicioRutina.setEjercicioRutinaPK(pk);
        */
        EjercicioRutina ejercicioRutina = new EjercicioRutina();

        Rutina rutina = rutinaRepository.findById(ejercicioRutinaDTO.getRutina()).orElse(null);
        assert(rutina!=null);
        ejercicioRutina.setRutina(rutina);

        Ejercicio ejercicio = ejercicioRepository.findById(ejercicioRutinaDTO.getEjercicio()).orElse(null);
        assert(ejercicio!=null);
        ejercicioRutina.setEjercicio(ejercicio);

        ejercicioRutina.setPeso(ejercicioRutinaDTO.getPeso());
        ejercicioRutina.setRepeticiones(ejercicioRutinaDTO.getRepeticiones());
        ejercicioRutina.setSeries(ejercicioRutinaDTO.getSeries());
        ejercicioRutina.setDiassemana(ejercicioRutinaDTO.getDiassemana());

        String orden = ejercicioRutinaRepository.findOrden();
        int ordenInt = -1;
        if(orden==null)
            ordenInt = 0;
        else
            ordenInt = Integer.parseInt(orden);
        ejercicioRutina.setOrden(ordenInt);

        ejercicioRutinaRepository.saveAndFlush(ejercicioRutina);


    }
}
