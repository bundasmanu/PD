/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import java.io.Serializable;
import java.text.Normalizer.Form;
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
@Named(value = "compraBilhete")
@ManagedBean
@SessionScoped
public class CompraBilhete implements Serializable{

    /**
     * Creates a new instance of CompraBilhete
     */
    public CompraBilhete() {
    }
    
    @EJB
    intermedioLogicaLocal acessoLogica;
    
    @Inject
    ViagensBean viagens;
    
    @Inject
    LoginBean l;        
    
    int num_bil;
    int id_viagem_comprar;
    
    public intermedioLogicaLocal getAcessoLogica() {
        return acessoLogica;
    }

    public ViagensBean getViagens() {
        return viagens;
    }

    public void setAcessoLogica(intermedioLogicaLocal acessoLogica) {
        this.acessoLogica = acessoLogica;
    }

    public void setViagens(ViagensBean viagens) {
        this.viagens = viagens;
    }

    public int getNum_bil() {
        return num_bil;
    }

    public void setNum_bil(int num_bil) {
        this.num_bil = num_bil;
    }

    public int getId_viagem_comprar() {
        return id_viagem_comprar;
    }

    public void setId_viagem_comprar(int id_viagem_comprar) {
        this.id_viagem_comprar = id_viagem_comprar;
    }

    public LoginBean getL() {
        return l;
    }

    public void setL(LoginBean l) {
        this.l = l;
    }

    public List<ViagemDTO> getViagensNormais(){
        
        try{
            
            /*OBTENCAO DE TODAS AS VIAGENS QUE ESTAO NO ESTADO EM PROCESSO*/
            return this.acessoLogica.getSingletonLogica().getViagensNormais();
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    public String compraViagens(){
        
        try{
            
            if(this.num_bil<=0){
                return "/erro.xhtml?faces-redirect=true?";
            }
            
            /*ACEDE AOS DADOS DOS DADOS, NUMERO DE BILHETES QUE O CLIENTE QUER COMPRAR E O ID DA VIAGEM QUE ESTE PRETENDE COMPRAR*/
            boolean retorno_numero=this.acessoLogica.getSingletonLogica().lugaresVagosViagem(this.getId_viagem_comprar(), num_bil);
            
            if(retorno_numero==true){
                for(int i=0;i<num_bil;++i){
                   boolean x=this.acessoLogica.getSingletonLogica().insereBilhete(this.getId_viagem_comprar(), l.getId());
                   if(x==false){
                       return "/erro.xhtml?faces-redirect=true?";
                   }
                }
                return "/index.xhtml?faces-redirect=true?";
            }
            
            return "/erro.xhtml?faces-redirect=true?";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "/erro.xhtml?faces-redirect=true?";
        }
        
    }
    
    public ViagemDTO verificaViagemInversa(){
        
        try{
            
            /*VERIFICAR PARA A VIAGEM SELECCIONADA, SE EXISTE DISPONIBILIDADE DE VIAGEM INVERSA*/
            ViagemDTO verifica_inversa=this.acessoLogica.getSingletonLogica().seleccionaViagemInversa(this.getId_viagem_comprar());
            
            if(verifica_inversa==null){
                return null;
            }
            
            return verifica_inversa; 
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
}
