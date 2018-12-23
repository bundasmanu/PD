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
@Table(name = "bagagens")
@NamedQueries({
    @NamedQuery(name = "Bagagens.findAll", query = "SELECT b FROM Bagagens b")
    , @NamedQuery(name = "Bagagens.findByIdBagagens", query = "SELECT b FROM Bagagens b WHERE b.idBagagens = :idBagagens")
    , @NamedQuery(name = "Bagagens.findByPesoBagagens", query = "SELECT b FROM Bagagens b WHERE b.pesoBagagens = :pesoBagagens")})
public class Bagagens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_bagagens")
    private Integer idBagagens;
    @Basic(optional = false)
    @Column(name = "peso_bagagens")
    private int pesoBagagens;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;

    public Bagagens() {
    }

    public Bagagens(Integer idBagagens) {
        this.idBagagens = idBagagens;
    }

    public Bagagens(Integer idBagagens, int pesoBagagens) {
        this.idBagagens = idBagagens;
        this.pesoBagagens = pesoBagagens;
    }

    public Integer getIdBagagens() {
        return idBagagens;
    }

    public void setIdBagagens(Integer idBagagens) {
        this.idBagagens = idBagagens;
    }

    public int getPesoBagagens() {
        return pesoBagagens;
    }

    public void setPesoBagagens(int pesoBagagens) {
        this.pesoBagagens = pesoBagagens;
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
        hash += (idBagagens != null ? idBagagens.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bagagens)) {
            return false;
        }
        Bagagens other = (Bagagens) object;
        if ((this.idBagagens == null && other.idBagagens != null) || (this.idBagagens != null && !this.idBagagens.equals(other.idBagagens))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Bagagens[ idBagagens=" + idBagagens + " ]";
    }
    
}
