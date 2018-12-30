/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpdtos;

import java.io.Serializable;

/**
 *
 * @author gustavo
 */
public class OperadorDTO implements Serializable{
    
    String nome;
    String email;
    private String pass;
    
    public OperadorDTO(String name, String mail,String pass){ /*INSERT OPERADOR*/
        this.nome=name;
        this.email=mail;
        this.pass=pass;
    }
    
    public OperadorDTO(String email, String pass){ /*SELECT OPERADOR*/
        this.email=email;
        this.pass=pass;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getMail(){
        return this.email;
    }
    
    public void setNome(String name){
        this.nome=name;
    }
    
    public void setMail(String mail){
        this.email=mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    @Override
    public String toString(){
        
        String info_op="";
        try{

            info_op+=this.getNome()+"\t+"+this.getMail()+"\n";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "";
        }
        return info_op;
    }
    
}
