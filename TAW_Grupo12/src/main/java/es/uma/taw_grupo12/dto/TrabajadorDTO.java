package es.uma.taw_grupo12.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Data
public class TrabajadorDTO {

    private Integer idtrabajador;
    private String nombre;
    private String email;
    private String contrasenya;
    private String tipo;
    private byte[] imagenperfil;
    private List<Integer> clienteList;
    private MultipartFile imagenperfilFile; //AÑADIDO PARA MANEJAR LA SUBIDAD DE IMÁGENES A LA BASE DE DATOS

    //@Victoria
    public String getImagenBase64() {
        return (this.imagenperfil != null) ? Base64.getEncoder().encodeToString(this.imagenperfil) : null;
    }
    //@Victoria

    public String getTipo(){
        if (this.tipo == null) {
            return "";
        }
        return switch (this.tipo){
            case "ENTRENADOR FUERZA" -> "entrenador";
            case "ENTRENADOR CROSSTRAINING" -> "entrenadorcross";
            case "DIETISTA" -> "dietista";
            default -> "";
        };
    }

    public String getTipoRaw(){
        return this.tipo;
    }

}
