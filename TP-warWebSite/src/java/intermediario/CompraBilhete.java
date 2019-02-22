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
    
    int numeroBilhetesComprar;
    int id_viagem_comprar;
    boolean idCheckbox=true;
    
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

    public int getNumeroBilhetesComprar() {
        return numeroBilhetesComprar;
    }

    public void setNumeroBilhetesComprar(int numeroBilhetesComprar) {
        this.numeroBilhetesComprar = numeroBilhetesComprar;
    }

    public int getId_viagem_comprar() {
        return id_viagem_comprar;
    }

    public void setId_viagem_comprar(int id_viagem_comprar) {
        this.id_viagem_comprar = id_viagem_comprar;
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

    public boolean isIdCheckbox() {
        return idCheckbox;
    }

    public void setIdCheckbox(boolean idCheckbox) {
        this.idCheckbox = idCheckbox;
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
