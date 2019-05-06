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
import tpdtos.ClienteDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "clienteBean")
@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable{
    
    /**
     * Creates a new instance of ClienteBean
     */
    public ClienteBean() {
    }
    
    private int id_cliente;
    private String nome_cliente;
    private String pass_cli;
    private String email_cliente;
    private int conta;

    @EJB
    intermedioLogicaLocal acessoLogica;
    
    public int getId_cliente() {
        return id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public String getPass_cli() {
        return pass_cli;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public int getConta() {
        return conta;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public void setPass_cli(String pass_cli) {
        this.pass_cli = pass_cli;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }
    
    public int ObtemIdCliente(){
        int id_cliente=(int)SessionContext.getInstance().getAttribute("id");
        return id_cliente;
    }
    
    
    public ClienteDTO obtemInfoCliente(){
        
        /*DEPOIS DE FAZER LOGIN, SEI QUAL É O UTILIZADOR, E ENCONTRA-SE DEFINIDO NA CLASSE SESSIONCONTEXT*/
        String cli= (String)SessionContext.getInstance().getAttribute("cli");
        this.setEmail_cliente(cli);
        
        /*BUSCAR DADOS RELATIVOS */
        ClienteDTO cli_ret=this.acessoLogica.getSingletonLogica().seleccionaCliente(this.getEmail_cliente());
        
        if(cli_ret==null){
            return null;
        }
        
        return cli_ret;
        
    }

    
}
