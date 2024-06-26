package es.uma.taw_grupo12.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Data
public class PlatoDTO{
    private Integer idplato;
    private String nombre;
    private String alergenos;
    private byte[] video;
    private String descripcion;
    private List<Integer> platoDietaList;

    //@Victoria
    private MultipartFile videoFile;
    public String getImagenBase64() {
        return (this.video != null) ? Base64.getEncoder().encodeToString(this.video) : null;
    }
    //@Victoria
}
