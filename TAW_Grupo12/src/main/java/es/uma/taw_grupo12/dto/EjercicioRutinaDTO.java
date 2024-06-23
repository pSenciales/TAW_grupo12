package es.uma.taw_grupo12.dto;

import lombok.Data;

@Data
public class EjercicioRutinaDTO {
    private Integer ejercicioRutinaPK;
    private String peso;
    private Integer repeticiones;
    private Integer series;
    private Integer orden;
    private String diassemana;
    private Integer rutina;
    private Integer ejercicio;
    private Integer seguimientoObjetivos;

    public Integer getDiassemanaInt() {
        return switch (this.diassemana) {
            case "Lunes" -> 1;
            case "Martes" -> 2;
            case "Miércoles" -> 3;
            case "Jueves" -> 4;
            case "Viernes" -> 5;
            case "Sabado" -> 6;
            case "Domingo" -> 7;
            default -> -1;
        };
    }

    @Override
    public String toString() {

        return "(" + this.series + "s, " + this.repeticiones + "r, " + this.peso + " kg)";

    }

    //Guillermo
    public String toStringCross() {
        String seriesText = (this.series == 1) ? " serie" : " series";
        String repeticionesText = (this.repeticiones == 1) ? " repetición" : " repeticiones";
        String pesoText = (this.peso.isEmpty()) ? ")" : " de " + this.peso + ")";

        return "(" + this.series + seriesText + " de " + this.repeticiones + repeticionesText + pesoText;
    }
    //Guillermo

}
