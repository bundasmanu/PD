/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import controladores.ViagensController;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import tpdtos.ViagemDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "visitanteViagensBean")
@ManagedBean
@SessionScoped
public class VisitanteViagensBean implements Serializable{

    /**
     * Creates a new instance of VisitanteViagensBean
     */
    public VisitanteViagensBean() {
    }
    
    @EJB
    private intermedioLogicaLocal acessoLogica;

    public intermedioLogicaLocal getAcessoLogica() {
        return acessoLogica;
    }

    public void setAcessoLogica(intermedioLogicaLocal acessoLogica) {
        this.acessoLogica = acessoLogica;
    }
    
    public List<ViagemDTO> mostraInfoViagens(){
        
        try{
            
            return this.getAcessoLogica().getSingletonLogica().getViagemBarataDestino();
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
}
