package es.uma.taw_grupo12.dto;

import lombok.Data;

import java.util.Base64;

@Data
public class PlatoDTO{
    private Integer idplato;
    private String nombre;
    private String alergenos;
    private byte[] video;
    private String descripcion;

    public String getImagenBase64() {
        return (this.video != null) ? Base64.getEncoder().encodeToString(this.video) : null;
    }
}
