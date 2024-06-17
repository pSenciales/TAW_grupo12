package es.uma.taw_grupo12.service;


import es.uma.taw_grupo12.dto.RutinaDTO;
import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.Rutina;
import es.uma.taw_grupo12.repository.ClienteRepository;
import es.uma.taw_grupo12.repository.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutinaService {
    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    //@Pablo
    public RutinaDTO findById(Integer id){
        Rutina rutina = rutinaRepository.findById(id).orElse(null);
        assert rutina != null;

        RutinaDTO rutinaDTO = rutina.toDTO();
        return rutinaDTO;

    }
    //@Pablo

    public RutinaDTO save(RutinaDTO rutinaDTO){
        Rutina rutina = new Rutina();
        Cliente cliente = clienteRepository.findById(1).orElse(null);
        assert cliente != null;
        rutina.setNombre(rutinaDTO.getNombre());
        rutina.setIdcliente(cliente);
        rutinaRepository.saveAndFlush(rutina);
        Rutina recuperada = rutinaRepository.findByClienteAndName(cliente.getIdcliente(), rutinaDTO.getNombre());

        return rutina.toDTO();
    }


}
