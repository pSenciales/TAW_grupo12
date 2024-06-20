/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uma.taw_grupo12.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.uma.taw_grupo12.dto.DTO;
import es.uma.taw_grupo12.dto.ClienteDTO;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


/**
 *
 * @author guzman
 */
@Data
@Entity
@Table(name = "Cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email"),
    @NamedQuery(name = "Cliente.findByContrasenya", query = "SELECT c FROM Cliente c WHERE c.contrasenya = :contrasenya"),
    @NamedQuery(name = "Cliente.findByPeso", query = "SELECT c FROM Cliente c WHERE c.peso = :peso"),
    @NamedQuery(name = "Cliente.findByAltura", query = "SELECT c FROM Cliente c WHERE c.altura = :altura"),
    @NamedQuery(name = "Cliente.findByAlergias", query = "SELECT c FROM Cliente c WHERE c.alergias = :alergias")})
public class Cliente implements Serializable, DTO<ClienteDTO> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Integer idcliente;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "contrasenya")
    private String contrasenya;
    @Lob
    @Column(name = "imagenperfil")
    private byte[] imagenperfil;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso")
    private Double peso;
    @Column(name = "altura")
    private Double altura;
    @Column(name = "alergias")
    private String alergias;
    @JoinTable(name = "Cliente_Trabajador", joinColumns = {
        @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")}, inverseJoinColumns = {
        @JoinColumn(name = "idtrabajador", referencedColumnName = "idtrabajador")})
    @ManyToMany
    private List<Trabajador> trabajadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcliente")
    private List<Dieta> dietaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcliente")
    private List<Rutina> rutinaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<SeguimientoDieta> seguimientoDietasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<SeguimientoObjetivos> objetivosList;


    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Cliente(Integer idcliente, String nombre, String email, String contrasenya) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.email = email;
        this.contrasenya = contrasenya;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
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

    public byte[] getImagenperfil() {
        return imagenperfil;
    }

    public void setImagenperfil(byte[] imagenperfil) {
        this.imagenperfil = imagenperfil;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    public List<Dieta> getDietaList() {
        return dietaList;
    }

    public void setDietaList(List<Dieta> dietaList) {
        this.dietaList = dietaList;
    }

    public List<Rutina> getRutinaList() {
        return rutinaList;
    }

    public void setRutinaList(List<Rutina> rutinaList) {
        this.rutinaList = rutinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw12.app.entity.Cliente[ idcliente=" + idcliente + " ]";
    }

    //@Victoria
    @Override
    public ClienteDTO toDTO() {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setIdcliente(this.idcliente);
        cliente.setNombre(this.nombre);
        cliente.setEmail(this.email);
        cliente.setContrasenya(this.contrasenya);
        cliente.setImagenperfil(this.imagenperfil);
        cliente.setPeso(this.peso);
        cliente.setAltura(this.altura);
        cliente.setAlergias(this.alergias);

        List<Integer> listaTrabajadores = new ArrayList<Integer>();
        if(this.trabajadorList != null) {
            for (Trabajador trabajador: this.trabajadorList) {
                listaTrabajadores.add(trabajador.getIdtrabajador());
            }
        }

        cliente.setTrabajadorList(listaTrabajadores);
        cliente.setDietaList(this.dietaList);
        cliente.setRutinaList(this.rutinaList);

        return cliente;
    }
    //@Victoria
}
