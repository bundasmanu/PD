/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gustavo
 */
@Entity
@Table(name = "destinos")
@NamedQueries({
    @NamedQuery(name = "Destinos.findAll", query = "SELECT d FROM Destinos d")
    , @NamedQuery(name = "Destinos.findByIdDestino", query = "SELECT d FROM Destinos d WHERE d.idDestino = :idDestino")
    , @NamedQuery(name = "Destinos.findByCidadeDestino", query = "SELECT d FROM Destinos d WHERE d.cidadeDestino = :cidadeDestino")})
public class Destinos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_destino")
    private Integer idDestino;
    @Basic(optional = false)
    @Column(name = "cidade_destino")
    private String cidadeDestino;
    @ManyToMany(mappedBy = "destinosCollection")
    private Collection<Pontuacao> pontuacaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDestino")
    private Collection<Viagens> viagensCollection;

    public Destinos() {
    }

    public Destinos(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public Destinos(Integer idDestino, String cidadeDestino) {
        this.idDestino = idDestino;
        this.cidadeDestino = cidadeDestino;
    }

    public Integer getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public Collection<Pontuacao> getPontuacaoCollection() {
        return pontuacaoCollection;
    }

    public void setPontuacaoCollection(Collection<Pontuacao> pontuacaoCollection) {
        this.pontuacaoCollection = pontuacaoCollection;
    }

    public Collection<Viagens> getViagensCollection() {
        return viagensCollection;
    }

    public void setViagensCollection(Collection<Viagens> viagensCollection) {
        this.viagensCollection = viagensCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDestino != null ? idDestino.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destinos)) {
            return false;
        }
        Destinos other = (Destinos) object;
        if ((this.idDestino == null && other.idDestino != null) || (this.idDestino != null && !this.idDestino.equals(other.idDestino))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Destinos[ idDestino=" + idDestino + " ]";
    }
    
}
