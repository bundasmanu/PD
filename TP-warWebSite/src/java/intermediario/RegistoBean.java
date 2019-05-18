/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import tpdtos.ClienteDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "registoBean")
@ManagedBean
@SessionScoped
public class RegistoBean implements Serializable{

    /**
     * Creates a new instance of RegistoBean
     */
    
    @EJB
    intermedioLogicaLocal acessoLogica;
    
    public RegistoBean() {
    }
    
    private String email;
    private String nome;
    private String pass;
    private String passConfirmar;

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getPass() {
        return pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPassConfirmar() {
        return passConfirmar;
    }

    public void setPassConfirmar(String passConfirmar) {
        this.passConfirmar = passConfirmar;
    }
    
        
    public void resetValues(){
        this.email="";
        this.nome="";
        this.pass="";
        this.passConfirmar="";
    }

    public String aplicaRegistoCli(){
        
        /*MEDIANTE OS DADOS DO UTILIZADOR, EFETUAR O SEU REGISTO*/
        
        try{
            if (this.getPass().equals(this.getPassConfirmar()) == true) {
                ClienteDTO c = new ClienteDTO(this.getNome(), this.getEmail());
                boolean retorno_insere_cliente = this.acessoLogica.getSingletonLogica().insereCliente(c, this.getPass());

                if (retorno_insere_cliente == true) {
                    resetValues();
                    return "/index.xhtml?faces-redirect=true?"; //-->DEPOIS DEFINIR NOVA PAGINA APOS REGISTO EFETUADO COM SUCESSO
                } else {
                    resetValues();
                    return "/erro.xhtml?faces-redirect=true?";
                }
            }
            resetValues();
            return null;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "/erro.xhtml?faces-redirect=true?";
        }
        
    }
    
}
