/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gustavo
 */
@Entity
@Table(name = "viagens")
@NamedQueries({
    @NamedQuery(name = "Viagens.findAll", query = "SELECT v FROM Viagens v")
    , @NamedQuery(name = "Viagens.findByIdViagens", query = "SELECT v FROM Viagens v WHERE v.idViagens = :idViagens")
    , @NamedQuery(name = "Viagens.findByHoraChegada", query = "SELECT v FROM Viagens v WHERE v.horaChegada = :horaChegada")
    , @NamedQuery(name = "Viagens.findByHoraPartida", query = "SELECT v FROM Viagens v WHERE v.horaPartida = :horaPartida")
    , @NamedQuery(name = "Viagens.findByPreco", query = "SELECT v FROM Viagens v WHERE v.preco = :preco")})
public class Viagens implements Serializable {

    @Column(name = "estado_viagem")
    private String estadoViagem;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_viagens")
    private Integer idViagens;
    @Column(name = "hora_chegada")
    private Integer horaChegada;
    @Column(name = "hora_partida")
    private Integer horaPartida;
    @Basic(optional = false)
    @Column(name = "preco")
    private int preco;
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

    public Viagens() {
    }

    public Viagens(Integer idViagens) {
        this.idViagens = idViagens;
    }

    public Viagens(Integer idViagens, int preco) {
        this.idViagens = idViagens;
        this.preco = preco;
    }

    public Integer getIdViagens() {
        return idViagens;
    }

    public void setIdViagens(Integer idViagens) {
        this.idViagens = idViagens;
    }

    public Integer getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(Integer horaChegada) {
        this.horaChegada = horaChegada;
    }

    public Integer getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(Integer horaPartida) {
        this.horaPartida = horaPartida;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
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

    public String getEstadoViagem() {
        return estadoViagem;
    }

    public void setEstadoViagem(String estadoViagem) {
        this.estadoViagem = estadoViagem;
    }
    
}
