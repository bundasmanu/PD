/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gustavo
 */
@Entity
@Table(name = "pontuacao")
@NamedQueries({
    @NamedQuery(name = "Pontuacao.findAll", query = "SELECT p FROM Pontuacao p")
    , @NamedQuery(name = "Pontuacao.findByIdPontuacao", query = "SELECT p FROM Pontuacao p WHERE p.idPontuacao = :idPontuacao")
    , @NamedQuery(name = "Pontuacao.findByValor", query = "SELECT p FROM Pontuacao p WHERE p.valor = :valor")})
public class Pontuacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_pontuacao")
    private Integer idPontuacao;
    @Basic(optional = false)
    @Column(name = "valor")
    private int valor;
    @ManyToMany(mappedBy = "pontuacaoCollection")
    private Collection<Partidas> partidasCollection;
    @ManyToMany(mappedBy = "pontuacaoCollection")
    private Collection<Companhia> companhiaCollection;
    @ManyToMany(mappedBy = "pontuacaoCollection")
    private Collection<Destinos> destinosCollection;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;

    public Pontuacao() {
    }

    public Pontuacao(Integer idPontuacao) {
        this.idPontuacao = idPontuacao;
    }

    public Pontuacao(Integer idPontuacao, int valor) {
        this.idPontuacao = idPontuacao;
        this.valor = valor;
    }

    public Integer getIdPontuacao() {
        return idPontuacao;
    }

    public void setIdPontuacao(Integer idPontuacao) {
        this.idPontuacao = idPontuacao;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Collection<Partidas> getPartidasCollection() {
        return partidasCollection;
    }

    public void setPartidasCollection(Collection<Partidas> partidasCollection) {
        this.partidasCollection = partidasCollection;
    }

    public Collection<Companhia> getCompanhiaCollection() {
        return companhiaCollection;
    }

    public void setCompanhiaCollection(Collection<Companhia> companhiaCollection) {
        this.companhiaCollection = companhiaCollection;
    }

    public Collection<Destinos> getDestinosCollection() {
        return destinosCollection;
    }

    public void setDestinosCollection(Collection<Destinos> destinosCollection) {
        this.destinosCollection = destinosCollection;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPontuacao != null ? idPontuacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pontuacao)) {
            return false;
        }
        Pontuacao other = (Pontuacao) object;
        if ((this.idPontuacao == null && other.idPontuacao != null) || (this.idPontuacao != null && !this.idPontuacao.equals(other.idPontuacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Pontuacao[ idPontuacao=" + idPontuacao + " ]";
    }
    
}
