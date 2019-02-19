/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpdtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author gustavo
 */
public class LogDTO implements Serializable{
    
    private String info_log;
    private Date data_log;
    
    public LogDTO(){
        
    }
    
    public LogDTO(String info_l, Date data_l){
        this.info_log=info_l;
        this.data_log=data_l;
    }

    public String getInfo_log() {
        return info_log;
    }

    public Date getData_log() {
        return data_log;
    }

    public void setInfo_log(String info_log) {
        this.info_log = info_log;
    }

    public void setData_log(Date data_log) {
        this.data_log = data_log;
    }
    
    @Override
    public String toString(){
        
        String info_logs="";
        
        info_logs+=this.getInfo_log()+"\t"+this.getData_log()+"\n";
        
        return info_logs;
    }
    
}
