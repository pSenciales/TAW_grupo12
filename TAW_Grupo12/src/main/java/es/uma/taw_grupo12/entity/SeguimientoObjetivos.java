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
@Table(name = "SeguimientoObjetivos")
@NamedQueries({
    @NamedQuery(name = "SeguimientoObjetivos.findAll", query = "SELECT s FROM SeguimientoObjetivos s"),
    @NamedQuery(name = "SeguimientoObjetivos.findByIdejerciciorutina", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.seguimientoObjetivosPK.idejerciciorutina = :idejerciciorutina"),
    @NamedQuery(name = "SeguimientoObjetivos.findByIdrutina", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.seguimientoObjetivosPK.idrutina = :idrutina"),
    @NamedQuery(name = "SeguimientoObjetivos.findByIdejercicio", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.seguimientoObjetivosPK.idejercicio = :idejercicio"),
    @NamedQuery(name = "SeguimientoObjetivos.findByFecha", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "SeguimientoObjetivos.findByRealizado", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.realizado = :realizado"),
    @NamedQuery(name = "SeguimientoObjetivos.findByPesorealizado", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.pesorealizado = :pesorealizado"),
    @NamedQuery(name = "SeguimientoObjetivos.findByRepeticionesrealizadas", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.repeticionesrealizadas = :repeticionesrealizadas"),
    @NamedQuery(name = "SeguimientoObjetivos.findBySeriesrealizadas", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.seriesrealizadas = :seriesrealizadas"),
    @NamedQuery(name = "SeguimientoObjetivos.findByObservaciones", query = "SELECT s FROM SeguimientoObjetivos s WHERE s.observaciones = :observaciones")})
public class SeguimientoObjetivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeguimientoObjetivosPK seguimientoObjetivosPK;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "realizado")
    private short realizado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pesorealizado")
    private Float pesorealizado;
    @Column(name = "repeticionesrealizadas")
    private Integer repeticionesrealizadas;
    @Column(name = "seriesrealizadas")
    private Integer seriesrealizadas;
    @Column(name = "observaciones")
    private String observaciones;
<<<<<<< Updated upstream
    @JoinColumns({
        @JoinColumn(name = "idejerciciorutina", referencedColumnName = "idejerciciorutina", insertable = false, updatable = false),
        @JoinColumn(name = "idrutina", referencedColumnName = "idrutina", insertable = false, updatable = false),
        @JoinColumn(name = "idejercicio", referencedColumnName = "idejercicio", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private EjercicioRutina ejercicioRutina;
=======
    @Column(name = "pesoobjetivo")
    private Float pesoobjetivo;
    @Column(name = "repeticionesobjetivo")
    private Integer repeticionesobjetivo;
    @Column(name = "seriesobjetivo")
    private Integer seriesobjetivo;
    @Column(name = "nombreejercicio")
    private String nombreejercicio;
    @JoinColumn(name = "idrutina", referencedColumnName = "idrutina")
    @ManyToOne(optional = false)
    private Rutina rutina;
>>>>>>> Stashed changes

    public SeguimientoObjetivos() {
    }

    public SeguimientoObjetivos(SeguimientoObjetivosPK seguimientoObjetivosPK) {
        this.seguimientoObjetivosPK = seguimientoObjetivosPK;
    }

    public SeguimientoObjetivos(SeguimientoObjetivosPK seguimientoObjetivosPK, Date fecha, short realizado) {
        this.seguimientoObjetivosPK = seguimientoObjetivosPK;
        this.fecha = fecha;
        this.realizado = realizado;
    }

    public SeguimientoObjetivos(int idejerciciorutina, int idrutina, int idejercicio) {
        this.seguimientoObjetivosPK = new SeguimientoObjetivosPK(idejerciciorutina, idrutina, idejercicio);
    }

    public SeguimientoObjetivosPK getSeguimientoObjetivosPK() {
        return seguimientoObjetivosPK;
    }

    public void setSeguimientoObjetivosPK(SeguimientoObjetivosPK seguimientoObjetivosPK) {
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

    public Float getPesorealizado() {
        return pesorealizado;
    }

    public void setPesorealizado(Float pesorealizado) {
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

    public Rutina getEjercicioRutina() {
        return rutina;
    }

    public void setRutina(Rutina ejercicioRutina) {
        this.rutina = ejercicioRutina;
    }

    public Rutina getRutina() {return rutina;}

    public String getNombreejercicio() {return nombreejercicio;}

    public void setNombreejercicio(String nombreejercicio) {this.nombreejercicio = nombreejercicio;}

    public Integer getSeriesobjetivo() {return seriesobjetivo;}

    public void setSeriesobjetivo(Integer seriesobjetivo) {this.seriesobjetivo = seriesobjetivo;}

    public Integer getRepeticionesobjetivo() {return repeticionesobjetivo;}

    public void setRepeticionesobjetivo(Integer repeticionesobjetivo) {this.repeticionesobjetivo = repeticionesobjetivo;}

    public Float getPesoobjetivo() {return pesoobjetivo;}

    public void setPesoobjetivo(Float pesoobjetivo) {this.pesoobjetivo = pesoobjetivo;}


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

}
