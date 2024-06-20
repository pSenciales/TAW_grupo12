package es.uma.taw_grupo12.dto;


import es.uma.taw_grupo12.entity.Dieta;
import es.uma.taw_grupo12.entity.Rutina;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Data
public class ClienteDTO {
    private Integer idcliente;
    private String nombre;
    private String email;
    private String contrasenya;
    private byte[] imagenperfil;
    private Double peso;
    private Double altura;
    private String alergias;
    private MultipartFile imagenperfilFile;     //AÑADIDO PARA MANEJAR LA SUBIDA DE IMÁGENES A LA BASE DE DATOS
    private List<Integer> trabajadorList;
    private List<Dieta> dietaList;
    private List<Rutina> rutinaList;

    //@Victoria
    public String getImagenBase64() {
        return (this.imagenperfil != null) ? Base64.getEncoder().encodeToString(this.imagenperfil) : null;
    }
    //@Victoria
}
