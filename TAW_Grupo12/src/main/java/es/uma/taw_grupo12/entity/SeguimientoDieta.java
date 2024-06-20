/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uma.taw_grupo12.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "seguimientodieta")
@NamedQueries({
    @NamedQuery(name = "SeguimientoDieta.findAll", query = "SELECT s FROM SeguimientoDieta s"),
    @NamedQuery(name = "SeguimientoDieta.findByFecha", query = "SELECT s FROM SeguimientoDieta s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "SeguimientoDieta.findByComido", query = "SELECT s FROM SeguimientoDieta s WHERE s.comido = :comido"),
    @NamedQuery(name = "SeguimientoDieta.findByCantidad", query = "SELECT s FROM SeguimientoDieta s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "SeguimientoDieta.findByObservaciones", query = "SELECT s FROM SeguimientoDieta s WHERE s.observaciones = :observaciones")})
public class SeguimientoDieta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idseguimientodieta")
    protected Integer seguimientoDietaPK;
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
    @Column(name = "cantidadobjetivo")
    private String cantidadobjetivo;
    @Column(name = "nombreplato")
    private String nombreplato;
    @JoinColumn(name = "iddieta", referencedColumnName = "iddieta")
    @ManyToOne(optional = false)
    private Dieta dieta;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    public SeguimientoDieta() {
    }

    public SeguimientoDieta(Integer  seguimientoDietaPK) {
        this.seguimientoDietaPK = seguimientoDietaPK;
    }

    public SeguimientoDieta(Integer  seguimientoDietaPK, Date fecha) {
        this.seguimientoDietaPK = seguimientoDietaPK;
        this.fecha = fecha;
    }


    public Integer  getSeguimientoDietaPK() {
        return seguimientoDietaPK;
    }

    public void setSeguimientoDietaPK(Integer  seguimientoDietaPK) {
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

    public Dieta getPlatoDieta() {
        return dieta;
    }

    public void setDieta(Dieta platoDieta) {
        this.dieta = platoDieta;
    }

    public String getCantidadobjetivo() {return cantidadobjetivo;}

    public void setCantidadobjetivo(String cantidadobjetivo) {this.cantidadobjetivo = cantidadobjetivo;}

    public String getNombreplato() {return nombreplato;}

    public void setNombreplato(String nombreplato) {this.nombreplato = nombreplato;}
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