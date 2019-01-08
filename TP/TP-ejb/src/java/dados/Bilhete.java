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
@Table(name = "bilhete")
@NamedQueries({
    @NamedQuery(name = "Bilhete.findAll", query = "SELECT b FROM Bilhete b")
    , @NamedQuery(name = "Bilhete.findByIdBilhete", query = "SELECT b FROM Bilhete b WHERE b.idBilhete = :idBilhete")
    , @NamedQuery(name = "Bilhete.findByPrecoBilhete", query = "SELECT b FROM Bilhete b WHERE b.precoBilhete = :precoBilhete")})
public class Bilhete implements Serializable {

    @Column(name = "lugar")
    private Integer lugar;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_bilhete")
    private Integer idBilhete;
    @Basic(optional = false)
    @Column(name = "preco_bilhete")
    private int precoBilhete;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @JoinColumn(name = "id_viagens", referencedColumnName = "id_viagens")
    @ManyToOne(optional = false)
    private Viagens idViagens;

    public Bilhete() {
    }

    public Bilhete(Integer idBilhete) {
        this.idBilhete = idBilhete;
    }

    public Bilhete(Integer idBilhete, int precoBilhete) {
        this.idBilhete = idBilhete;
        this.precoBilhete = precoBilhete;
    }

    public Integer getIdBilhete() {
        return idBilhete;
    }

    public void setIdBilhete(Integer idBilhete) {
        this.idBilhete = idBilhete;
    }

    public int getPrecoBilhete() {
        return precoBilhete;
    }

    public void setPrecoBilhete(int precoBilhete) {
        this.precoBilhete = precoBilhete;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Viagens getIdViagens() {
        return idViagens;
    }

    public void setIdViagens(Viagens idViagens) {
        this.idViagens = idViagens;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBilhete != null ? idBilhete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bilhete)) {
            return false;
        }
        Bilhete other = (Bilhete) object;
        if ((this.idBilhete == null && other.idBilhete != null) || (this.idBilhete != null && !this.idBilhete.equals(other.idBilhete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Bilhete[ idBilhete=" + idBilhete + " ]";
    }

    public Integer getLugar() {
        return lugar;
    }

    public void setLugar(Integer lugar) {
        this.lugar = lugar;
    }
    
}
