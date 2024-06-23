package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.ClienteRepository;
import es.uma.taw_grupo12.dao.DietaRepository;
import es.uma.taw_grupo12.dao.PlatodietaRepository;
import es.uma.taw_grupo12.dao.PlatoRepository;
import es.uma.taw_grupo12.dto.DietaDTO;
import es.uma.taw_grupo12.dto.PlatoDietaDTO;
import es.uma.taw_grupo12.dto.RutinaDTO;
import es.uma.taw_grupo12.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DietaService {

    @Autowired
    protected DietaRepository dietaRepository;
    @Autowired
    protected PlatoRepository platoRepository;
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected PlatodietaRepository platoDietaRepository;

    public DietaDTO findDietaById(Integer id){
        Dieta dieta = dietaRepository.findById(id).orElse(null);

        return dieta.toDTO();
    }

    public List<DietaDTO> findAllDieta(){
        List<Dieta> dietas = this.dietaRepository.findAll();

        List<DietaDTO> dietasDTO = new ArrayList<>();
        for (Dieta d : dietas){
            dietasDTO.add(d.toDTO());
        }

        return dietasDTO;
    }

    public List<DietaDTO> findByName(String nombre){
        List<Dieta> dietas = this.dietaRepository.findByName(nombre);

        List<DietaDTO> dietasDTO = new ArrayList<>();
        for (Dieta d : dietas){
            dietasDTO.add(d.toDTO());
        }

        return dietasDTO;
    }

    public void createDieta(DietaDTO dietaDTO){
        Dieta dieta;
        if(dietaDTO.getIdDieta() != null){
            dieta = dietaRepository.findById(dietaDTO.getIdDieta()).orElse(new Dieta());
        }else{
            dieta = new Dieta();
        }

        dieta.setIddieta(dietaDTO.getIdDieta());
        dieta.setNombre(dietaDTO.getNombre());

        Cliente cliente = clienteRepository.findById(dietaDTO.getIdCliente()).orElse(null);
        dieta.setIdcliente(cliente);

        dietaRepository.saveAndFlush(dieta);

        Dieta dietaSaved = dietaRepository.findByNombre(dietaDTO.getNombre()).orElse(null);

        List<PlatoDieta> platos = new ArrayList<>();
        List<PlatoDietaDTO> platosDietasDTO = dietaDTO.getPlatoDietaList();
        List<PlatoDieta> platosAux = platoDietaRepository.findByDietaId(dietaSaved.getIddieta());
        int count = 0;
        for(int i=0; i<platosDietasDTO.size(); i++){

            PlatoDieta platoDieta = new PlatoDieta();
            if(platosDietasDTO.get(i).getIdPlato() != null){
                if(count < platosAux.size() && !platosAux.isEmpty()){
                    platoDietaRepository.delete(platosAux.get(count));
                    platoDietaRepository.flush();
                }
                count++;
                Plato plato = platoRepository.findById(platosDietasDTO.get(i).getIdPlato()).orElse(null);

                PlatoDietaPK pk = new PlatoDietaPK();
                pk.setIdplatodieta(count);
                pk.setIddieta(dietaSaved.getIddieta());
                pk.setIdplato(plato.getIdplato());

                platoDieta.setPlatoDietaPK(pk);
                platoDieta.setCalorias(0);
                platoDieta.setCantidad(0);
                platoDieta.setOrden(0);
                platoDieta.setDieta(dieta);
                platoDieta.setPlato(plato);
                platoDieta.setFranjahoraria(platosDietasDTO.get(i).getFranjaHoraria());
                platoDieta.setDiassemana(platosDietasDTO.get(i).getDiasSemana());
                //System.out.println(platosDietasDTO.get(i).getDiasSemana() + ", " + platosDietasDTO.get(i).getFranjaHoraria());
                platoDietaRepository.saveAndFlush(platoDieta);
            }
        }
        dieta.setPlatoDietaList(platos);

        dietaRepository.saveAndFlush(dieta);
    }

    public void deleteById(Integer id){
        dietaRepository.deleteById(id);
    }

    //Nacho
    public List<DietaDTO> findAllByCliente(Integer id) {
        List<DietaDTO> lista = new ArrayList<>();
        List<Dieta> dietas = dietaRepository.findAllByClienteId(id);
        for(Dieta dieta : dietas){
            DietaDTO dietaDTO = dieta.toDTO();
            lista.add(dietaDTO);
        }
        return lista;
    }

    //Nacho
    public List<DietaDTO> findByNameAndClienteID(String nombre, Integer idCliente) {
        List<Dieta> d = dietaRepository.findByNombreAndClienteID(nombre, idCliente);
        List<DietaDTO> listaDTO = new ArrayList<>();
        for(Dieta dieta : d){
            listaDTO.add(dieta.toDTO());
        }

        return listaDTO;
    }
}
