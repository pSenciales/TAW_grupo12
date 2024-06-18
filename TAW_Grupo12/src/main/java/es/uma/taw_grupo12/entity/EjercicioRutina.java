/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uma.taw_grupo12.entity;

import java.io.Serializable;
import java.util.List;

import es.uma.taw_grupo12.dto.EjercicioRutinaDTO;
import jakarta.persistence.*;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "ejerciciorutina")
@NamedQueries({
    @NamedQuery(name = "EjercicioRutina.findAll", query = "SELECT e FROM EjercicioRutina e"),
    @NamedQuery(name = "EjercicioRutina.findByPeso", query = "SELECT e FROM EjercicioRutina e WHERE e.peso = :peso"),
    @NamedQuery(name = "EjercicioRutina.findByRepeticiones", query = "SELECT e FROM EjercicioRutina e WHERE e.repeticiones = :repeticiones"),
    @NamedQuery(name = "EjercicioRutina.findBySeries", query = "SELECT e FROM EjercicioRutina e WHERE e.series = :series"),
    @NamedQuery(name = "EjercicioRutina.findByOrden", query = "SELECT e FROM EjercicioRutina e WHERE e.orden = :orden"),
    @NamedQuery(name = "EjercicioRutina.findByDiassemana", query = "SELECT e FROM EjercicioRutina e WHERE e.diassemana = :diassemana")})
public class EjercicioRutina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idejerciciorutina")
    protected Integer ejercicioRutinaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso")
    private String peso;
    @Column(name = "repeticiones")
    private Integer repeticiones;
    @Column(name = "series")
    private Integer series;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "diassemana")
    private String diassemana;
    @JoinColumn(name = "idrutina", referencedColumnName = "idrutina")
    @ManyToOne(optional = false)
    private Rutina rutina;
    @JoinColumn(name = "idejercicio", referencedColumnName = "idejercicio")
    @ManyToOne(optional = false)
    private Ejercicio ejercicio;

    public EjercicioRutina() {
    }

    public EjercicioRutina(Integer ejercicioRutinaPK) {
        this.ejercicioRutinaPK = ejercicioRutinaPK;
    }



    public Integer getEjercicioRutinaPK() {
        return ejercicioRutinaPK;
    }

    public void setEjercicioRutinaPK(Integer ejercicioRutinaPK) {
        this.ejercicioRutinaPK = ejercicioRutinaPK;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
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
}