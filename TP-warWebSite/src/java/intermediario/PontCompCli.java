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
import tpdtos.PontuacaoDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "pontCompCli")
@ManagedBean
@SessionScoped
public class PontCompCli implements Serializable {

    /**
     * Creates a new instance of PontCompCli
     */
    
    private String nome_companhia;
    private int valor_atribuido;
    
    
    public PontCompCli() {
    }

    @EJB
    intermedioLogicaLocal acessoLogica;
    
    @Inject
    LoginBean login;

    private String nomeCompanhia;
    private int valor_pontuacao_comp;
    
    public intermedioLogicaLocal getAcessoLogica() {
        return acessoLogica;
    }

    public void setAcessoLogica(intermedioLogicaLocal acessoLogica) {
        this.acessoLogica = acessoLogica;
    }

    public String getNomeCompanhia() {
        return nomeCompanhia;
    }

    public int getValor_pontuacao_comp() {
        return valor_pontuacao_comp;
    }

    public void setNomeCompanhia(String nomeCompanhia) {
        this.nomeCompanhia = nomeCompanhia;
    }

    public void setValor_pontuacao_comp(int valor_pontuacao_comp) {
        this.valor_pontuacao_comp = valor_pontuacao_comp;
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
    
    public String enviaPontuacao(){
        
        try{
            
            boolean retorno_pont_comp=this.acessoLogica.getSingletonLogica().inserePontComp(this.getValor_pontuacao_comp(), this.login.getMail(), this.getNomeCompanhia());
            if(retorno_pont_comp==true){
                return "/PontSucesso.xhtml?faces-redirect=true?";
            }
            
            return "/erro.xhtml?faces-redirect=true?";       
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "/erro.xhtml?faces-redirect=true?";
        }
        
    }
    
}
