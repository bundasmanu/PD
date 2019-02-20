/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import controladores.AviaoController;
import controladores.ClienteController;
import dados.Cliente;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import tpdtos.ClienteDTO;
import tpdtos.OperadorDTO;

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
    private int id;
    
    @EJB
    intermedioLogicaLocal acessoLogica;
    
    @Inject
    ClienteController c;//--> Injeccao de um ManagedBean dentro de outro ManagedBean
    
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void resetValues(){
        this.mail="";
        this.pwd="";
    }
    
    /*VERIFICA SE É CLIENTE, A PESSOA QUE ESTA A TENTAR EFETUAR*/
    public boolean validarUser(String email,String pass){
        
        boolean retorno=this.acessoLogica.getSingletonLogica().validaLogin(email, pass);
        if(retorno==true){
            ClienteDTO cli=this.acessoLogica.getSingletonLogica().seleccionaCliente(email);
            if(cli!=null){
                this.setId(cli.getId());
                 SessionContext.getInstance().setAttribute("id", this.getId());
                return true;
            }
        }
        
        return false;
    }
    
    /*VERIFICA SE É OPERADOR, A PESSOA QUE ESTA A TENTAR EFETUAR*/
    public boolean validarUser2(String email, String pass){
        
        return this.acessoLogica.getSingletonLogica().encontrouOperador(new OperadorDTO(email, pass));
        
    }
    
    public String myNameLogin(){
        return (String)SessionContext.getInstance().getAttribute("cli");
    }
    
//    public String logout(){
//        SessionContext.getInstance().encerrarSessao();
//        return "/index.xhtml?faces-redirect=true?";
//    }
    
    public Cliente buscaCli(){
        return this.c.getCliente(1); //-->PORQUE NAO FUNCIONA
    }
    
    public String login() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext context = FacesContext.getCurrentInstance();

            boolean logged_cliente = this.validarUser(this.getMail(),this.getPwd());
            boolean logged_operador=this.validarUser2(this.getMail(), this.getPwd());
            if (logged_cliente==false && logged_operador==false) {
                context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Login inválido!"));
                this.resetValues();
                return null; //-->mesma página 
            }
            //garante que é um cliente e não um operador
            else if(logged_cliente==true && logged_operador==false){
            
                context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sucesso", "Login validado!"));
                SessionContext.getInstance().setAttribute("cli", this.mail);
                //associar role á sessão
                SessionContext.getInstance().setAttribute("Role", "Cliente");
            }
            //garante que é um operador e não é um cliente
            else if(logged_cliente==false && logged_operador==true){
             
                context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sucesso", "Login validado!"));
                SessionContext.getInstance().setAttribute("operador", this.mail);
                //associar role á sessão
                SessionContext.getInstance().setAttribute("cli", this.mail);
                //COMENTEI PQ Um operador diferente de cliente
                SessionContext.getInstance().setAttribute("Role", "Operador");
                
            }
            //este else é no contexto de o utilizador estar como operador,mas não como cliente q é uma condicao impossivel
//            else{
//                throw new Exception("O utilizador está registado na tabela operadores mas sem registo de user.Contacte o administrador de sistema");
//            }
            //context.getExternalContext().getSessionMap().put("cli", this.mail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        this.resetValues();
        return "/vistas/cliente/infoCliente.xhtml?faces-redirect=true?";
    }
    
    public String logout() throws IOException{
        FacesContext context= FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
       return "/vistas/login/login.xhtml?faces-redirect=true?";
    }

}
    


    
