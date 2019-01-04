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
public class BilheteDTO implements Serializable{
    
    int preco_bilhete;
    ClienteDTO cli;
    ViagemDTO viagem;
    
    public BilheteDTO(int preco){
        this.preco_bilhete=preco;
    }

    public int getPreco_bilhete() {
        return preco_bilhete;
    }

    public ViagemDTO getViagem() {
        return viagem;
    }

    public void setPreco_bilhete(int preco_bilhete) {
        this.preco_bilhete = preco_bilhete;
    }

    public void setViagem(ViagemDTO viagem) {
        this.viagem = viagem;
    }

    public ClienteDTO getCli() {
        return cli;
    }

    public void setCli(ClienteDTO cli) {
        this.cli = cli;
    }
    
    @Override
    public String toString(){
        
        String info_bilhete="";
        
        info_bilhete+=this.getPreco_bilhete()+"\n";
        info_bilhete+="Viagem : "+this.getViagem().toString()+"\n";
        
        return info_bilhete;
    }
    
    
}
