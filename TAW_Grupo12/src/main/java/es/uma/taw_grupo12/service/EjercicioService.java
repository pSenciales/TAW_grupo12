package es.uma.taw_grupo12.service;


import es.uma.taw_grupo12.dto.EjercicioDTO;
import es.uma.taw_grupo12.dto.PlatoDTO;
import es.uma.taw_grupo12.entity.Ejercicio;
import es.uma.taw_grupo12.dao.EjercicioRepository;
import es.uma.taw_grupo12.entity.Plato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;


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
    //@Victoria
}
