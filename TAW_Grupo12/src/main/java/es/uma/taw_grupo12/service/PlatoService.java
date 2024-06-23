

package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.PlatoRepository;
import es.uma.taw_grupo12.dao.PlatodietaRepository;
import es.uma.taw_grupo12.dto.PlatoDTO;
import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.PlatoDieta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.uma.taw_grupo12.entity.Plato;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlatoService {

    @Autowired
    protected PlatoRepository platoRepository;

    @Autowired
    protected PlatodietaRepository platodietaRepository;

    //@Victoria
    public List<PlatoDTO> listarPlatosDTO() {
        return this.listarplatosDTO("");
    }

    public List<PlatoDTO> listarplatosDTO(String busqueda) {
        List<Plato> platos = this.platoRepository.findAllByNombre(busqueda);
        List<PlatoDTO> platosDTO = new ArrayList<>();
        for(Plato p : platos){
            platosDTO.add(p.toDTO());
        }
        return platosDTO;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<PlatoDTO> listarPlatosDTOAlergenos(List<String> alergenos, String busqueda) {

        //HE TENIDO QUE USAR CONSULTA DINAMICA PORQUE JPQL NO PERMITE USAR LIKE CON UNA LISTA DE PARAMETROS

        StringBuilder sb = new StringBuilder("SELECT p FROM Plato p");
        if (!alergenos.isEmpty()) {
            sb.append(" WHERE");
            for (int i = 0; i < alergenos.size(); i++) {
                sb.append(" (p.alergenos IS NULL OR p.alergenos NOT LIKE :alergeno").append(i).append(")");
                if (i < alergenos.size() - 1) {
                    sb.append(" AND");
                }
            }
            sb.append(" AND p.nombre LIKE :busqueda");
        }

        TypedQuery<Plato> query = entityManager.createQuery(sb.toString(), Plato.class);
        for (int i = 0; i < alergenos.size(); i++) {
            query.setParameter("alergeno" + i, "%" + alergenos.get(i) + "%");
        }
        query.setParameter("busqueda", "%" + busqueda + "%");

        List<Plato> platos = query.getResultList();
        List<PlatoDTO> platosDTO = new ArrayList<>();
        for(Plato p : platos){
            platosDTO.add(p.toDTO());
        }
        return platosDTO;
    }

    public void guardarPlato(PlatoDTO platoDTO) throws IOException {
        Plato miPlato = this.platoRepository.findById(platoDTO.getIdplato()).orElse(null);
        if(miPlato != null){
            miPlato.setNombre(platoDTO.getNombre());
            if(!platoDTO.getDescripcion().isEmpty()){
                miPlato.setDescripcion(platoDTO.getDescripcion());
            }
            if (platoDTO.getVideoFile() != null && !platoDTO.getVideoFile().isEmpty()) {
                MultipartFile myFile = platoDTO.getVideoFile();
                byte[] imagenBytes = myFile.getBytes();
                miPlato.setVideo(imagenBytes);
            }
            if(!platoDTO.getAlergenos().isEmpty()){
                miPlato.setAlergenos(platoDTO.getAlergenos());
            }
            this.platoRepository.save(miPlato);
        }
    }

    public List<Plato> buscarPlatoNombre(PlatoDTO platoDTO) {
        Plato original = this.platoRepository.findById(platoDTO.getIdplato()).orElse(null);
        if(original.getNombre().equals(platoDTO.getNombre())){
            return null;
        }
        return  this.platoRepository.findAllByNombre(platoDTO.getNombre());
    }

    public List<Plato> buscarPlatoNombre(String nombre) {
        List<Plato> platos = this.platoRepository.findByNombre(nombre);
       return platos;
    }

    public void eliminarPlato(Integer idplato) {
        Plato plato = this.platoRepository.findById(idplato).orElse(null);
        List<PlatoDieta> platosDieta = this.platodietaRepository.findPlatosDieta(idplato);

        this.platodietaRepository.deleteAll(platosDieta);

        this.platoRepository.deleteById(idplato);

    }

    public void crearPlato(PlatoDTO platoDTO) throws IOException {
        List<Plato> existe = this.platoRepository.findByNombre(platoDTO.getNombre());
        if(existe != null && !existe.isEmpty()){
            return;
        }
        Plato plato = new Plato();
        plato.setNombre(platoDTO.getNombre());
        plato.setDescripcion(platoDTO.getDescripcion());
        plato.setAlergenos(platoDTO.getAlergenos());

        if (platoDTO.getVideoFile() != null && !platoDTO.getVideoFile().isEmpty()) {
            MultipartFile myFile = platoDTO.getVideoFile();
            byte[] imagenBytes = myFile.getBytes();
            plato.setVideo(imagenBytes);
        } else {
            plato.setVideo(null);
        }

        this.platoRepository.save(plato);
    }

    //Nacho
    public PlatoDTO findById(Integer idPlato) {
        Plato p = platoRepository.findById(idPlato).orElse(null);

        return p.toDTO();
    }
    //@Victoria
}
