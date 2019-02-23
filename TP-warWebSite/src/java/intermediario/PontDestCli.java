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
import tpdtos.DestinoDTO;
import tpdtos.PontuacaoDTO;

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
      
    private String cidade_destino_pontua;
    private int pontuacao_destino;
    
    public PontDestCli() {
    }
    
    @EJB
    intermedioLogicaLocal acessoLogica;

    @Inject
    LoginBean login;
  
    
    public intermedioLogicaLocal getAcessoLogica() {
        return acessoLogica;
    }

    public void setAcessoLogica(intermedioLogicaLocal acessoLogica) {
        this.acessoLogica = acessoLogica;
    }

    public String getCidade_destino_pontua() {
        return cidade_destino_pontua;
    }

    public void setCidade_destino_pontua(String cidade_destino_pontua) {
        this.cidade_destino_pontua = cidade_destino_pontua;
    }

    public int getPontuacao_destino() {
        return pontuacao_destino;
    }

    public void setPontuacao_destino(int pontuacao_destino) {
        this.pontuacao_destino = pontuacao_destino;
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
    
    public String enviaPontuacao(){
        
        try{
            
            boolean retorno_insere_pont_dest=this.acessoLogica.getSingletonLogica().inserePontDestino(this.getPontuacao_destino(), this.login.getMail() , this.getCidade_destino_pontua());
            if(retorno_insere_pont_dest==true){
                return "/PontSucesso.xhtml?faces-redirect=true?";
            }
            
            return null;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    public List<PontuacaoDTO> selecionaTodasPontDest(){
        try{
            String email_cli=(String) SessionContext.getInstance().getAttribute("cli");
            List<PontuacaoDTO> lista_pont_dest= this.acessoLogica.getSingletonLogica().selectAllPontuacoesDestino(email_cli);
            if(lista_pont_dest==null){
                return null;
            }
            return lista_pont_dest;
        }catch(Exception e){
            System.out.println(""+e.getMessage());
            return null;
        }
    }
    
}
