/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uma.taw_grupo12.entity;

import java.io.Serializable;
import java.util.Date;

import es.uma.taw_grupo12.dto.DTO;
import es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO;
import jakarta.persistence.*;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "seguimientoobjetivos")
@NamedQueries({
    @NamedQuery(name = "SeguimientoObjetivos.findAll", query = "SELECT s FROM SeguimientoObjetivos s"),
    @NamedQuery(name = "SeguimientoObjetivos.findByIdejerciciorutina", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.seguimientoObjetivosPK = :idejerciciorutina"),
    @NamedQuery(name = "SeguimientoObjetivos.findByIdrutina", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.seguimientoObjetivosPK = :idrutina"),
    @NamedQuery(name = "SeguimientoObjetivos.findByIdejercicio", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.seguimientoObjetivosPK = :idejercicio"),
    @NamedQuery(name = "SeguimientoObjetivos.findByFecha", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "SeguimientoObjetivos.findByRealizado", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.realizado = :realizado"),
    @NamedQuery(name = "SeguimientoObjetivos.findByPesorealizado", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.pesorealizado = :pesorealizado"),
    @NamedQuery(name = "SeguimientoObjetivos.findByRepeticionesrealizadas", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.repeticionesrealizadas = :repeticionesrealizadas"),
    @NamedQuery(name = "SeguimientoObjetivos.findBySeriesrealizadas", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.seriesrealizadas = :seriesrealizadas"),
    @NamedQuery(name = "SeguimientoObjetivos.findByObservaciones", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.observaciones = :observaciones")})
public class SeguimientoObjetivos implements Serializable, DTO<SeguimientoObjetivosDTO> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idseguimiento")
    protected Integer seguimientoObjetivosPK;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "realizado")
    private short realizado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pesorealizado")
    private String pesorealizado;
    @Column(name = "repeticionesrealizadas")
    private Integer repeticionesrealizadas;
    @Column(name = "seriesrealizadas")
    private Integer seriesrealizadas;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "pesoobjetivo")
    private String pesoobjetivo;
    @Column(name = "repeticionesobjetivo")
    private Integer repeticionesobjetivo;
    @Column(name = "seriesobjetivo")
    private Integer seriesobjetivo;
    @Column(name = "nombreejercicio")
    private String nombreejercicio;
    @JoinColumn(name = "idrutina", referencedColumnName = "idrutina")
    @ManyToOne(optional = false)
    private Rutina rutina;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;

    public SeguimientoObjetivos() {
    }

    public SeguimientoObjetivos(Integer seguimientoObjetivosPK) {
        this.seguimientoObjetivosPK = seguimientoObjetivosPK;
    }

    public SeguimientoObjetivos(Integer seguimientoObjetivosPK, Date fecha, short realizado) {
        this.seguimientoObjetivosPK = seguimientoObjetivosPK;
        this.fecha = fecha;
        this.realizado = realizado;
    }



    public Integer getSeguimientoObjetivosPK() {
        return seguimientoObjetivosPK;
    }

    public void setSeguimientoObjetivosPK(Integer seguimientoObjetivosPK) {
        this.seguimientoObjetivosPK = seguimientoObjetivosPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public short getRealizado() {
        return realizado;
    }

    public void setRealizado(short realizado) {
        this.realizado = realizado;
    }

    public String getPesorealizado() {
        return pesorealizado;
    }

    public void setPesorealizado(String pesorealizado) {
        this.pesorealizado = pesorealizado;
    }

    public Integer getRepeticionesrealizadas() {
        return repeticionesrealizadas;
    }

    public void setRepeticionesrealizadas(Integer repeticionesrealizadas) {
        this.repeticionesrealizadas = repeticionesrealizadas;
    }

    public Integer getSeriesrealizadas() {
        return seriesrealizadas;
    }

    public void setSeriesrealizadas(Integer seriesrealizadas) {
        this.seriesrealizadas = seriesrealizadas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina ejercicioRutina) {
        this.rutina = ejercicioRutina;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombreejercicio() {return nombreejercicio;}

    public void setNombreejercicio(String nombreejercicio) {this.nombreejercicio = nombreejercicio;}

    public Integer getSeriesobjetivo() {return seriesobjetivo;}

    public void setSeriesobjetivo(Integer seriesobjetivo) {this.seriesobjetivo = seriesobjetivo;}

    public Integer getRepeticionesobjetivo() {return repeticionesobjetivo;}

    public void setRepeticionesobjetivo(Integer repeticionesobjetivo) {this.repeticionesobjetivo = repeticionesobjetivo;}

    public String getPesoobjetivo() {return pesoobjetivo;}

    public void setPesoobjetivo(String pesoobjetivo) {this.pesoobjetivo = pesoobjetivo;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seguimientoObjetivosPK != null ? seguimientoObjetivosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoObjetivos)) {
            return false;
        }
        SeguimientoObjetivos other = (SeguimientoObjetivos) object;
        if ((this.seguimientoObjetivosPK == null && other.seguimientoObjetivosPK != null) || (this.seguimientoObjetivosPK != null && !this.seguimientoObjetivosPK.equals(other.seguimientoObjetivosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw12.app.entity.SeguimientoObjetivos[ seguimientoObjetivosPK=" + seguimientoObjetivosPK + " ]";
    }
    @Override
    public SeguimientoObjetivosDTO toDTO() {
        SeguimientoObjetivosDTO nueva = new SeguimientoObjetivosDTO();
        nueva.setSeguimientoObjetivosPK(this.seguimientoObjetivosPK);
        nueva.setRutina(this.rutina.getIdrutina());
        nueva.setCliente(this.cliente.getIdcliente());
        nueva.setFecha(this.fecha);
        nueva.setRealizado(this.realizado);
        nueva.setPesorealizado(this.pesorealizado);
        nueva.setRepeticionesrealizadas(this.repeticionesrealizadas);
        nueva.setSeriesrealizadas(this.seriesrealizadas);
        nueva.setObservaciones(this.observaciones);
        nueva.setPesoobjetivo(this.pesoobjetivo);
        nueva.setSeriesobjetivo(this.seriesobjetivo);
        nueva.setRepeticionesobjetivo(this.repeticionesobjetivo);
        nueva.setNombreejercicio(this.nombreejercicio);

        return nueva;
    }
}
