/**
 * @author María Victoria Huesca Peláez
 */

package es.uma.taw_grupo12.ui.Administrador;

public class FiltroClientes {
    protected String busqueda;
    public FiltroClientes() {
        this.busqueda = "";
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public boolean estaVacio () {
        return busqueda.isEmpty();
    }
}
