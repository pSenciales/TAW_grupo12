package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.ClienteRepository;
import es.uma.taw_grupo12.dto.ClienteDTO;
import es.uma.taw_grupo12.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {


    @Autowired
    protected ClienteRepository clienteRepository;

    //@Victoria
    public List<ClienteDTO> listarClientesDTO () {
        List<Cliente> clientes = this.clienteRepository.findAll();

        List<ClienteDTO> clientesDTO = new ArrayList<>();
        for(Cliente c : clientes){
            clientesDTO.add(c.toDTO());
        }
        return clientesDTO;
    }
    //@Victoria
}
