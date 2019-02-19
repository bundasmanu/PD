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
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import tpdtos.BagagemDTO;
import tpdtos.ViagemDTO;

/**
 *
 * @author pedro
 */
@Named(value = "bagagemBean")
@ManagedBean
@SessionScoped
public class BagabemBean implements Serializable{
    private int id_bagagem;
    private int peso_bagagem;
    private ViagemDTO viagem;  
     
     @EJB
     intermedioLogicaLocal acessoLogica;
     
//     @EJB
//     ClienteBean cliente;
     
    public BagabemBean(){
        
    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public int getId_bagagem() {
        return id_bagagem;
    }

    public void setId_bagagem(int id_bagagem) {
        this.id_bagagem = id_bagagem;
    }

    public int getPeso_bagagem() {
        return peso_bagagem;
    }

    public void setPeso_bagagem(int peso_bagagem) {
        this.peso_bagagem = peso_bagagem;
    }

    public ViagemDTO getViagem() {
        return viagem;
    }

    public void setViagem(ViagemDTO viagem) {
        this.viagem = viagem;
    }
    
    public List<BagagemDTO> selecionaAllBagagens(){
        /*DEPOIS DE FAZER LOGIN, SEI QUAL É O UTILIZADOR, E ENCONTRA-SE DEFINIDO NA CLASSE SESSIONCONTEXT*/
        String cli= (String)SessionContext.getInstance().getAttribute("cli");
        //this.cliente.setEmail_cliente(cli);
        
        List<BagagemDTO> bagagens= this.acessoLogica.getSingletonLogica().SelecionaBagagens(cli);
        if(bagagens==null){
            return null;
        }
        
        return bagagens;
    }

   
}
