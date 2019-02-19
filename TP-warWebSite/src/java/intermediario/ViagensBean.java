/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

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
    
}
