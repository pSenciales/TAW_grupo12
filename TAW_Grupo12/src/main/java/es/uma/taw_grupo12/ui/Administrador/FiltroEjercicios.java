/**
 * @author María Victoria Huesca Peláez
 */

package es.uma.taw_grupo12.ui.Administrador;

import java.util.List;

public class FiltroEjercicios {

    private String busqueda;
    private List<String> tipos;

    public FiltroEjercicios() {
        this.busqueda = "";
        this.tipos = null;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<String> getTipos() {
        return tipos;
    }

    public void setTipos(List<String> tipos) {
        this.tipos = tipos;
    }

    public boolean estaVacio() {
        return busqueda.isEmpty() && tipos.isEmpty();
    }
}
