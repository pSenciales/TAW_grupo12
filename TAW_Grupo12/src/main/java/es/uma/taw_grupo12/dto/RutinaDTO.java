package es.uma.taw_grupo12.dto;

import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.EjercicioRutina;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class RutinaDTO {
    private Integer idrutina;
    private String nombre;
    private List<EjercicioRutina> ejercicioRutinaList;
    private Integer idcliente;


}
