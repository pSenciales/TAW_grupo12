package es.uma.taw_grupo12.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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

    public String getTipo(){
        return switch (this.tipo){
            case "ENTRENADOR FUERZA" -> "entrenador";
            case "ENTRENADOR CROSSTRAINNING" -> "entrenador-cross";
            case "DIETISTA" -> "dietista";
            default -> null;
        };
    }

}
