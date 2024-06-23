/**
 * @author María Victoria Huesca Peláez (6/8)
 * @author Ignacio Morillas Rosell (1/8)
 * @author 	PABLO SENCIALES DE LA HIGUERA (1/8)
 */

package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.ClienteRepository;
import es.uma.taw_grupo12.dao.TrabajadorRepository;
import es.uma.taw_grupo12.dto.ClienteDTO;
import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.Trabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {


    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected TrabajadorRepository trabajadorRepository;

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

    public void eliminarCliente(Integer idCliente) {
        Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
        List<Trabajador> trabajadores = this.trabajadorRepository.findTrabajadoresAsociados(idCliente);

        for(Trabajador t : trabajadores){
            List<Cliente> clientes = t.getClienteList();
            clientes.remove(cliente);
            this.trabajadorRepository.save(t);
        }
        this.clienteRepository.deleteById(idCliente);

    }

    public void guardarCliente(ClienteDTO cliente) throws IOException {

        Cliente miCliente = this.clienteRepository.findById(cliente.getIdcliente()).orElse(null);
        if(cliente != null){
            miCliente.setNombre(cliente.getNombre());
            miCliente.setEmail(cliente.getEmail());
            miCliente.setContrasenya(cliente.getContrasenya());
            if (cliente.getImagenperfilFile() != null && !cliente.getImagenperfilFile().isEmpty()) {
                MultipartFile myFile = cliente.getImagenperfilFile();
                byte[] imagenBytes = myFile.getBytes();
                miCliente.setImagenperfil(imagenBytes);
            }
            miCliente.setPeso(cliente.getPeso());
            miCliente.setAltura(cliente.getAltura());
            if(!cliente.getAlergias().isEmpty()){
                miCliente.setAlergias(cliente.getAlergias());
            }

            this.clienteRepository.save(miCliente);
        }
    }

    public List<Cliente>  buscarClienteNombreoEmail(ClienteDTO cliente) {
        Cliente original = this.clienteRepository.findById(cliente.getIdcliente()).orElse(null);
        if(original.getEmail().equals(cliente.getEmail())){
            if(original.getNombre().equals(cliente.getNombre())){
                return null;
            }
            return this.clienteRepository.findAllByEmailorNombre("", cliente.getNombre(), cliente.getIdcliente());
        }
        if(original.getNombre().equals(cliente.getNombre())){
            if(original.getEmail().equals(cliente.getEmail())){
                return null;
            }
            return this.clienteRepository.findAllByEmailorNombre(cliente.getEmail(), "", cliente.getIdcliente());
        }
        return  this.clienteRepository.findAllByEmailorNombre(cliente.getEmail(), cliente.getNombre(), cliente.getIdcliente());
    }
    //@Victoria

    //@Pablo
    public List<ClienteDTO> findByTrabajador(Integer idtrabajador) {
        List<ClienteDTO> clientesDTO = new ArrayList<>();
        List<Cliente> cliente = clienteRepository.findByTrabajador(idtrabajador);

        for(Cliente c : cliente){
            clientesDTO.add(c.toDTO());
        }
        return clientesDTO;
    }

    //Nacho
    public void actualizarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(clienteDTO.getIdcliente()).orElse(null);

        if (cliente != null) {
            cliente.setNombre(clienteDTO.getNombre());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setPeso(clienteDTO.getPeso());
            cliente.setAltura(clienteDTO.getAltura());
            cliente.setAlergias(clienteDTO.getAlergias());

            clienteRepository.save(cliente);
        }
    }

}
