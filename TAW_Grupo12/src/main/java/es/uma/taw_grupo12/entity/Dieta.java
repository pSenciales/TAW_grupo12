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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "Dieta")
@NamedQueries({
    @NamedQuery(name = "Dieta.findAll", query = "SELECT d FROM Dieta d"),
    @NamedQuery(name = "Dieta.findByIddieta", query = "SELECT d FROM Dieta d WHERE d.iddieta = :iddieta"),
    @NamedQuery(name = "Dieta.findByNombre", query = "SELECT d FROM Dieta d WHERE d.nombre = :nombre")})
public class Dieta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddieta")
    private Integer iddieta;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dieta")
    private List<PlatoDieta> platoDietaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seguimientoDietaPK")
    private List<SeguimientoDieta> seguimientoDieta;

    public Dieta() {
    }

    public Dieta(Integer iddieta) {
        this.iddieta = iddieta;
    }

    public Dieta(Integer iddieta, String nombre) {
        this.iddieta = iddieta;
        this.nombre = nombre;
    }

    public Integer getIddieta() {
        return iddieta;
    }

    public void setIddieta(Integer iddieta) {
        this.iddieta = iddieta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public List<PlatoDieta> getPlatoDietaList() {
        return platoDietaList;
    }

    public void setPlatoDietaList(List<PlatoDieta> platoDietaList) {
        this.platoDietaList = platoDietaList;
    }

    public List<SeguimientoDieta> getSeguimientoDieta() {return seguimientoDieta;}

    public void setSeguimientoDieta(List<SeguimientoDieta> seguimientoDieta) {this.seguimientoDieta = seguimientoDieta;}
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddieta != null ? iddieta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dieta)) {
            return false;
        }
        Dieta other = (Dieta) object;
        if ((this.iddieta == null && other.iddieta != null) || (this.iddieta != null && !this.iddieta.equals(other.iddieta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw12.app.entity.Dieta[ iddieta=" + iddieta + " ]";
    }
    
}
