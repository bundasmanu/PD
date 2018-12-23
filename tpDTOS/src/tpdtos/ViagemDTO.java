/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpdtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class ViagemDTO {
    
    private int id;
    int numero_lugares;
    Date hora_part;
    Date hora_cheg;
    AviaoDTO aviao;
    List<BilheteDTO> bilhetes;
    PartidaDTO part;
    DestinoDTO dest;
    
    public ViagemDTO(int number, Date part, Date cheg, String cidade_part, String cidade_dest, int id_aviao){
        this.numero_lugares=number;
        this.hora_part=part;
        this.hora_cheg=cheg;
        this.part=new PartidaDTO(cidade_part);
        this.dest=new DestinoDTO(cidade_dest);
        this.aviao=new AviaoDTO(id_aviao);
        this.bilhetes=new ArrayList<BilheteDTO>();
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

    public AviaoDTO getAviao() {
        return aviao;
    }

    public List<BilheteDTO> getBilhetes() {
        return bilhetes;
    }

    public PartidaDTO getPart() {
        return part;
    }

    public DestinoDTO getDest() {
        return dest;
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

    public void setAviao(AviaoDTO aviao) {
        this.aviao = aviao;
    }

    public void setBilhetes(List<BilheteDTO> bilhetes) {
        this.bilhetes = bilhetes;
    }

    public void setPart(PartidaDTO part) {
        this.part = part;
    }

    public void setDest(DestinoDTO dest) {
        this.dest = dest;
    }
    
    @Override
    public String toString(){
        
        String info_viagem="";
        
        info_viagem+=this.getNumero_lugares()+"\t"+this.getHora_part()+"\t"+this.getHora_cheg()+"\n";
        info_viagem+=this.getPart().toString()+"\n";
        info_viagem+=this.getHora_cheg().toString()+"\n";
        info_viagem+="Info Aviao:\n";
        info_viagem+=this.getAviao().toString()+"\n";
        info_viagem+="Info Bilhetes:\n";
        
        for(BilheteDTO x : this.bilhetes){
            info_viagem+=x.toString()+"\n";
        }
        
        return info_viagem;
    }
    
}
