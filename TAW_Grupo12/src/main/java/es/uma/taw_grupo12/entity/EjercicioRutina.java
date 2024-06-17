/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uma.taw_grupo12.entity;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "EjercicioRutina")
@NamedQueries({
    @NamedQuery(name = "EjercicioRutina.findAll", query = "SELECT e FROM EjercicioRutina e"),
    @NamedQuery(name = "EjercicioRutina.findByIdejerciciorutina", query = "SELECT e FROM EjercicioRutina e WHERE e.ejercicioRutinaPK.idejerciciorutina = :idejerciciorutina"),
    @NamedQuery(name = "EjercicioRutina.findByIdrutina", query = "SELECT e FROM EjercicioRutina e WHERE e.ejercicioRutinaPK.idrutina = :idrutina"),
    @NamedQuery(name = "EjercicioRutina.findByIdejercicio", query = "SELECT e FROM EjercicioRutina e WHERE e.ejercicioRutinaPK.idejercicio = :idejercicio"),
    @NamedQuery(name = "EjercicioRutina.findByPeso", query = "SELECT e FROM EjercicioRutina e WHERE e.peso = :peso"),
    @NamedQuery(name = "EjercicioRutina.findByRepeticiones", query = "SELECT e FROM EjercicioRutina e WHERE e.repeticiones = :repeticiones"),
    @NamedQuery(name = "EjercicioRutina.findBySeries", query = "SELECT e FROM EjercicioRutina e WHERE e.series = :series"),
    @NamedQuery(name = "EjercicioRutina.findByOrden", query = "SELECT e FROM EjercicioRutina e WHERE e.orden = :orden"),
    @NamedQuery(name = "EjercicioRutina.findByDiassemana", query = "SELECT e FROM EjercicioRutina e WHERE e.diassemana = :diassemana")})
public class EjercicioRutina implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EjercicioRutinaPK ejercicioRutinaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso")
    private Float peso;
    @Column(name = "repeticiones")
    private Integer repeticiones;
    @Column(name = "series")
    private Integer series;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "diassemana")
    private String diassemana;
    @JoinColumn(name = "idrutina", referencedColumnName = "idrutina", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rutina rutina;
    @JoinColumn(name = "idejercicio", referencedColumnName = "idejercicio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ejercicio ejercicio;
<<<<<<< Updated upstream
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ejercicioRutina")
    private SeguimientoObjetivos seguimientoObjetivos;
=======
>>>>>>> Stashed changes

    public EjercicioRutina() {
    }

    public EjercicioRutina(EjercicioRutinaPK ejercicioRutinaPK) {
        this.ejercicioRutinaPK = ejercicioRutinaPK;
    }

    public EjercicioRutina(int idejerciciorutina, int idrutina, int idejercicio) {
        this.ejercicioRutinaPK = new EjercicioRutinaPK(idejerciciorutina, idrutina, idejercicio);
    }

    public EjercicioRutinaPK getEjercicioRutinaPK() {
        return ejercicioRutinaPK;
    }

    public void setEjercicioRutinaPK(EjercicioRutinaPK ejercicioRutinaPK) {
        this.ejercicioRutinaPK = ejercicioRutinaPK;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getDiassemana() {
        return diassemana;
    }

    public void setDiassemana(String diassemana) {
        this.diassemana = diassemana;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

<<<<<<< Updated upstream
    public SeguimientoObjetivos getSeguimientoObjetivos() {
        return seguimientoObjetivos;
    }

    public void setSeguimientoObjetivos(SeguimientoObjetivos seguimientoObjetivos) {
        this.seguimientoObjetivos = seguimientoObjetivos;
    }
=======
>>>>>>> Stashed changes

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ejercicioRutinaPK != null ? ejercicioRutinaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EjercicioRutina)) {
            return false;
        }
        EjercicioRutina other = (EjercicioRutina) object;
        if ((this.ejercicioRutinaPK == null && other.ejercicioRutinaPK != null) || (this.ejercicioRutinaPK != null && !this.ejercicioRutinaPK.equals(other.ejercicioRutinaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw12.app.entity.EjercicioRutina[ ejercicioRutinaPK=" + ejercicioRutinaPK + " ]";
    }
<<<<<<< Updated upstream
    
=======

    public String getDiassemanaString(){

        return switch (this.diassemana) {
            case "1" -> "Lunes";
            case "2" -> "Martes";
            case "3" -> "Miercoles";
            case "4" -> "Jueves";
            case "5" -> "Viernes";
            case "6" -> "Sabado";
            case "7" -> "Domingo";
            default -> null;
        };
    }

    public EjercicioRutinaDTO toDTO() {
        EjercicioRutinaDTO nueva = new EjercicioRutinaDTO();
        nueva.setEjercicioRutinaPK(this.ejercicioRutinaPK);
        nueva.setRutina(this.rutina.getIdrutina());
        nueva.setEjercicio(this.ejercicio.getIdejercicio());
        nueva.setPeso(this.peso);
        nueva.setRepeticiones(this.repeticiones);
        nueva.setSeries(this.series);
        nueva.setOrden(this.orden);
        nueva.setDiassemana(this.diassemana);
        return nueva;
    }
>>>>>>> Stashed changes
}
