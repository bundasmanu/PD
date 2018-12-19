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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gustavo
 */
@Entity
@Table(name = "partidas")
@NamedQueries({
    @NamedQuery(name = "Partidas.findAll", query = "SELECT p FROM Partidas p")
    , @NamedQuery(name = "Partidas.findByIdPartida", query = "SELECT p FROM Partidas p WHERE p.idPartida = :idPartida")
    , @NamedQuery(name = "Partidas.findByCidadePartida", query = "SELECT p FROM Partidas p WHERE p.cidadePartida = :cidadePartida")})
public class Partidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_partida")
    private Integer idPartida;
    @Basic(optional = false)
    @Column(name = "cidade_partida")
    private String cidadePartida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPartida")
    private Collection<Viagens> viagensCollection;

    public Partidas() {
    }

    public Partidas(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Partidas(Integer idPartida, String cidadePartida) {
        this.idPartida = idPartida;
        this.cidadePartida = cidadePartida;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public String getCidadePartida() {
        return cidadePartida;
    }

    public void setCidadePartida(String cidadePartida) {
        this.cidadePartida = cidadePartida;
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
        hash += (idPartida != null ? idPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partidas)) {
            return false;
        }
        Partidas other = (Partidas) object;
        if ((this.idPartida == null && other.idPartida != null) || (this.idPartida != null && !this.idPartida.equals(other.idPartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Partidas[ idPartida=" + idPartida + " ]";
    }
    
}
