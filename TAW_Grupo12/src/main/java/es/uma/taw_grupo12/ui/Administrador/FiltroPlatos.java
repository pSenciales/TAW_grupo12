package es.uma.taw_grupo12.ui.Administrador;

import java.util.List;

public class FiltroPlatos {
    protected String busqueda;
    protected List<String> alergenos;
    public FiltroPlatos() {
        this.busqueda = "";
        this.alergenos = null;
    }
    public String getBusqueda() {
        return busqueda;
    }
    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    public List<String> getAlergenos() {
        return alergenos;
    }
    public void setAlergenos(List<String> alergenos) {
        this.alergenos = alergenos;
    }
    public boolean isEmpty() {
        return this.busqueda.isEmpty() && this.alergenos == null;
    }
}
