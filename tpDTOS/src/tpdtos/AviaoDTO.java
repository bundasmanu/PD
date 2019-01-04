/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpdtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class AviaoDTO implements Serializable{
    
    int id;
    String nomeAviao;
    int num_lugares;
    //CompanhiaDTO companhiaAviao;
    List<ViagemDTO> viagensAviao;
    
    public AviaoDTO(String name, int numberLug){
        this.nomeAviao=name;
        this.num_lugares=numberLug;
        //this.companhiaAviao=comp;
        this.viagensAviao=new ArrayList<ViagemDTO>();
    }
    
    public AviaoDTO(int idd){
        this.id=idd;
    }

    
    public String getNomeAviao() {
        return nomeAviao;
    }

    public int getNum_lugares() {
        return num_lugares;
    }

    /*public CompanhiaDTO getCompanhiaAviao() {
        return companhiaAviao;
    }*/

    public List<ViagemDTO> getViagensAviao() {
        return viagensAviao;
    }

    public void setNomeAviao(String nomeAviao) {
        this.nomeAviao = nomeAviao;
    }

    public void setNum_lugares(int num_lugares) {
        this.num_lugares = num_lugares;
    }

    /*public void setCompanhiaAviao(CompanhiaDTO companhiaAviao) {
        this.companhiaAviao = companhiaAviao;
    }*/

    public void setViagensAviao(List<ViagemDTO> viagensAviao) {
        this.viagensAviao = viagensAviao;
    }
 
    @Override
    public String toString(){
        
        String info_aviao="";
        
        info_aviao+=this.getNomeAviao()+"\t"+this.getNum_lugares()+"\n";
        //info_aviao+="Pertenco a companhia: "+this.getCompanhiaAviao()+"\n";
        info_aviao+="Viagens a serem efetuadas:\n";
        
        for(ViagemDTO x : this.viagensAviao){
            info_aviao+=x.toString()+"\n";
        }
        
        return info_aviao;
    }
    
}
