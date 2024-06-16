package es.uma.taw_grupo12.ui.Administrador;

public class FiltroClientes {
    protected String nombre;
    protected String email;

    public FiltroClientes() {
        this.nombre = "";
        this.email = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean estaVacio () {
        return nombre.isEmpty() && this.email.isEmpty();
    }
}
