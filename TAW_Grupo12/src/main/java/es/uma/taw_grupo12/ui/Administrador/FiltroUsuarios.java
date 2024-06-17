package es.uma.taw_grupo12.ui.Administrador;

import java.util.ArrayList;
import java.util.List;

public class FiltroUsuarios {
    protected String busqueda;
    protected List<String> tipoUsuario;
    protected List<String> tipoTrabajador;

    public FiltroUsuarios() {
        this.busqueda = "";
        this.tipoUsuario = new ArrayList<>();
        this.tipoTrabajador = new ArrayList<>();
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<String> getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(List<String> tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<String> getTipoTrabajador() {
        return tipoTrabajador;
    }

    public void setTipoTrabajador(List<String> tipoTrabajador) {
        this.tipoTrabajador = tipoTrabajador;
    }

    public boolean estaVacio () {
        return busqueda.isEmpty() && tipoUsuario.isEmpty() && tipoTrabajador.isEmpty();
    }
}