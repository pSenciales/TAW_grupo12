package es.uma.taw_grupo12.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    protected boolean estaAutenticado(HttpSession session) {
        return session.getAttribute("usuario") != null;
    }
}
