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
import tpdtos.ViagemDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "viagensLeilao")
@ManagedBean
@SessionScoped
public class ViagensLeilao implements Serializable{

    /**
     * Creates a new instance of ViagensLeilao
     */
    public ViagensLeilao() {
    }
    
    @EJB
    private intermedioLogicaLocal acessoLogica;

    public intermedioLogicaLocal getAcessoLogica() {
        return acessoLogica;
    }

    public void setAcessoLogica(intermedioLogicaLocal acessoLogica) {
        this.acessoLogica = acessoLogica;
    }
    
    public List<ViagemDTO> getViagensLeilao(){
        
        try{
            
            List<ViagemDTO> viagens_retornadas_leilao= this.getAcessoLogica().getSingletonLogica().getViagensEmLeilao();
            
            return viagens_retornadas_leilao;
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
}
