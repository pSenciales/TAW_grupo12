/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uma.taw_grupo12.entity;

import java.io.Serializable;

import es.uma.taw_grupo12.dto.DTO;
import es.uma.taw_grupo12.dto.PlatoDietaDTO;
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
@Table(name = "platodieta")
@NamedQueries({
    @NamedQuery(name = "PlatoDieta.findAll", query = "SELECT p FROM PlatoDieta p"),
    @NamedQuery(name = "PlatoDieta.findByIdplatodieta", query = "SELECT p FROM PlatoDieta p WHERE p.platoDietaPK.idplatodieta = :idplatodieta"),
    @NamedQuery(name = "PlatoDieta.findByIdplato", query = "SELECT p FROM PlatoDieta p WHERE p.platoDietaPK.idplato = :idplato"),
    @NamedQuery(name = "PlatoDieta.findByIddieta", query = "SELECT p FROM PlatoDieta p WHERE p.platoDietaPK.iddieta = :iddieta"),
    @NamedQuery(name = "PlatoDieta.findByCalorias", query = "SELECT p FROM PlatoDieta p WHERE p.calorias = :calorias"),
    @NamedQuery(name = "PlatoDieta.findByCantidad", query = "SELECT p FROM PlatoDieta p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "PlatoDieta.findByOrden", query = "SELECT p FROM PlatoDieta p WHERE p.orden = :orden"),
    @NamedQuery(name = "PlatoDieta.findByDiassemana", query = "SELECT p FROM PlatoDieta p WHERE p.diassemana = :diassemana"),
    @NamedQuery(name = "PlatoDieta.findByFranjahoraria", query = "SELECT p FROM PlatoDieta p WHERE p.franjahoraria = :franjahoraria")})
public class PlatoDieta implements Serializable , DTO<PlatoDietaDTO> {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlatoDietaPK platoDietaPK;
    @Column(name = "calorias")
    private Integer calorias;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "diassemana")
    private String diassemana;
    @Column(name = "franjahoraria")
    private String franjahoraria;
    @JoinColumn(name = "iddieta", referencedColumnName = "iddieta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dieta dieta;
    @JoinColumn(name = "idplato", referencedColumnName = "idplato", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Plato plato;

    public PlatoDieta() {
    }

    public PlatoDieta(PlatoDietaPK platoDietaPK) {
        this.platoDietaPK = platoDietaPK;
    }

    public PlatoDieta(int idplatodieta, int idplato, int iddieta) {
        this.platoDietaPK = new PlatoDietaPK(idplatodieta, idplato, iddieta);
    }

    public PlatoDietaPK getPlatoDietaPK() {
        return platoDietaPK;
    }

    public void setPlatoDietaPK(PlatoDietaPK platoDietaPK) {
        this.platoDietaPK = platoDietaPK;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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

    public String getFranjahoraria() {
        return franjahoraria;
    }

    public void setFranjahoraria(String franjahoraria) {
        this.franjahoraria = franjahoraria;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (platoDietaPK != null ? platoDietaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlatoDieta)) {
            return false;
        }
        PlatoDieta other = (PlatoDieta) object;
        if ((this.platoDietaPK == null && other.platoDietaPK != null) || (this.platoDietaPK != null && !this.platoDietaPK.equals(other.platoDietaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw12.app.entity.PlatoDieta[ platoDietaPK=" + platoDietaPK + " ]";
    }

    @Override
    public PlatoDietaDTO toDTO(){
        PlatoDietaDTO platoDieta = new PlatoDietaDTO();

        platoDieta.setIdPlatoDieta(this.platoDietaPK.getIdplatodieta());
        platoDieta.setIdDieta(this.dieta.getIddieta());
        platoDieta.setIdPlato(this.plato.getIdplato());
        platoDieta.setCantidad(this.cantidad);
        platoDieta.setCalorias(this.calorias);
        platoDieta.setOrden(this.orden);
        platoDieta.setDiasSemana(this.diassemana);
        platoDieta.setFranjaHoraria(this.franjahoraria);

        return platoDieta;
    }
}
