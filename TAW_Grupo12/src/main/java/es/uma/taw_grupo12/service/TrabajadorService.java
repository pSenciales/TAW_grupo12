package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.ClienteRepository;
import es.uma.taw_grupo12.dao.TrabajadorRepository;
import es.uma.taw_grupo12.dto.TrabajadorDTO;
import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.Trabajador;
import es.uma.taw_grupo12.dao.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrabajadorService {

    @Autowired
    TrabajadorRepository trabajadorRepository;

    @Autowired
    ClienteRepository clienteRepository;
    public TrabajadorDTO findById(Integer id) {
        Trabajador trabajador = trabajadorRepository.findById(id).orElse(null);

        return trabajador.toDTO();
    }
    //@Victoria
    public List<TrabajadorDTO> listarTrabajadoresDTO () {
        List<Trabajador> trabajadores = this.trabajadorRepository.findAll();

        List<TrabajadorDTO> trabajadoresDTO = new ArrayList<>();
        for(Trabajador t : trabajadores){
            trabajadoresDTO.add(t.toDTO());
        }

        return trabajadoresDTO;
    }

    public List<TrabajadorDTO> listarTrabajadoresDTO (String busqueda) {
        List<Trabajador> trabajadores = this.trabajadorRepository.findByEmailorNombre(busqueda);

        List<TrabajadorDTO> trabajadoresDTO = new ArrayList<>();
        for(Trabajador t : trabajadores){
            trabajadoresDTO.add(t.toDTO());
        }

        return trabajadoresDTO;
    }

    public List<TrabajadorDTO> listarTrabajadoresDTOTipo(String tipo) {
        List<Trabajador> trabajadores = this.trabajadorRepository.findByTipo(tipo);

        List<TrabajadorDTO> trabajadoresDTO = new ArrayList<>();
        for(Trabajador t : trabajadores){
            trabajadoresDTO.add(t.toDTO());
        }

        return trabajadoresDTO;
    }

    public List<TrabajadorDTO> listarTrabajadoresDTOTipo(String tipo, String busqueda) {
        List<Trabajador> trabajadores = this.trabajadorRepository.findByTipoAndNombreorEmail(tipo, busqueda);

        List<TrabajadorDTO> trabajadoresDTO = new ArrayList<>();
        for(Trabajador t : trabajadores){
            trabajadoresDTO.add(t.toDTO());
        }

        return trabajadoresDTO;
    }

    public void eliminarTrabajador(Integer idTrabajador) {
        Trabajador trabajador = this.trabajadorRepository.findById(idTrabajador).orElse(null);
        List<Cliente> clientes = this.clienteRepository.findClientesAsociados(idTrabajador);
        for(Cliente c : clientes){
            List<Trabajador> trabajadores = c.getTrabajadorList();
            trabajadores.remove(trabajador);
            this.clienteRepository.save(c);
        }
        this.trabajadorRepository.deleteById(idTrabajador);
    }

    public void guardarTrabajador(TrabajadorDTO trabajador) throws IOException {


        Trabajador miTrabajador = this.trabajadorRepository.findById(trabajador.getIdtrabajador()).orElse(null);
        if(trabajador != null){
            miTrabajador.setNombre(trabajador.getNombre());
            miTrabajador.setEmail(trabajador.getEmail());
            miTrabajador.setContrasenya(trabajador.getContrasenya());
            if (trabajador.getImagenperfilFile() != null && !trabajador.getImagenperfilFile().isEmpty()) {
                MultipartFile myFile = trabajador.getImagenperfilFile();
                byte[] imagenBytes = myFile.getBytes();
                miTrabajador.setImagenperfil(imagenBytes);
            }

            this.trabajadorRepository.save(miTrabajador);
        }
    }

    public List<Trabajador>  buscarTrabajadorNombreoEmail(TrabajadorDTO trabajador) {
        Trabajador original = this.trabajadorRepository.findById(trabajador.getIdtrabajador()).orElse(null);
        if(original.getEmail().equals(trabajador.getEmail())){
            if(original.getNombre().equals(trabajador.getNombre())){
                return null;
            }
            return this.trabajadorRepository.findAllByEmailorNombre("", trabajador.getNombre());
        }
        if(original.getNombre().equals(trabajador.getNombre())){
            if(original.getEmail().equals(trabajador.getEmail())){
                return null;
            }
            return this.trabajadorRepository.findAllByEmailorNombre(trabajador.getEmail(), "");
        }
        return  this.trabajadorRepository.findAllByEmailorNombre(trabajador.getEmail(), trabajador.getNombre());
    }
    //@Victoria
}
