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
@Table(name = "agencia_viagens")
@NamedQueries({
    @NamedQuery(name = "AgenciaViagens.findAll", query = "SELECT a FROM AgenciaViagens a")
    , @NamedQuery(name = "AgenciaViagens.findByIdAgencia", query = "SELECT a FROM AgenciaViagens a WHERE a.idAgencia = :idAgencia")
    , @NamedQuery(name = "AgenciaViagens.findByNomeAgencia", query = "SELECT a FROM AgenciaViagens a WHERE a.nomeAgencia = :nomeAgencia")})
public class AgenciaViagens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_agencia")
    private Integer idAgencia;
    @Basic(optional = false)
    @Column(name = "nome_agencia")
    private String nomeAgencia;
    @ManyToMany(mappedBy = "agenciaViagensCollection")
    private Collection<Companhia> companhiaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgencia")
    private Collection<Operador> operadorCollection;

    public AgenciaViagens() {
    }

    public AgenciaViagens(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }

    public AgenciaViagens(Integer idAgencia, String nomeAgencia) {
        this.idAgencia = idAgencia;
        this.nomeAgencia = nomeAgencia;
    }

    public Integer getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getNomeAgencia() {
        return nomeAgencia;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }

    public Collection<Companhia> getCompanhiaCollection() {
        return companhiaCollection;
    }

    public void setCompanhiaCollection(Collection<Companhia> companhiaCollection) {
        this.companhiaCollection = companhiaCollection;
    }

    public Collection<Operador> getOperadorCollection() {
        return operadorCollection;
    }

    public void setOperadorCollection(Collection<Operador> operadorCollection) {
        this.operadorCollection = operadorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgencia != null ? idAgencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AgenciaViagens)) {
            return false;
        }
        AgenciaViagens other = (AgenciaViagens) object;
        if ((this.idAgencia == null && other.idAgencia != null) || (this.idAgencia != null && !this.idAgencia.equals(other.idAgencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.AgenciaViagens[ idAgencia=" + idAgencia + " ]";
    }
    
}
