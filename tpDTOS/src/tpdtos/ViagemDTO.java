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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gustavo
 */
@XmlRootElement
public class ViagemDTO implements Serializable{
    
    private int id;
    int hora_part;
    int hora_cheg;
    int preco;
    String estado_viagem;
    private int numeroVagas;
    List<BilheteDTO> bilhetes;
    List<BagagemDTO> bagagens;
    PartidaDTO part; /*NESTE CASO E IMPORTANTE A SUA PRESENCA*/
    DestinoDTO dest; /*NESTE CASO E IMPORTANTE A SUA PRESENCA*/
    
    public ViagemDTO(int part,int cheg){
        
        this.hora_part=part;
        this.hora_cheg=cheg;
        this.bilhetes=new ArrayList<BilheteDTO>();
        this.bagagens=new ArrayList<BagagemDTO>();
    }
    
    public ViagemDTO(int id){  /*USO POR CAUSA DOS BILHETES*/
        this.id=id;
    }
    
    public ViagemDTO(){
        
    }
    
    public int getHora_part() {
        return hora_part;
    }

    public int getHora_cheg() {
        return hora_cheg;
    }

    public List<BilheteDTO> getBilhetes() {
        return bilhetes;
    }


    public void setHora_part(int hora_part) {
        this.hora_part = hora_part;
    }

    public void setHora_cheg(int hora_cheg) {
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

    public PartidaDTO getPart() {
        return part;
    }

    public DestinoDTO getDest() {
        return dest;
    }

    public void setPart(PartidaDTO part) {
        this.part = part;
    }

    public void setDest(DestinoDTO dest) {
        this.dest = dest;
    }

    public int getPreco() {
        return preco;
    }

    public String getEstado_viagem() {
        return estado_viagem;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public void setEstado_viagem(String estado_viagem) {
        this.estado_viagem = estado_viagem;
    }

    public int getNumeroVagas() {
        return numeroVagas;
    }

    public void setNumeroVagas(int numeroVagas) {
        this.numeroVagas = numeroVagas;
    }
    
    @Override
    public String toString(){
        
        String info_viagem="";
        
        info_viagem+=this.getHora_part()+"\t"+this.getHora_cheg()+"\n";
        info_viagem+="Info Bilhetes:\n";
       
        for(BilheteDTO x : this.bilhetes){
            
            //info_viagem+=x.toString()+"\n";
            info_viagem+="Preco do bilhete:"+x.getPreco_bilhete()+"\t"+"Lugar do bilhete"+x.getLugar()+"\n";
            
        }
        
        info_viagem+="Info Bagagens:\n";
        for(BagagemDTO x : this.bagagens){
            info_viagem+=x.toString()+"\n";
        }
        
        return info_viagem;
    }
    
}
