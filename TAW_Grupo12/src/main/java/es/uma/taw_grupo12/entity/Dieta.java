/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uma.taw_grupo12.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.uma.taw_grupo12.dto.DietaDTO;
import jakarta.persistence.*;

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

    //Nacho
    public DietaDTO toDTO() {
        DietaDTO dieta = new DietaDTO();
        List<Integer> list = new ArrayList<>();

        if(this.get() != null) {
            for (EjercicioRutina re : this.ejercicioRutinaList)
                list.add(re.getEjercicioRutinaPK());
        }
        rutina.setEjercicioRutinaList(list);
        rutina.setIdcliente(this.idcliente.getIdcliente());
        rutina.setNombre(this.nombre);
        rutina.setIdrutina(this.idrutina);
        rutina.setIdtrabajador(this.idtrabajador.getIdtrabajador());
        return rutina;
    }
    
}
