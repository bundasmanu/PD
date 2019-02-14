/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import tpdtos.ClienteDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "loginBean")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

    /**
     * Creates a new instance of LoginBean
     */
    
    private String pwd;
    private String msg;
    private String mail;
    
    @EJB
    intermedioLogicaLocal acessoLogica;
    
    public LoginBean() {
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String user) {
        this.mail = user;
    }
    
    public void resetValues(){
        this.mail="";
        this.pwd="";
    }
    
    public boolean validarUser(String email,String pass){
        
        return this.acessoLogica.getSingletonLogica().validaLogin(email, pass);
        
    }
    
    public String login() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext context = FacesContext.getCurrentInstance();

            boolean logged = this.validarUser(request.getParameter("form:email"), request.getParameter("form:password"));
            if (!logged) {
                context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Login inválido!"));
                this.resetValues();
                return null; //-->mesma página
            }

            context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sucesso", "Login validado!"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        this.resetValues();
        return "/vistas/login/confirmar.xhtml?faces-redirect=true?";
    }
    
    public String logout() throws IOException{
        FacesContext context= FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
       return "/vistas/login/login.xhtml?faces-redirect=true?";
    }

}
    


    
