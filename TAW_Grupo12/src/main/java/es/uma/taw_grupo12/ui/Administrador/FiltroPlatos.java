package es.uma.taw_grupo12.ui.Administrador;

import java.util.List;

public class FiltroPlatos {
    protected String busqueda;
    protected List<String> sinAlergenos;
    public FiltroPlatos() {
        this.busqueda = "";
        this.sinAlergenos = null;
    }
    public String getBusqueda() {
        return busqueda;
    }
    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    public List<String> getSinAlergenos() {
        return sinAlergenos;
    }
    public void setSinAlergenos(List<String> sinAlergenos) {
        this.sinAlergenos = sinAlergenos;
    }
    public boolean estaVacio() {
        return this.busqueda.isEmpty() && this.sinAlergenos == null;
    }
}
