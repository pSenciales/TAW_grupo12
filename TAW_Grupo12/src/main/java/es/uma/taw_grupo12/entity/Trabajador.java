/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uma.taw_grupo12.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.uma.taw_grupo12.dto.TrabajadorDTO;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "Trabajador")
@NamedQueries({
    @NamedQuery(name = "Trabajador.findAll", query = "SELECT t FROM Trabajador t"),
    @NamedQuery(name = "Trabajador.findByIdtrabajador", query = "SELECT t FROM Trabajador t WHERE t.idtrabajador = :idtrabajador"),
    @NamedQuery(name = "Trabajador.findByNombre", query = "SELECT t FROM Trabajador t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Trabajador.findByEmail", query = "SELECT t FROM Trabajador t WHERE t.email = :email"),
    @NamedQuery(name = "Trabajador.findByContrasenya", query = "SELECT t FROM Trabajador t WHERE t.contrasenya = :contrasenya"),
    @NamedQuery(name = "Trabajador.findByTipo", query = "SELECT t FROM Trabajador t WHERE t.tipo = :tipo")})
public class Trabajador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtrabajador")
    private Integer idtrabajador;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "contrasenya")
    private String contrasenya;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Lob
    @Column(name = "imagenperfil")
    private byte[] imagenperfil;
    @ManyToMany(mappedBy = "trabajadorList")
    private List<Cliente> clienteList;

    public Trabajador() {
    }

    public Trabajador(Integer idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public Trabajador(Integer idtrabajador, String nombre, String email, String contrasenya, String tipo) {
        this.idtrabajador = idtrabajador;
        this.nombre = nombre;
        this.email = email;
        this.contrasenya = contrasenya;
        this.tipo = tipo;
    }

    public Integer getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(Integer idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getImagenperfil() {
        return imagenperfil;
    }

    public void setImagenperfil(byte[] imagenperfil) {
        this.imagenperfil = imagenperfil;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtrabajador != null ? idtrabajador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trabajador)) {
            return false;
        }
        Trabajador other = (Trabajador) object;
        if ((this.idtrabajador == null && other.idtrabajador != null) || (this.idtrabajador != null && !this.idtrabajador.equals(other.idtrabajador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw12.app.entity.Trabajador[ idtrabajador=" + idtrabajador + " ]";
    }

    public TrabajadorDTO toDTO() {
        TrabajadorDTO trabajadorDTO = new TrabajadorDTO();
        trabajadorDTO.setImagenperfil(this.imagenperfil);
        trabajadorDTO.setNombre(this.nombre);
        trabajadorDTO.setContrasenya(this.contrasenya);
        trabajadorDTO.setTipo(this.tipo);
        trabajadorDTO.setEmail(this.email);
        trabajadorDTO.setIdtrabajador(this.idtrabajador);
        List<Integer> clientes = new ArrayList<>();
        for(Cliente cliente : this.clienteList) {
            clientes.add(cliente.getIdcliente());
        }
        trabajadorDTO.setClienteList(clientes);

        return trabajadorDTO;
    }
}
