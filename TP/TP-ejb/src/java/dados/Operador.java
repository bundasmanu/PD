/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gustavo
 */
@Entity
@Table(name = "operador")
@NamedQueries({
    @NamedQuery(name = "Operador.findAll", query = "SELECT o FROM Operador o")
    , @NamedQuery(name = "Operador.findByIdOperador", query = "SELECT o FROM Operador o WHERE o.idOperador = :idOperador")
    , @NamedQuery(name = "Operador.findByNomeOperador", query = "SELECT o FROM Operador o WHERE o.nomeOperador = :nomeOperador")
    , @NamedQuery(name = "Operador.findByEmailOperador", query = "SELECT o FROM Operador o WHERE o.emailOperador = :emailOperador")
    , @NamedQuery(name = "Operador.findByPassOperador", query = "SELECT o FROM Operador o WHERE o.passOperador = :passOperador")})
public class Operador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_operador")
    private Integer idOperador;
    @Basic(optional = false)
    @Column(name = "nome_operador")
    private String nomeOperador;
    @Basic(optional = false)
    @Column(name = "email_operador")
    private String emailOperador;
    @Basic(optional = false)
    @Column(name = "pass_operador")
    private String passOperador;

    public Operador() {
    }

    public Operador(Integer idOperador) {
        this.idOperador = idOperador;
    }

    public Operador(Integer idOperador, String nomeOperador, String emailOperador, String passOperador) {
        this.idOperador = idOperador;
        this.nomeOperador = nomeOperador;
        this.emailOperador = emailOperador;
        this.passOperador = passOperador;
    }

    public Integer getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(Integer idOperador) {
        this.idOperador = idOperador;
    }

    public String getNomeOperador() {
        return nomeOperador;
    }

    public void setNomeOperador(String nomeOperador) {
        this.nomeOperador = nomeOperador;
    }

    public String getEmailOperador() {
        return emailOperador;
    }

    public void setEmailOperador(String emailOperador) {
        this.emailOperador = emailOperador;
    }

    public String getPassOperador() {
        return passOperador;
    }

    public void setPassOperador(String passOperador) {
        this.passOperador = passOperador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperador != null ? idOperador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operador)) {
            return false;
        }
        Operador other = (Operador) object;
        if ((this.idOperador == null && other.idOperador != null) || (this.idOperador != null && !this.idOperador.equals(other.idOperador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Operador[ idOperador=" + idOperador + " ]";
    }
    
}
