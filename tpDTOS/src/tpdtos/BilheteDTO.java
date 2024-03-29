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
public class BilheteDTO implements Serializable, Comparable<BilheteDTO>{
    
    int preco_bilhete;
    int lugar;
    ClienteDTO cli;
    ViagemDTO viagem;
    
    public BilheteDTO(){
        
    }
    
    public BilheteDTO(int preco){
        this.preco_bilhete=preco;
    }

    public int getPreco_bilhete() {
        return preco_bilhete;
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
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
        //info_bilhete+="Cliente : "+this.getCli().getNome()+"\n";
        info_bilhete+= this.getLugar()+"\n";
        //info_bilhete+="Viagem : "+this.getViagem().toString()+"\n";
        
        return info_bilhete;
    }
    
    @Override
    public int compareTo(BilheteDTO b){
        
        if(this.getPreco_bilhete()>b.getPreco_bilhete()){
            return 1;
        }
        else if(this.getPreco_bilhete()<b.getPreco_bilhete()){
            return -1;
        }
        
        /*SE FOREM IGUAIS RETORNA 0*/
        return 0;
    }
    
}
