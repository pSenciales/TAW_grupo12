package es.uma.taw_grupo12.dto;

import lombok.Data;

@Data
public class EjercicioRutinaDTO {
    private Integer ejercicioRutinaPK;
    private Float peso;
    private Integer repeticiones;
    private Integer series;
    private Integer orden;
    private String diassemana;
    private Integer rutina;
    private Integer ejercicio;
    private Integer seguimientoObjetivos;
}
