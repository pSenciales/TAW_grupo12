package es.uma.taw_grupo12.dto;

import lombok.Data;

import java.util.List;

@Data
public class DietaDTO {
    private Integer idDieta;
    private String nombre;
    private Integer idCliente;
    private List<PlatoDietaDTO> platoDietaList;
    private List<Integer> seguimientoDieta;
}
