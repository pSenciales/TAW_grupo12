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
        return this.listarClientesDTO("");
    }

    public List<ClienteDTO> listarClientesDTO (String busqueda) {
        List<Cliente> clientes = this.clienteRepository.findByEmailorNombre(busqueda);

        List<ClienteDTO> clientesDTO = new ArrayList<>();
        for(Cliente c : clientes){
            clientesDTO.add(c.toDTO());
        }
        return clientesDTO;
    }

    public ClienteDTO buscarClienteId(Integer idCliente) {
        Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
        if (cliente != null) {
            return cliente.toDTO();
        } else {
            return null;
        }
    }
    //@Victoria
}
