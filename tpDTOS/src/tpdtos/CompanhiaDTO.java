/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpdtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class CompanhiaDTO {
    
    String nome;
    AgenciaDTO ag;
    List<AviaoDTO> avioes_companhia;
    List<PontuacaoDTO> pontuacoes;
    
    public CompanhiaDTO(String name, int id_agencia){
        this.nome=name;
        this.ag=new AgenciaDTO(id_agencia);
        this.avioes_companhia=new ArrayList<AviaoDTO>();
        this.pontuacoes=new ArrayList<PontuacaoDTO>();
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String name){
        this.nome=name;
    }
    
    public AgenciaDTO getAgencia(){
        return this.ag;
    }
    
    public void setAgencia(AgenciaDTO agdt){
        this.ag=agdt;
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
    
    @Override
    public String toString(){
        
        String info_companhia="";
        
        info_companhia+=this.getNome()+"\tTrabalha com a agencia\n"+this.ag.getNomeAgencia();
        info_companhia+="\nAvioes:\n";
        for(AviaoDTO x : this.avioes_companhia){
            info_companhia+=x.toString()+"\n";
        }
        info_companhia+="Pontuacoes: \n";
        for(PontuacaoDTO x : this.pontuacoes){
            info_companhia+=x.toString()+"\n";
        }
        
        return info_companhia;
    }
    
}
