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
@Table(name = "Plato")
@NamedQueries({
    @NamedQuery(name = "Plato.findAll", query = "SELECT p FROM Plato p"),
    @NamedQuery(name = "Plato.findByIdplato", query = "SELECT p FROM Plato p WHERE p.idplato = :idplato"),
    @NamedQuery(name = "Plato.findByNombre", query = "SELECT p FROM Plato p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Plato.findByAlergenos", query = "SELECT p FROM Plato p WHERE p.alergenos = :alergenos"),
    @NamedQuery(name = "Plato.findByDescripcion", query = "SELECT p FROM Plato p WHERE p.descripcion = :descripcion")})
public class Plato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplato")
    private Integer idplato;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "alergenos")
    private String alergenos;
    @Lob
    @Column(name = "video")
    private byte[] video;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plato")
    private List<PlatoDieta> platoDietaList;

    public Plato() {
    }

    public Plato(Integer idplato) {
        this.idplato = idplato;
    }

    public Plato(Integer idplato, String nombre) {
        this.idplato = idplato;
        this.nombre = nombre;
    }

    public Integer getIdplato() {
        return idplato;
    }

    public void setIdplato(Integer idplato) {
        this.idplato = idplato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(String alergenos) {
        this.alergenos = alergenos;
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

    public List<PlatoDieta> getPlatoDietaList() {
        return platoDietaList;
    }

    public void setPlatoDietaList(List<PlatoDieta> platoDietaList) {
        this.platoDietaList = platoDietaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplato != null ? idplato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plato)) {
            return false;
        }
        Plato other = (Plato) object;
        if ((this.idplato == null && other.idplato != null) || (this.idplato != null && !this.idplato.equals(other.idplato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw12.app.entity.Plato[ idplato=" + idplato + " ]";
    }
    
}
