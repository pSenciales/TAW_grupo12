package es.uma.taw_grupo12.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    //AÑADIDO PARA MANEJAR EL INICIO DE SESIÓN
    private int id;
    private String email;
    private String contrasenya;
}
