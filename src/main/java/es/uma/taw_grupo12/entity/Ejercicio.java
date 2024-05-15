/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uma.taw_grupo12.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 *
 * @author guzman
 */
@Entity
@Table(name = "Ejercicio")
@NamedQueries({
    @NamedQuery(name = "Ejercicio.findAll", query = "SELECT e FROM Ejercicio e"),
    @NamedQuery(name = "Ejercicio.findByIdejercicio", query = "SELECT e FROM Ejercicio e WHERE e.idejercicio = :idejercicio"),
    @NamedQuery(name = "Ejercicio.findByNombre", query = "SELECT e FROM Ejercicio e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Ejercicio.findByTipo", query = "SELECT e FROM Ejercicio e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "Ejercicio.findByDescripcion", query = "SELECT e FROM Ejercicio e WHERE e.descripcion = :descripcion")})
public class Ejercicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idejercicio")
    private Integer idejercicio;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Lob
    @Column(name = "video")
    private byte[] video;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejercicio")
    private List<EjercicioRutina> ejercicioRutinaList;

    public Ejercicio() {
    }

    public Ejercicio(Integer idejercicio) {
        this.idejercicio = idejercicio;
    }

    public Ejercicio(Integer idejercicio, String nombre, String tipo) {
        this.idejercicio = idejercicio;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Integer getIdejercicio() {
        return idejercicio;
    }

    public void setIdejercicio(Integer idejercicio) {
        this.idejercicio = idejercicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<EjercicioRutina> getEjercicioRutinaList() {
        return ejercicioRutinaList;
    }

    public void setEjercicioRutinaList(List<EjercicioRutina> ejercicioRutinaList) {
        this.ejercicioRutinaList = ejercicioRutinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idejercicio != null ? idejercicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejercicio)) {
            return false;
        }
        Ejercicio other = (Ejercicio) object;
        if ((this.idejercicio == null && other.idejercicio != null) || (this.idejercicio != null && !this.idejercicio.equals(other.idejercicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw12.app.entity.Ejercicio[ idejercicio=" + idejercicio + " ]";
    }
    
}
