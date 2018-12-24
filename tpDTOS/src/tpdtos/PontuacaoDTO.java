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
public class PontuacaoDTO {
    
    /*QUANDO FIZERMOS A CENA DAS PONTUACOES*/
    /*TEMOS DE PERGUNTAR PRIMEIRO--> SE QUEREM PONTUAR UM DESTINO, OU UMA PARTIDA OU UMA COMPNAHIA,
    PARA ASSIM DEPOIS INSERIRMOS NA COLECCÇAO CORRETA A PONTUACAO*/
    
    int valor;
    ClienteDTO cli;
    List<CompanhiaDTO> companhias;
    List<DestinoDTO> destinos;
    List<PartidaDTO> partidas;
    
    public PontuacaoDTO(int val,String name,String email){
        this.valor=val;
        this.cli=new ClienteDTO(name,email);
        this.companhias=new ArrayList<CompanhiaDTO>();
        this.destinos=new ArrayList<DestinoDTO>();
        this.partidas=new ArrayList<PartidaDTO>();
    }

    public int getValor() {
        return valor;
    }

    public List<CompanhiaDTO> getCompanhias() {
        return companhias;
    }

    public List<DestinoDTO> getDestinos() {
        return destinos;
    }

    public List<PartidaDTO> getPartidas() {
        return partidas;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setCompanhias(List<CompanhiaDTO> companhias) {
        this.companhias = companhias;
    }

    public void setDestinos(List<DestinoDTO> destinos) {
        this.destinos = destinos;
    }

    public void setPartidas(List<PartidaDTO> partidas) {
        this.partidas = partidas;
    }

    public ClienteDTO getCli() {
        return cli;
    }

    public void setCli(ClienteDTO cli) {
        this.cli = cli;
    }

    public String toString(){
        
        String info_pontuacao="";
        
        info_pontuacao+=this.getValor()+"\n";
        info_pontuacao+="Companhias :\n";
        for(CompanhiaDTO x : this.companhias){
            info_pontuacao+=x.toString()+"\n";
        }
        
        info_pontuacao+="Destinos: \n";
        for(DestinoDTO x : this.destinos){
            info_pontuacao+=x.toString()+"\n";
        }
        
        info_pontuacao+="Partidas: \n";
        for(PartidaDTO x : this.partidas){
            info_pontuacao+=x.toString()+"\n";
        }
        
        return info_pontuacao;
    }
    
}
