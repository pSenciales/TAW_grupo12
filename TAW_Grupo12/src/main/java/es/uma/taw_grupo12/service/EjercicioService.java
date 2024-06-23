package es.uma.taw_grupo12.service;


import es.uma.taw_grupo12.dao.EjercicioRutinaRepository;
import es.uma.taw_grupo12.dto.EjercicioDTO;
import es.uma.taw_grupo12.dto.EjercicioRutinaDTO;
import es.uma.taw_grupo12.entity.Ejercicio;
import es.uma.taw_grupo12.dao.EjercicioRepository;
import es.uma.taw_grupo12.entity.EjercicioRutina;
import es.uma.taw_grupo12.dto.PlatoDTO;
import es.uma.taw_grupo12.entity.Ejercicio;
import es.uma.taw_grupo12.dao.EjercicioRepository;
import es.uma.taw_grupo12.entity.EjercicioRutina;
import es.uma.taw_grupo12.entity.Plato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    protected EjercicioRutinaRepository ejercicioRutinaRepository;


    //@Pablo
    public List<EjercicioDTO> getFuerza() {
        List<Ejercicio> ejerciciosList = ejercicioRepository.getFuerza();
        List<EjercicioDTO> ejerciciosDTO = new ArrayList<>();
        for(Ejercicio ej : ejerciciosList){
            EjercicioDTO aux = ej.toDTO();
            ejerciciosDTO.add(aux);
        }
        return ejerciciosDTO;
    }
    //@Pablo

    //@Guillermo
    public List<EjercicioDTO> getAll() {
        List<Ejercicio> ejerciciosList = ejercicioRepository.findAll();
        List<EjercicioDTO> ejerciciosDTO = new ArrayList<>();
        for (Ejercicio ej : ejerciciosList) {
            EjercicioDTO aux = ej.toDTO();
            ejerciciosDTO.add(aux);
        }
        return ejerciciosDTO;
    }

    public List<String> getTipos() {
        List<String> tipos = new ArrayList<>();
        List<Ejercicio> ejerciciosList = ejercicioRepository.findAll();

        for (Ejercicio ej : ejerciciosList) {
            if (!tipos.contains(ej.getTipo())) {
                tipos.add(ej.getTipo());
            }
        }
        return tipos;
    }

    public List<EjercicioDTO> getByTipos(List<String> filtrotipos) {
        List<Ejercicio> ejerciciosList = ejercicioRepository.findAll();
        List<EjercicioDTO> ejerciciosDTO = new ArrayList<>();

        for (Ejercicio ej : ejerciciosList) {
            if (filtrotipos.contains(ej.getTipo())) {
                EjercicioDTO aux = ej.toDTO();
                ejerciciosDTO.add(aux);
            }
        }

        return ejerciciosDTO;
    }
    //@Gillermo

    public EjercicioDTO findById(Integer id) {
        Ejercicio ejercicio = ejercicioRepository.findById(id).orElse(null);
        assert (ejercicio != null);
        return ejercicio.toDTO();
    }
    //@Victoria
    public List<EjercicioDTO> listarEjerciciosDTO(String busqueda) {
        List<Ejercicio> ejercicios = this.ejercicioRepository.findAllByNombre(busqueda);
        List<EjercicioDTO> ejerciciosDTO = new ArrayList<>();
        for(Ejercicio e : ejercicios){
            ejerciciosDTO.add(e.toDTO());
        }
        return ejerciciosDTO;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<EjercicioDTO> listarEjerciciosDTOTipos(List<String> tipos, String busqueda) {
        StringBuilder sb = new StringBuilder("SELECT e FROM Ejercicio e");
        if (!tipos.isEmpty()) {
            sb.append(" WHERE (");
            for (int i = 0; i < tipos.size(); i++) {
                sb.append(" (e.tipo = :tipo").append(i).append(")");
                if (i < tipos.size() - 1) {
                    sb.append(" OR ");
                }
            }
            sb.append(") AND e.nombre LIKE :busqueda");
        }

        TypedQuery<Ejercicio> query = entityManager.createQuery(sb.toString(), Ejercicio.class);
        for (int i = 0; i < tipos.size(); i++) {
            query.setParameter("tipo" + i, tipos.get(i));
        }
        query.setParameter("busqueda", "%" + busqueda + "%");

        List<Ejercicio> ejercicios = query.getResultList();
        List<EjercicioDTO> ejerciciosDTO = new ArrayList<>();
        for(Ejercicio e : ejercicios){
            ejerciciosDTO.add(e.toDTO());
        }
        return ejerciciosDTO;
    }

    public Ejercicio buscarEjercicioNombre(String nombre) {
        return this.ejercicioRepository.findByNombre(nombre).orElse(null);
    }



    public void crearEjercicio(EjercicioDTO ejercicioDTO) throws IOException {
        Ejercicio ejercicioNuevo = new Ejercicio();

        ejercicioNuevo.setNombre(ejercicioDTO.getNombre());
        ejercicioNuevo.setDescripcion(ejercicioDTO.getDescripcion());
        ejercicioNuevo.setTipo(ejercicioDTO.getTipo());

        if (ejercicioDTO.getVideoFile() != null && !ejercicioDTO.getVideoFile().isEmpty()) {
            MultipartFile myFile = ejercicioDTO.getVideoFile();
            byte[] imagenBytes = myFile.getBytes();
            ejercicioNuevo.setVideo(imagenBytes);
        } else {
            ejercicioDTO.setVideo(null);
        }

        this.ejercicioRepository.save(ejercicioNuevo);
    }

    public void eliminarEjercicio(Integer idEjercicio) {
        Ejercicio ejercicio = this.ejercicioRepository.findById(idEjercicio).orElse(null);
        List<EjercicioRutina> ejerciciosRutina = this.ejercicioRutinaRepository.findEjerciciosRutina(idEjercicio);

        this.ejercicioRutinaRepository.deleteAll(ejerciciosRutina);

        this.ejercicioRepository.deleteById(idEjercicio);
    }

    public void guardarEjercicio(EjercicioDTO ejercicioDTO) throws IOException {
        Ejercicio miEjercicio = this.ejercicioRepository.findById(ejercicioDTO.getIdejercicio()).orElse(null);
        if(miEjercicio != null){
            miEjercicio.setNombre(miEjercicio.getNombre());
            if(!ejercicioDTO.getDescripcion().isEmpty()){
                miEjercicio.setDescripcion(ejercicioDTO.getDescripcion());
            }
            if (ejercicioDTO.getVideoFile() != null && !ejercicioDTO.getVideoFile().isEmpty()) {
                MultipartFile myFile = ejercicioDTO.getVideoFile();
                byte[] imagenBytes = myFile.getBytes();
                miEjercicio.setVideo(imagenBytes);
            }
            miEjercicio.setTipo(ejercicioDTO.getTipo());
            this.ejercicioRepository.save(miEjercicio);
        }
    }

    public Ejercicio buscarEjercicioNombre(EjercicioDTO ejercicioDTO) {
        Ejercicio original = this.ejercicioRepository.findById(ejercicioDTO.getIdejercicio()).orElse(null);
        if(original.getNombre().equals(ejercicioDTO.getNombre())){
            return null;
        }
        return  this.ejercicioRepository.findByNombre(ejercicioDTO.getNombre()).orElse(null);
    }
    //@Victoria
}
