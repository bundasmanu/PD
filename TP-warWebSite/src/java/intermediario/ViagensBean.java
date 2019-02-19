/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import controladores.ViagensController;
import dados.Aviao;
import dados.Destinos;
import dados.Partidas;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author gustavo
 */
@Named(value = "viagensBean")
@ManagedBean
@SessionScoped
public class ViagensBean implements Serializable{

    /**
     * Creates a new instance of ViagensBean
     */
    public ViagensBean() {
    }
    
    private int id_viagem;
    private int hora_chegada;
    private int hora_partida;
    private int preco;
    private String estado_viagem;
    private Aviao id_aviao;
    private Partidas id_partida;
    private Destinos id_destino;
    
    @Inject
    ViagensController viagens;
    
    @EJB
    intermedioLogicaLocal acessoLogica;

    public int getId_viagem() {
        return id_viagem;
    }

    public int getHora_chegada() {
        return hora_chegada;
    }

    public int getHora_partida() {
        return hora_partida;
    }

    public int getPreco() {
        return preco;
    }

    public String getEstado_viagem() {
        return estado_viagem;
    }

    public void setId_viagem(int id_viagem) {
        this.id_viagem = id_viagem;
    }

    public void setHora_chegada(int hora_chegada) {
        this.hora_chegada = hora_chegada;
    }

    public void setHora_partida(int hora_partida) {
        this.hora_partida = hora_partida;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public void setEstado_viagem(String estado_viagem) {
        this.estado_viagem = estado_viagem;
    }

    public ViagensController getViagens() {
        return viagens;
    }

    public void setViagens(ViagensController viagens) {
        this.viagens = viagens;
    }

    public Aviao getId_aviao() {
        return id_aviao;
    }

    public Partidas getId_partida() {
        return id_partida;
    }

    public Destinos getId_destino() {
        return id_destino;
    }

    public void setId_aviao(Aviao id_aviao) {
        this.id_aviao = id_aviao;
    }

    public void setId_partida(Partidas id_partida) {
        this.id_partida = id_partida;
    }

    public void setId_destino(Destinos id_destino) {
        this.id_destino = id_destino;
    }
    
    public String criaViagem(){
        
        try{
            
            boolean retorno=this.acessoLogica.getSingletonLogica().insereViagem(hora_partida, hora_chegada, id_aviao.getIdAviao(), id_partida.getIdPartida(), id_destino.getIdDestino(), preco);
            if(retorno==true){
                return "/index.xhtml?faces-redirect=true?";
            }
            
            return null;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
}
