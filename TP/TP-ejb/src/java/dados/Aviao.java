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
@Table(name = "aviao")
@NamedQueries({
    @NamedQuery(name = "Aviao.findAll", query = "SELECT a FROM Aviao a")
    , @NamedQuery(name = "Aviao.findByIdAviao", query = "SELECT a FROM Aviao a WHERE a.idAviao = :idAviao")
    , @NamedQuery(name = "Aviao.findByNomeAviao", query = "SELECT a FROM Aviao a WHERE a.nomeAviao = :nomeAviao")
    , @NamedQuery(name = "Aviao.findByNumLugares", query = "SELECT a FROM Aviao a WHERE a.numLugares = :numLugares")})
public class Aviao implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAviao")
    private Collection<Viagens> viagensCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_aviao")
    private Integer idAviao;
    @Basic(optional = false)
    @Column(name = "nome_aviao")
    private String nomeAviao;
    @Basic(optional = false)
    @Column(name = "num_lugares")
    private int numLugares;
    @JoinColumn(name = "id_companhia", referencedColumnName = "id_companhia")
    @ManyToOne(optional = false)
    private Companhia idCompanhia;

    public Aviao() {
    }

    public Aviao(Integer idAviao) {
        this.idAviao = idAviao;
    }

    public Aviao(String nomeAviao, int numLugares) {
        this.nomeAviao = nomeAviao;
        this.numLugares = numLugares;
    }

    public Integer getIdAviao() {
        return idAviao;
    }

    public void setIdAviao(Integer idAviao) {
        this.idAviao = idAviao;
    }

    public String getNomeAviao() {
        return nomeAviao;
    }

    public void setNomeAviao(String nomeAviao) {
        this.nomeAviao = nomeAviao;
    }

    public int getNumLugares() {
        return numLugares;
    }

    public void setNumLugares(int numLugares) {
        this.numLugares = numLugares;
    }

    public Companhia getIdCompanhia() {
        return idCompanhia;
    }

    public void setIdCompanhia(Companhia idCompanhia) {
        this.idCompanhia = idCompanhia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAviao != null ? idAviao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aviao)) {
            return false;
        }
        Aviao other = (Aviao) object;
        if ((this.idAviao == null && other.idAviao != null) || (this.idAviao != null && !this.idAviao.equals(other.idAviao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Aviao[ idAviao=" + idAviao + " ]";
    }

    public Collection<Viagens> getViagensCollection() {
        return viagensCollection;
    }

    public void setViagensCollection(Collection<Viagens> viagensCollection) {
        this.viagensCollection = viagensCollection;
    }
    
}
