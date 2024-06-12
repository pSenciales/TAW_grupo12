package es.uma.taw_grupo12.service;

import es.uma.taw_grupo12.dao.AdministradorRepository;
import es.uma.taw_grupo12.entity.Administrador;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdministradorService {

    @Autowired
    protected AdministradorRepository administradorRepository;

    public Administrador autenticarAdministrador (String email, String contrasenya) {
        Administrador admin = this.administradorRepository.autentica(email, contrasenya);
        if (admin != null) {
            return admin.toDTO();
        } else {
            return null;
        }
    }
}
