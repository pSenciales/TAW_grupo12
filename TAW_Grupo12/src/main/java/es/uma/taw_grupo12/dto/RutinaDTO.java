package es.uma.taw_grupo12.dto;

import es.uma.taw_grupo12.entity.Rutina;
import lombok.Data;

import java.util.List;

@Data
public class RutinaDTO {
    private Integer idrutina;
    private String nombre;
    private List<Integer> ejercicioRutinaList;
    private Integer idcliente;

    public RutinaDTO(String nombre) {
        this.nombre = nombre;
    }

    public RutinaDTO() {

    }
}
