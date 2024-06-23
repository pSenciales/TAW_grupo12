package es.uma.taw_grupo12.dto;

import lombok.Data;

@Data
public class PlatoDietaDTO {
    private Integer idPlatoDieta;
    private Integer idPlato;
    private Integer idDieta;
    private Integer calorias;
    private Integer cantidad;
    private Integer orden;
    private String diasSemana;
    private String franjaHoraria;
}
