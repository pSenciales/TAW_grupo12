package es.uma.taw_grupo12.dto;

import es.uma.taw_grupo12.entity.Cliente;
import es.uma.taw_grupo12.entity.Rutina;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class SeguimientoObjetivosDTO {
    protected Integer seguimientoObjetivosPK;
    private Date fecha;
    private short realizado;
    private String pesorealizado;
    private Integer repeticionesrealizadas;
    private Integer seriesrealizadas;
    private String observaciones;
    private String pesoobjetivo;
    private Integer repeticionesobjetivo;
    private Integer seriesobjetivo;
    private String nombreejercicio;
    private Integer rutina;
    private Integer cliente;

    //Nacho
    public static boolean equalsOrNull(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

}
