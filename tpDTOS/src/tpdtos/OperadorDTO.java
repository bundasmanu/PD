/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpdtos;

/**
 *
 * @author gustavo
 */
public class OperadorDTO {
    
    String nome;
    String email;
    
    public OperadorDTO(String name, String mail){
        this.nome=name;
        this.email=mail;
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
    
    @Override
    public String toString(){
        
        String info_op="";
        
        info_op+=this.getNome()+this.getMail()+"\n";
        
        return info_op;
    }
    
}
