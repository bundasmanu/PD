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
public class ClienteDTO {
    
    String nome;
    String email;
    List<BilheteDTO> bilhetes;
    List<BagagemDTO> bagagens;
    List<PontuacaoDTO> pontuacoes;
    
    public ClienteDTO(String nome,String email){
        this.nome=nome;
        this.email=email;
        this.bilhetes=new ArrayList<BilheteDTO>();
        this.bagagens=new ArrayList<BagagemDTO>();
        this.pontuacoes=new ArrayList<PontuacaoDTO>();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<BilheteDTO> getBilhetes() {
        return bilhetes;
    }

    public List<BagagemDTO> getBagagens() {
        return bagagens;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBilhetes(List<BilheteDTO> bilhetes) {
        this.bilhetes = bilhetes;
    }

    public void setBagagens(List<BagagemDTO> bagagens) {
        this.bagagens = bagagens;
    }

    public List<PontuacaoDTO> getPontuacoes() {
        return pontuacoes;
    }

    public void setPontuacoes(List<PontuacaoDTO> pontuacoes) {
        this.pontuacoes = pontuacoes;
    }
    
    @Override
    public String toString(){
        
        String info_cliente="";
        
        info_cliente+=this.getNome()+"\t"+this.getEmail()+"\n";
        info_cliente+="Bilhetes: \n";
        
        for(BilheteDTO x : this.bilhetes){
            info_cliente+=x.toString()+"\n";
        }
        
        info_cliente+="Bagagens:\n";
        for(BagagemDTO x : this.bagagens){
            info_cliente+=x.toString()+"\n";
        }
        
        info_cliente+="Pontuacoes: \n";
        for(PontuacaoDTO x : this.pontuacoes){
            info_cliente+=x.toString()+"\n";
        }
        
        return info_cliente;
    }
    
}
