package es.uma.taw_grupo12.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SeguimientoDietaDTO {
    private Integer idSeguimientoDieta;
    private Integer idDieta;
    private Integer idCliente;
    private Date fecha;
    private Short comido;
    private Integer cantidad;
    private String observaciones;
    private String cantidadObjeto;
    private String nombrePlato;

    //Nacho
    public static boolean equalsOrNull(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

}
