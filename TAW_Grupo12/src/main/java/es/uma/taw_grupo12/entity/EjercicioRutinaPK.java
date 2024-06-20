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
public class EjercicioRutinaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idejerciciorutina")
    private int idejerciciorutina;
    @Basic(optional = false)
    @Column(name = "idrutina")
    private int idrutina;
    @Basic(optional = false)
    @Column(name = "idejercicio")
    private int idejercicio;

    public EjercicioRutinaPK() {
    }

    public EjercicioRutinaPK(int idejerciciorutina, int idrutina, int idejercicio) {
        this.idejerciciorutina = idejerciciorutina;
        this.idrutina = idrutina;
        this.idejercicio = idejercicio;
    }

    public int getIdejerciciorutina() {
        return idejerciciorutina;
    }

    public void setIdejerciciorutina(int idejerciciorutina) {
        this.idejerciciorutina = idejerciciorutina;
    }

    public int getIdrutina() {
        return idrutina;
    }

    public void setIdrutina(int idrutina) {
        this.idrutina = idrutina;
    }

    public int getIdejercicio() {
        return idejercicio;
    }

    public void setIdejercicio(int idejercicio) {
        this.idejercicio = idejercicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idejerciciorutina;
        hash += (int) idrutina;
        hash += (int) idejercicio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EjercicioRutinaPK)) {
            return false;
        }
        EjercicioRutinaPK other = (EjercicioRutinaPK) object;
        if (this.idejerciciorutina != other.idejerciciorutina) {
            return false;
        }
        if (this.idrutina != other.idrutina) {
            return false;
        }
        if (this.idejercicio != other.idejercicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw12.app.entity.EjercicioRutinaPK[ idejerciciorutina=" + idejerciciorutina + ", idrutina=" + idrutina + ", idejercicio=" + idejercicio + " ]";
    }
    
}
