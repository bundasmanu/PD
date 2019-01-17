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
public class CompanhiaDTO implements Serializable{
    
    String nome;
    float pontuacao_media;
    List<AviaoDTO> avioes_companhia;
    List<PontuacaoDTO> pontuacoes;
    
    public CompanhiaDTO(String name){ /*INSERT*/
        this.nome=name;
        this.pontuacao_media=0;
        this.avioes_companhia=new ArrayList<AviaoDTO>();
        this.pontuacoes=new ArrayList<PontuacaoDTO>();
    }
    
    public CompanhiaDTO(String name, float pont){ /*RETORNO*/
        this.nome=name;
        this.pontuacao_media=pont;
        this.avioes_companhia=new ArrayList<AviaoDTO>();
        this.pontuacoes=new ArrayList<PontuacaoDTO>();
    }
    
    public CompanhiaDTO(int id_agencia){
        
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String name){
        this.nome=name;
    }
    
    public List<AviaoDTO> getAvioes(){
        return this.avioes_companhia;
    }
    
    public void setListAvioes(List<AviaoDTO> av){
        this.avioes_companhia=av;
    }

    public void setPontuacoes(List<PontuacaoDTO> pontuacoes) {
        this.pontuacoes = pontuacoes;
    }

    public List<PontuacaoDTO> getPontuacoes() {
        return pontuacoes;
    }

    public float getPontuacao_media() {
        return pontuacao_media;
    }

    public void setPontuacao_media(float pontuacao_media) {
        this.pontuacao_media = pontuacao_media;
    }
    
    @Override
    public String toString(){
        
        String info_companhia="Nome: "+this.getNome();

        info_companhia+="\nAvioes:\n";
        for(AviaoDTO x : this.avioes_companhia){
            //info_companhia+=x.toString()+"\n";
            info_companhia+=x.getNomeAviao()+"\t"+x.getNum_lugares()+"\n";
        }
        info_companhia+="Pontuacoes: \n";
        for(PontuacaoDTO x : this.pontuacoes){
            //info_companhia+=x.toString()+"\n";
            info_companhia+="Valor da pontuacao:"+ x.getValor();
        }
        
        return info_companhia;
    }
    
}
