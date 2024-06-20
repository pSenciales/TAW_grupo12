package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.PlatoRepository;
import es.uma.taw_grupo12.dto.PlatoDTO;
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

    public List<PlatoDTO> listarPlatosDTO() {
        List<Plato> platos =  this.platoRepository.findAll();
        List<PlatoDTO> platosDTO = new ArrayList<>();
        for(Plato p : platos){
            platosDTO.add(p.toDTO());
        }
        return platosDTO;
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

    public List<Plato> buscarPlatoNombre(String nombre) {
        return this.platoRepository.findByNombre(nombre);
    }
}
