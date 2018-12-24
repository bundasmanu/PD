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
public class BagagemDTO {
    
    int peso_bagagem;
    ClienteDTO cli;
    ViagemDTO viagem;
    
    public BagagemDTO(int peso, String nome_cli, String email, int idViagem){
        this.peso_bagagem=peso;
        this.cli=new ClienteDTO(nome_cli,email);
        this.viagem=new ViagemDTO(idViagem);
    }

    public int getPeso_bagagem() {
        return peso_bagagem;
    }

    public ClienteDTO getCli() {
        return cli;
    }

    public void setPeso_bagagem(int peso_bagagem) {
        this.peso_bagagem = peso_bagagem;
    }

    public void setCli(ClienteDTO cli) {
        this.cli = cli;
    }

    public ViagemDTO getViagem() {
        return viagem;
    }

    public void setViagem(ViagemDTO viagem) {
        this.viagem = viagem;
    }
    
    @Override
    public String toString(){
        
        String info_bagagem="";
        info_bagagem+=this.getPeso_bagagem()+"\n";
        info_bagagem+="Info do Cliente:\n";
        info_bagagem+=this.getCli().toString()+"\n";
        
        return info_bagagem;
    }
    
}
