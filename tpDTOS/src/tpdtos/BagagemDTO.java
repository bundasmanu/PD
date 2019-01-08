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
public class BagagemDTO implements Serializable{
    
    int peso_bagagem;
    ViagemDTO viagem;
    
    public BagagemDTO(int peso){
        this.peso_bagagem=peso;
    }

    public int getPeso_bagagem() {
        return peso_bagagem;
    }

    public void setPeso_bagagem(int peso_bagagem) {
        this.peso_bagagem = peso_bagagem;
    }

    public ViagemDTO getViagem() {
        return viagem;
    }

    public void setViagem(ViagemDTO viagem) {
        this.viagem = viagem;
    }
    
    @Override
    public String toString(){
        
        String info_bagagem="";
        info_bagagem+=this.getPeso_bagagem()+"\n";
        info_bagagem+=this.viagem.toString()+"\n";
        
        return info_bagagem;
    }
    
}
