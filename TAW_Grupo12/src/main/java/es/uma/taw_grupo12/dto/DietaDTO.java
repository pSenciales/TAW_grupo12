package es.uma.taw_grupo12.dto;

import es.uma.taw_grupo12.entity.PlatoDieta;
import lombok.Data;

import java.util.List;

@Data
public class DietaDTO {
    private Integer idDieta;
    private String nombre;
    private Integer idcliente;
    private List<PlatoDieta>

    public DietaDTO(String nombre, Integer idcliente) {
        this.nombre = nombre;
        this.idcliente = idcliente;
    }

    public DietaDTO() {

    }
}
