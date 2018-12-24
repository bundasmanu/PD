/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gustavo
 */
@Entity
@Table(name = "viagens")
@NamedQueries({
    @NamedQuery(name = "Viagens.findAll", query = "SELECT v FROM Viagens v")
    , @NamedQuery(name = "Viagens.findByIdViagens", query = "SELECT v FROM Viagens v WHERE v.idViagens = :idViagens")
    , @NamedQuery(name = "Viagens.findByNumLugares", query = "SELECT v FROM Viagens v WHERE v.numLugares = :numLugares")
    , @NamedQuery(name = "Viagens.findByHoraPartida", query = "SELECT v FROM Viagens v WHERE v.horaPartida = :horaPartida")
    , @NamedQuery(name = "Viagens.findByHoraChegada", query = "SELECT v FROM Viagens v WHERE v.horaChegada = :horaChegada")})
public class Viagens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_viagens")
    private Integer idViagens;
    @Basic(optional = false)
    @Column(name = "num_lugares")
    private int numLugares;
    @Basic(optional = false)
    @Column(name = "hora_partida")
    @Temporal(TemporalType.DATE)
    private Date horaPartida;
    @Basic(optional = false)
    @Column(name = "hora_chegada")
    @Temporal(TemporalType.DATE)
    private Date horaChegada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idViagens")
    private Collection<Bilhete> bilheteCollection;
    @JoinColumn(name = "id_aviao", referencedColumnName = "id_aviao")
    @ManyToOne(optional = false)
    private Aviao idAviao;
    @JoinColumn(name = "id_destino", referencedColumnName = "id_destino")
    @ManyToOne(optional = false)
    private Destinos idDestino;
    @JoinColumn(name = "id_partida", referencedColumnName = "id_partida")
    @ManyToOne(optional = false)
    private Partidas idPartida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idViagens")
    private Collection<Bagagens> bagagensCollection;

    public Viagens() {
    }

    public Viagens(Integer idViagens) {
        this.idViagens = idViagens;
    }

    public Viagens(Integer idViagens, int numLugares, Date horaPartida, Date horaChegada) {
        this.idViagens = idViagens;
        this.numLugares = numLugares;
        this.horaPartida = horaPartida;
        this.horaChegada = horaChegada;
    }

    public Integer getIdViagens() {
        return idViagens;
    }

    public void setIdViagens(Integer idViagens) {
        this.idViagens = idViagens;
    }

    public int getNumLugares() {
        return numLugares;
    }

    public void setNumLugares(int numLugares) {
        this.numLugares = numLugares;
    }

    public Date getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(Date horaPartida) {
        this.horaPartida = horaPartida;
    }

    public Date getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(Date horaChegada) {
        this.horaChegada = horaChegada;
    }

    public Collection<Bilhete> getBilheteCollection() {
        return bilheteCollection;
    }

    public void setBilheteCollection(Collection<Bilhete> bilheteCollection) {
        this.bilheteCollection = bilheteCollection;
    }

    public Aviao getIdAviao() {
        return idAviao;
    }

    public void setIdAviao(Aviao idAviao) {
        this.idAviao = idAviao;
    }

    public Destinos getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Destinos idDestino) {
        this.idDestino = idDestino;
    }

    public Partidas getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Partidas idPartida) {
        this.idPartida = idPartida;
    }

    public Collection<Bagagens> getBagagensCollection() {
        return bagagensCollection;
    }

    public void setBagagensCollection(Collection<Bagagens> bagagensCollection) {
        this.bagagensCollection = bagagensCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idViagens != null ? idViagens.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viagens)) {
            return false;
        }
        Viagens other = (Viagens) object;
        if ((this.idViagens == null && other.idViagens != null) || (this.idViagens != null && !this.idViagens.equals(other.idViagens))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Viagens[ idViagens=" + idViagens + " ]";
    }
    
}
