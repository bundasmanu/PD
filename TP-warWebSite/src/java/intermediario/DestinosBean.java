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
@Named(value = "destinosBean")
@ManagedBean
@SessionScoped
public class DestinosBean implements Serializable{

    /**
     * Creates a new instance of DestinosBean
     */
    public DestinosBean() {
    }
    
    private int id_destino;
    private String cidade_destino;
    private float pontuacao_media;

    public int getId_destino() {
        return id_destino;
    }

    public String getCidade_destino() {
        return cidade_destino;
    }

    public float getPontuacao_media() {
        return pontuacao_media;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public void setCidade_destino(String cidade_destino) {
        this.cidade_destino = cidade_destino;
    }

    public void setPontuacao_media(float pontuacao_media) {
        this.pontuacao_media = pontuacao_media;
    }
    
}
