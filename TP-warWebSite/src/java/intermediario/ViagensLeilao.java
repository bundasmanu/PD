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
    @Inject
    LoginBean login;
    private int id_viagem;
    private double preco_viagem_leilao;

    public intermedioLogicaLocal getAcessoLogica() {
        return acessoLogica;
    }

    public void setAcessoLogica(intermedioLogicaLocal acessoLogica) {
        this.acessoLogica = acessoLogica;
    }

    public int getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(int id_viagem) {
        this.id_viagem = id_viagem;
    }

    public double getPreco_viagem_leilao() {
        return preco_viagem_leilao;
    }

    public void setPreco_viagem_leilao(double preco_viagem_leilao) {
        this.preco_viagem_leilao = preco_viagem_leilao;
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
    
    public String insereBilheteLeilao(){
        
        try{
            
            boolean retorno=this.acessoLogica.getSingletonLogica().insereBilheteLeilao(id_viagem, login.getId() , (int)this.getPreco_viagem_leilao());
            if(retorno==true){
                return "/index.xhtml?faces-redirect=true?";
            }
            
            return "/erro.xhtml?faces-redirect=true?";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "/erro.xhtml?faces-redirect=true?";
        }
        
    }
    
}
