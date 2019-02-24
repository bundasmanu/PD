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
    private ClienteDTO cliente;
    private int id;
    private boolean alteraContaCli=false;
    private boolean alteraPassCli=false;
    private int novo_valor_conta;
    private String novaPassword;
    private int controla_render_c=0;
    private int controla_render_p=0;
    
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
    
    public void resetValues2(){
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

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public boolean isAlteraContaCli() {
        return alteraContaCli;
    }

    public void setAlteraContaCli(boolean alteraContaCli) {
        this.alteraContaCli = alteraContaCli;
        this.controla_render_c++;
    }

    public int getNovo_valor_conta() {
        return novo_valor_conta;
    }

    public void setNovo_valor_conta(int novo_valor_conta) {
        this.novo_valor_conta = novo_valor_conta;
        this.controla_render_c=2;
    }

    public boolean isAlteraPassCli() {
        return alteraPassCli;
    }

    public void setAlteraPassCli(boolean alteraPassCli) {
        this.alteraPassCli = alteraPassCli;
    }

    public String getNovaPassword() {
        return novaPassword;
    }

    public void setNovaPassword(String novaPassword) {
        this.novaPassword = novaPassword;
        this.controla_render_p=2;
    }

    public int getControla_render_c() {
        return controla_render_c;
    }

    public int getControla_render_p() {
        return controla_render_p;
    }

    public void setControla_render_c(int controla_render_c) {
        this.controla_render_c = controla_render_c;
    }

    public void setControla_render_p(int controla_render_p) {
        this.controla_render_p = controla_render_p;
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
                this.cliente=this.acessoLogica.getSingletonLogica().seleccionaCliente(this.getMail());
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
        
        this.resetValues2();
        return "/vistas/cliente/infoCliente.xhtml?faces-redirect=true?";
    }
    
    public String logout() throws IOException{
        FacesContext context= FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
       return "/vistas/login/login.xhtml?faces-redirect=true?";
    }

    public String alteraConta(){
        
        try{
            
            boolean retorno_altera_conta=this.acessoLogica.getSingletonLogica().atualizaContaCliente(this.getMail(), this.getNovo_valor_conta());
            if(retorno_altera_conta==true){
                this.verificaConta();
                return "/camposAtualizados.xhtml?faces-redirect=true?";
            }
            
            return "/erro.xhtml?faces-redirect=true?";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "/erro.xhtml?faces-redirect=true?";
        }
        
    }
    
    public void verificaConta(){
        
        try{
            
            ClienteDTO ret_cli=this.acessoLogica.getSingletonLogica().seleccionaCliente(this.getMail());
            if(ret_cli!=null){
                this.cliente.setConta(ret_cli.getConta());
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public String alteraPassword(){
        
        try{
            
            boolean retorno_altera_password=this.acessoLogica.getSingletonLogica().atualizaCliente(this.getMail(), this.getNovaPassword());
            if(retorno_altera_password==true){
                return "/camposAtualizados.xhtml?faces-redirect=true?";
            }
            
            return "/erro.xhtml?faces-redirect=true?";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "/erro.xhtml?faces-redirect=true?";
        }
        
    }
    
}  
