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
import javax.persistence.JoinTable;
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
@Table(name = "companhia")
@NamedQueries({
    @NamedQuery(name = "Companhia.findAll", query = "SELECT c FROM Companhia c")
    , @NamedQuery(name = "Companhia.findByIdCompanhia", query = "SELECT c FROM Companhia c WHERE c.idCompanhia = :idCompanhia")
    , @NamedQuery(name = "Companhia.findByNomeCompanhia", query = "SELECT c FROM Companhia c WHERE c.nomeCompanhia = :nomeCompanhia")
    , @NamedQuery(name = "Companhia.findByPontuacaoMedia", query = "SELECT c FROM Companhia c WHERE c.pontuacaoMedia = :pontuacaoMedia")})
public class Companhia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_companhia")
    private Integer idCompanhia;
    @Basic(optional = false)
    @Column(name = "nome_companhia")
    private String nomeCompanhia;
    @Basic(optional = false)
    @Column(name = "pontuacao_media")
    private float pontuacaoMedia;
    @JoinTable(name = "pont_companhia", joinColumns = {
        @JoinColumn(name = "id_companhia", referencedColumnName = "id_companhia")}, inverseJoinColumns = {
        @JoinColumn(name = "id_pontuacao", referencedColumnName = "id_pontuacao")})
    @ManyToMany
    private Collection<Pontuacao> pontuacaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCompanhia")
    private Collection<Aviao> aviaoCollection;

    public Companhia() {
    }

    public Companhia(String nomeCompanhia) {
        this.nomeCompanhia = nomeCompanhia;
        this.pontuacaoMedia = 0;
    }
    
        public Companhia(Integer idCompanhia, String nomeCompanhia) {
        this.idCompanhia = idCompanhia;
        this.nomeCompanhia = nomeCompanhia;
        this.pontuacaoMedia = 0;
    }

    public Integer getIdCompanhia() {
        return idCompanhia;
    }

    public void setIdCompanhia(Integer idCompanhia) {
        this.idCompanhia = idCompanhia;
    }

    public String getNomeCompanhia() {
        return nomeCompanhia;
    }

    public void setNomeCompanhia(String nomeCompanhia) {
        this.nomeCompanhia = nomeCompanhia;
    }

    public float getPontuacaoMedia() {
        return pontuacaoMedia;
    }

    public void setPontuacaoMedia(float pontuacaoMedia) {
        this.pontuacaoMedia = pontuacaoMedia;
    }

    public Collection<Pontuacao> getPontuacaoCollection() {
        return pontuacaoCollection;
    }

    public void setPontuacaoCollection(Collection<Pontuacao> pontuacaoCollection) {
        this.pontuacaoCollection = pontuacaoCollection;
    }

    public Collection<Aviao> getAviaoCollection() {
        return aviaoCollection;
    }

    public void setAviaoCollection(Collection<Aviao> aviaoCollection) {
        this.aviaoCollection = aviaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompanhia != null ? idCompanhia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companhia)) {
            return false;
        }
        Companhia other = (Companhia) object;
        if ((this.idCompanhia == null && other.idCompanhia != null) || (this.idCompanhia != null && !this.idCompanhia.equals(other.idCompanhia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Companhia[ idCompanhia=" + idCompanhia + " ]";
    }
    
}
