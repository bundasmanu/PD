/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpdtos;

/**
 *
 * @author gustavo
 */
public class BilheteDTO {
    
    int preco_bilhete;
    ClienteDTO cli;
    ViagemDTO viagem;
    
    public BilheteDTO(int preco, String nome, String email, int id_viagem){
        this.preco_bilhete=preco;
        this.cli=new ClienteDTO(nome,email);
        this.viagem=new ViagemDTO(id_viagem);
    }

    public int getPreco_bilhete() {
        return preco_bilhete;
    }

    public ClienteDTO getCli() {
        return cli;
    }

    public ViagemDTO getViagem() {
        return viagem;
    }

    public void setPreco_bilhete(int preco_bilhete) {
        this.preco_bilhete = preco_bilhete;
    }

    public void setCli(ClienteDTO cli) {
        this.cli = cli;
    }

    public void setViagem(ViagemDTO viagem) {
        this.viagem = viagem;
    }
    
    @Override
    public String toString(){
        
        String info_bilhete="";
        
        info_bilhete+=this.getPreco_bilhete()+"\n";
        info_bilhete+="Cliente : "+this.getCli().toString()+"\n";
        info_bilhete+="Viagem : "+this.getViagem().toString()+"\n";
        
        return info_bilhete;
    }
    
    
}
