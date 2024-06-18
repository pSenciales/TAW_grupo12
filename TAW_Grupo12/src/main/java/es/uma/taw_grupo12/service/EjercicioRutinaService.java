package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dto.EjercicioRutinaDTO;
import es.uma.taw_grupo12.entity.Ejercicio;
import es.uma.taw_grupo12.entity.EjercicioRutina;
import es.uma.taw_grupo12.entity.Rutina;
import es.uma.taw_grupo12.dao.EjercicioRepository;
import es.uma.taw_grupo12.dao.EjercicioRutinaRepository;
import es.uma.taw_grupo12.dao.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EjercicioRutinaService {

    @Autowired
    private EjercicioRutinaRepository ejercicioRutinaRepository;

    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;

    public void save(EjercicioRutinaDTO ejercicioRutinaDTO) {
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
        ejercicioRutina.setEjercicioRutinaPK(ejercicioRutinaDTO.getEjercicioRutinaPK());

        EjercicioRutina ejercicioRutinaFound = ejercicioRutinaRepository.findById(ejercicioRutinaDTO.getEjercicioRutinaPK()).orElse(null);
        String orden = ejercicioRutinaRepository.findByOrden(rutina.getIdrutina(), ejercicioRutina.getDiassemanaString());
        int ordenInt = (orden == null) ? 0 : (Integer.parseInt(orden)+1);

        ejercicioRutina.setOrden(ordenInt);


        if(ejercicioRutinaFound!=null && !Objects.equals(ejercicioRutinaFound.getDiassemana(), ejercicioRutina.getDiassemana())) {
            int rutinaid = ejercicioRutinaFound.getRutina().getIdrutina();
            String diassemana = ejercicioRutinaFound.getDiassemana();
            int ordenprev = ejercicioRutinaFound.getOrden();
            List<EjercicioRutina> rutinas = ejercicioRutinaRepository.findMayoresOrden(rutinaid, diassemana, ordenprev);
            desfragmentar(rutinas);
        }
        ejercicioRutinaRepository.save(ejercicioRutina);




    }

    public List<EjercicioRutinaDTO> findAllByRutinaId(Integer id) {
        List<EjercicioRutina> ejercicioRutinas = ejercicioRutinaRepository.findAllByRutinaId(id);
        List<EjercicioRutinaDTO> ejercicioRutinaDTOs = new ArrayList<>();

        for(EjercicioRutina ejercicioRutina : ejercicioRutinas){
            ejercicioRutinaDTOs.add(ejercicioRutina.toDTO());
        }
        return  ejercicioRutinaDTOs;
    }

    public void cambiarOrden(Integer id, boolean b) {
        EjercicioRutina curr = ejercicioRutinaRepository.findById(id).orElse(null);
        assert(curr!=null);
        int rutina = curr.getRutina().getIdrutina();
        String diassemana = curr.getDiassemana();
        int orden = b ? curr.getOrden() - 1 : curr.getOrden() + 1;

        EjercicioRutina other = ejercicioRutinaRepository.findSuperiorOrInferior(rutina,diassemana, orden).orElse(null);
        if(other !=null){
            int aux = curr.getOrden();
            curr.setOrden(other.getOrden());
            other.setOrden(aux);
            ejercicioRutinaRepository.save(curr);
            ejercicioRutinaRepository.saveAndFlush(other);
        }

    }

    public EjercicioRutinaDTO findById(Integer id) {
        EjercicioRutina ejercicioRutina = ejercicioRutinaRepository.findById(id).orElse(null);
        assert(ejercicioRutina!=null);
        return ejercicioRutina.toDTO();
    }

    public void deleteById(Integer id) {
        EjercicioRutina ejercicioRutina = ejercicioRutinaRepository.findById(id).orElse(null);
        assert ejercicioRutina != null;
        String diassemana = ejercicioRutina.getDiassemana();
        int orden = ejercicioRutina.getOrden();
        int rutina = ejercicioRutina.getRutina().getIdrutina();
        ejercicioRutinaRepository.deleteById(id);

        List<EjercicioRutina> rutinas = ejercicioRutinaRepository.findMayoresOrden(rutina, diassemana, orden);
        desfragmentar(rutinas);

    }
    private void desfragmentar(List<EjercicioRutina> rutinas){
        for(EjercicioRutina ejercicioRutina : rutinas){
            ejercicioRutina.setOrden(ejercicioRutina.getOrden()-1);
        }
        ejercicioRutinaRepository.saveAllAndFlush(rutinas);
    }
}
