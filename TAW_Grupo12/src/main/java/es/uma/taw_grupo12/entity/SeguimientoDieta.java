/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uma.taw_grupo12.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "SeguimientoDieta")
@NamedQueries({
    @NamedQuery(name = "SeguimientoDieta.findAll", query = "SELECT s FROM SeguimientoDieta s"),
    @NamedQuery(name = "SeguimientoDieta.findByIdplatodieta", query = "SELECT s FROM SeguimientoDieta s WHERE s.seguimientoDietaPK.idplatodieta = :idplatodieta"),
    @NamedQuery(name = "SeguimientoDieta.findByIdplato", query = "SELECT s FROM SeguimientoDieta s WHERE s.seguimientoDietaPK.idplato = :idplato"),
    @NamedQuery(name = "SeguimientoDieta.findByIddieta", query = "SELECT s FROM SeguimientoDieta s WHERE s.seguimientoDietaPK.iddieta = :iddieta"),
    @NamedQuery(name = "SeguimientoDieta.findByFecha", query = "SELECT s FROM SeguimientoDieta s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "SeguimientoDieta.findByComido", query = "SELECT s FROM SeguimientoDieta s WHERE s.comido = :comido"),
    @NamedQuery(name = "SeguimientoDieta.findByCantidad", query = "SELECT s FROM SeguimientoDieta s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "SeguimientoDieta.findByObservaciones", query = "SELECT s FROM SeguimientoDieta s WHERE s.observaciones = :observaciones")})
public class SeguimientoDieta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeguimientoDietaPK seguimientoDietaPK;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "comido")
    private Short comido;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumns({
        @JoinColumn(name = "idplatodieta", referencedColumnName = "idplatodieta", insertable = false, updatable = false),
        @JoinColumn(name = "idplato", referencedColumnName = "idplato", insertable = false, updatable = false),
        @JoinColumn(name = "iddieta", referencedColumnName = "iddieta", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private PlatoDieta platoDieta;

    public SeguimientoDieta() {
    }

    public SeguimientoDieta(SeguimientoDietaPK seguimientoDietaPK) {
        this.seguimientoDietaPK = seguimientoDietaPK;
    }

    public SeguimientoDieta(SeguimientoDietaPK seguimientoDietaPK, Date fecha) {
        this.seguimientoDietaPK = seguimientoDietaPK;
        this.fecha = fecha;
    }

    public SeguimientoDieta(int idplatodieta, int idplato, int iddieta) {
        this.seguimientoDietaPK = new SeguimientoDietaPK(idplatodieta, idplato, iddieta);
    }

    public SeguimientoDietaPK getSeguimientoDietaPK() {
        return seguimientoDietaPK;
    }

    public void setSeguimientoDietaPK(SeguimientoDietaPK seguimientoDietaPK) {
        this.seguimientoDietaPK = seguimientoDietaPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Short getComido() {
        return comido;
    }

    public void setComido(Short comido) {
        this.comido = comido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public PlatoDieta getPlatoDieta() {
        return platoDieta;
    }

    public void setPlatoDieta(PlatoDieta platoDieta) {
        this.platoDieta = platoDieta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seguimientoDietaPK != null ? seguimientoDietaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoDieta)) {
            return false;
        }
        SeguimientoDieta other = (SeguimientoDieta) object;
        if ((this.seguimientoDietaPK == null && other.seguimientoDietaPK != null) || (this.seguimientoDietaPK != null && !this.seguimientoDietaPK.equals(other.seguimientoDietaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw12.app.entity.SeguimientoDieta[ seguimientoDietaPK=" + seguimientoDietaPK + " ]";
    }
    
}
