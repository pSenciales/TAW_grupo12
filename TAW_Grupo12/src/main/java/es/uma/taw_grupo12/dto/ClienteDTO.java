package es.uma.taw_grupo12.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
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
    private MultipartFile imagenperfilFile;
    private List<Integer> trabajadorList;
    private List<Integer> dietaList;
    private List<Integer> rutinaList;
}
