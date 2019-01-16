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
public class PartidaDTO implements Serializable{
    
    String cidade;
    float pontuacao_media;
    List<ViagemDTO> viagens;
    List<PontuacaoDTO> pontuacoes;
    
    public PartidaDTO(String city){ /*INSERCAO*/
        this.cidade=city;
        this.pontuacao_media=0;
        this.viagens=new ArrayList<ViagemDTO>();
        this.pontuacoes=new ArrayList<PontuacaoDTO>();
    }

    public PartidaDTO(String city, float pont_med){ /*SELECT*/
        this.cidade=city;
        this.pontuacao_media=pont_med;
        this.viagens=new ArrayList<ViagemDTO>();
        this.pontuacoes=new ArrayList<PontuacaoDTO>();
    }
    
    public String getCidade() {
        return cidade;
    }

    public List<ViagemDTO> getViagens() {
        return viagens;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setViagens(List<ViagemDTO> viagens) {
        this.viagens = viagens;
    }

    public float getPontuacao_media() {
        return pontuacao_media;
    }

    public void setPontuacao_media(float pontuacao_media) {
        this.pontuacao_media = pontuacao_media;
    }

    public List<PontuacaoDTO> getPontuacoes() {
        return pontuacoes;
    }

    public void setPontuacoes(List<PontuacaoDTO> pontuacoes) {
        this.pontuacoes = pontuacoes;
    }
    
    @Override
    public String toString(){
        
        String info_partida="";
        
        info_partida+=this.getCidade()+"\n";
        info_partida+=this.getPontuacao_media()+"\n";
        
        info_partida+="Viagens: \n";
        for(ViagemDTO x : this.viagens){
            info_partida+=x.toString()+"\n";
        }
        info_partida+="Pontuacoes: \n";
        for(PontuacaoDTO x : this.pontuacoes){
            info_partida+=x.getValor()+"\n";
        }
        
        return info_partida;
    }
    
}
