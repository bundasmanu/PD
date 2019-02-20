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
import javax.inject.Inject;
import tpdtos.CompanhiaDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "pontCompCli")
@ManagedBean
@SessionScoped
public class PontCompCli implements Serializable{

    /**
     * Creates a new instance of PontCompCli
     */
    public PontCompCli() {
    }
    
    @EJB
    intermedioLogicaLocal acessoLogica;

    public intermedioLogicaLocal getAcessoLogica() {
        return acessoLogica;
    }

    public void setAcessoLogica(intermedioLogicaLocal acessoLogica) {
        this.acessoLogica = acessoLogica;
    }
    
    public List<CompanhiaDTO> getCompanhiaPossoDarPontuacao(){
        
        try{
            
            return this.getAcessoLogica().getSingletonLogica().selectAllCompaniesFromClient((int)SessionContext.getInstance().getAttribute("id"));
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
}
