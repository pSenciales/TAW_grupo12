/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uma.taw_grupo12.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.uma.taw_grupo12.dto.RutinaDTO;
import es.uma.taw_grupo12.dto.TrabajadorDTO;
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
@Table(name = "Rutina")
@NamedQueries({
    @NamedQuery(name = "Rutina.findAll", query = "SELECT r FROM Rutina r"),
    @NamedQuery(name = "Rutina.findByIdrutina", query = "SELECT r FROM Rutina r WHERE r.idrutina = :idrutina"),
    @NamedQuery(name = "Rutina.findByNombre", query = "SELECT r FROM Rutina r WHERE r.nombre = :nombre")})
    public class Rutina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrutina")
    private Integer idrutina;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutina")
    private List<EjercicioRutina> ejercicioRutinaList;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @JoinColumn(name = "idtrabajador", referencedColumnName = "idtrabajador")
    @ManyToOne(optional = false)
    private Trabajador idtrabajador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seguimientoObjetivosPK")
    private List<SeguimientoObjetivos> seguimientoobjetivos;

    public Rutina() {
    }

    public Rutina(Integer idrutina) {
        this.idrutina = idrutina;
    }

    public Rutina(Integer idrutina, String nombre) {
        this.idrutina = idrutina;
        this.nombre = nombre;
    }

    public Integer getIdrutina() {
        return idrutina;
    }

    public void setIdrutina(Integer idrutina) {
        this.idrutina = idrutina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<EjercicioRutina> getEjercicioRutinaList() {
        return ejercicioRutinaList;
    }

    public void setEjercicioRutinaList(List<EjercicioRutina> ejercicioRutinaList) {
        this.ejercicioRutinaList = ejercicioRutinaList;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Trabajador getIdtrabajador() {return idtrabajador;}

    public void setIdtrabajador(Trabajador idtrabajador) {this.idtrabajador = idtrabajador;}
    public List<SeguimientoObjetivos> getSeguimientoobjetivos() {return seguimientoobjetivos;}

    public void setSeguimientoobjetivos(List<SeguimientoObjetivos> seguimientoobjetivos) {this.seguimientoobjetivos = seguimientoobjetivos;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrutina != null ? idrutina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rutina)) {
            return false;
        }
        Rutina other = (Rutina) object;
        if ((this.idrutina == null && other.idrutina != null) || (this.idrutina != null && !this.idrutina.equals(other.idrutina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw12.app.entity.Rutina[ idrutina=" + idrutina + " ]";
    }

    public RutinaDTO toDTO() {
        RutinaDTO rutina = new RutinaDTO();
        List<Integer> list = new ArrayList<>();

        if(this.getEjercicioRutinaList() != null) {
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