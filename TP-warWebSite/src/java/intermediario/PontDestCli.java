/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import tpdtos.DestinoDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "pontDestCli")
@ManagedBean
@SessionScoped
public class PontDestCli implements Serializable{

    /**
     * Creates a new instance of PontDestCli
     */
    public PontDestCli() {
    }
    
    @EJB
    intermedioLogicaLocal acessoLogica;

    public intermedioLogicaLocal getAcessoLogica() {
        return acessoLogica;
    }

    public void setAcessoLogica(intermedioLogicaLocal acessoLogica) {
        this.acessoLogica = acessoLogica;
    }
    
    public List<DestinoDTO> getDestinosPossoDarPontuacao(){
        
        try{
            
            return this.getAcessoLogica().getSingletonLogica().selectAllDestiniesFromClient((int)SessionContext.getInstance().getAttribute("id"));
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
}
