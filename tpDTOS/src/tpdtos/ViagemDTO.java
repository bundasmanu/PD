/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpdtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class ViagemDTO implements Serializable{
    
    private int id;
    int numero_lugares;
    Date hora_part;
    Date hora_cheg;
    List<BilheteDTO> bilhetes;
    List<BagagemDTO> bagagens;
    
    public ViagemDTO(int number, Date part, Date cheg){
        this.numero_lugares=number;
        this.hora_part=part;
        this.hora_cheg=cheg;
        this.bilhetes=new ArrayList<BilheteDTO>();
        this.bagagens=new ArrayList<BagagemDTO>();
    }
    
    public ViagemDTO(int id){  /*USO POR CAUSA DOS BILHETES*/
        this.id=id;
    }
    
    public int getNumero_lugares() {
        return numero_lugares;
    }

    public Date getHora_part() {
        return hora_part;
    }

    public Date getHora_cheg() {
        return hora_cheg;
    }

    public List<BilheteDTO> getBilhetes() {
        return bilhetes;
    }

    public void setNumero_lugares(int numero_lugares) {
        this.numero_lugares = numero_lugares;
    }

    public void setHora_part(Date hora_part) {
        this.hora_part = hora_part;
    }

    public void setHora_cheg(Date hora_cheg) {
        this.hora_cheg = hora_cheg;
    }
    
    public void setBilhetes(List<BilheteDTO> bilhetes) {
        this.bilhetes = bilhetes;
    }

    public List<BagagemDTO> getBagagens() {
        return bagagens;
    }

    public void setBagagens(List<BagagemDTO> bagagens) {
        this.bagagens = bagagens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    @Override
    public String toString(){
        
        String info_viagem="";
        
        info_viagem+=this.getNumero_lugares()+"\t"+this.getHora_part()+"\t"+this.getHora_cheg()+"\n";
        info_viagem+=this.getHora_cheg().toString()+"\n";
        info_viagem+="Info Bilhetes:\n";
        
        for(BilheteDTO x : this.bilhetes){
            info_viagem+=x.toString()+"\n";
        }
        
        info_viagem+="Info Bagagens:\n";
        for(BagagemDTO x : this.bagagens){
            info_viagem+=x.toString()+"\n";
        }
        
        return info_viagem;
    }
    
}
