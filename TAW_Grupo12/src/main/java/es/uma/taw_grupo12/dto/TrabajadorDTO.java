package es.uma.taw_grupo12.dto;

import lombok.Data;

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


}
