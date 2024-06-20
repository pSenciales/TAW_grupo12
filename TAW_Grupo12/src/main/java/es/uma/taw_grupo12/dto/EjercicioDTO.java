package es.uma.taw_grupo12.dto;

import lombok.Data;

import java.util.List;

@Data
public class EjercicioDTO {
    private Integer idejercicio;
    private String nombre;
    private String tipo;
    private byte[] video;
    private String descripcion;
    private List<Integer> ejercicioRutinaList;
}
