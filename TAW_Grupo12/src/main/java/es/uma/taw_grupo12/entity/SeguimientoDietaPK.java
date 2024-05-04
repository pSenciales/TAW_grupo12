/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uma.taw_grupo12.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author guzman
 */
@Embeddable
public class SeguimientoDietaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idplatodieta")
    private int idplatodieta;
    @Basic(optional = false)
    @Column(name = "idplato")
    private int idplato;
    @Basic(optional = false)
    @Column(name = "iddieta")
    private int iddieta;

    public SeguimientoDietaPK() {
    }

    public SeguimientoDietaPK(int idplatodieta, int idplato, int iddieta) {
        this.idplatodieta = idplatodieta;
        this.idplato = idplato;
        this.iddieta = iddieta;
    }

    public int getIdplatodieta() {
        return idplatodieta;
    }

    public void setIdplatodieta(int idplatodieta) {
        this.idplatodieta = idplatodieta;
    }

    public int getIdplato() {
        return idplato;
    }

    public void setIdplato(int idplato) {
        this.idplato = idplato;
    }

    public int getIddieta() {
        return iddieta;
    }

    public void setIddieta(int iddieta) {
        this.iddieta = iddieta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idplatodieta;
        hash += (int) idplato;
        hash += (int) iddieta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoDietaPK)) {
            return false;
        }
        SeguimientoDietaPK other = (SeguimientoDietaPK) object;
        if (this.idplatodieta != other.idplatodieta) {
            return false;
        }
        if (this.idplato != other.idplato) {
            return false;
        }
        if (this.iddieta != other.iddieta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw12.app.entity.SeguimientoDietaPK[ idplatodieta=" + idplatodieta + ", idplato=" + idplato + ", iddieta=" + iddieta + " ]";
    }
    
}
