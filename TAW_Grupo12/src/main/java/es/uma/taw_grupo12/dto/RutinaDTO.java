package es.uma.taw_grupo12.dto;

import es.uma.taw_grupo12.entity.Rutina;
import lombok.Data;

import java.util.List;

@Data
public class RutinaDTO {
    private Integer idtrabajador;
    private Integer idrutina;
    private String nombre;
    private List<Integer> ejercicioRutinaList;
    private Integer idcliente;

    public RutinaDTO(String nombre, Integer idtrabajador, Integer idcliente) {
        this.nombre = nombre;
        this.idtrabajador = idtrabajador;
        this.idcliente = idcliente;
    }

    public RutinaDTO() {

    }
}
