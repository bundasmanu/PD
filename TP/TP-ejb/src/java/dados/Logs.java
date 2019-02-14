/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gustavo
 */
@Entity
@Table(name = "logs")
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l")
    , @NamedQuery(name = "Logs.findByIdLog", query = "SELECT l FROM Logs l WHERE l.idLog = :idLog")
    , @NamedQuery(name = "Logs.findByInfoLog", query = "SELECT l FROM Logs l WHERE l.infoLog = :infoLog")
    , @NamedQuery(name = "Logs.findByDataLog", query = "SELECT l FROM Logs l WHERE l.dataLog = :dataLog")})
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_log")
    private Integer idLog;
    @Basic(optional = false)
    @Column(name = "info_log")
    private String infoLog;
    @Basic(optional = false)
    @Column(name = "data_log")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLog;

    public Logs() {
    }

    public Logs(Integer idLog) {
        this.idLog = idLog;
    }
    
    public Logs(String infoLOg, Date dataLog){
        this.infoLog = infoLOg;
        this.dataLog = dataLog;
    }

    public Logs(Integer idLog, String infoLog, Date dataLog) {
        this.idLog = idLog;
        this.infoLog = infoLog;
        this.dataLog = dataLog;
    }

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }

    public String getInfoLog() {
        return infoLog;
    }

    public void setInfoLog(String infoLog) {
        this.infoLog = infoLog;
    }

    public Date getDataLog() {
        return dataLog;
    }

    public void setDataLog(Date dataLog) {
        this.dataLog = dataLog;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLog != null ? idLog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.idLog == null && other.idLog != null) || (this.idLog != null && !this.idLog.equals(other.idLog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dados.Logs[ idLog=" + idLog + " ]";
    }
    
}
