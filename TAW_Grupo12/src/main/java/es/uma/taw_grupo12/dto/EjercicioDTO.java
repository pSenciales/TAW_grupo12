package es.uma.taw_grupo12.dto;

import es.uma.taw_grupo12.entity.EjercicioRutina;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Data
public class EjercicioDTO {
    private Integer idejercicio;
    private String nombre;
    private String tipo;
    private byte[] video;
    private MultipartFile videoFile;
    private String descripcion;
    private List<Integer> ejercicioRutinaList;

    public String getImagenBase64() {
        return (this.video != null) ? Base64.getEncoder().encodeToString(this.video) : null;
    }
}
