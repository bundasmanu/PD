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
@Named(value = "partidasBean")
@ManagedBean
@SessionScoped
public class PartidasBean implements Serializable{

    /**
     * Creates a new instance of PartidasBean
     */
    public PartidasBean() {
    }
    
    private int id_partida;
    private String cidade_partida;
    private float pont_media;

    public int getId_partida() {
        return id_partida;
    }

    public String getCidade_partida() {
        return cidade_partida;
    }

    public float getPont_media() {
        return pont_media;
    }

    public void setId_partida(int id_partida) {
        this.id_partida = id_partida;
    }

    public void setCidade_partida(String cidade_partida) {
        this.cidade_partida = cidade_partida;
    }

    public void setPont_media(float pont_media) {
        this.pont_media = pont_media;
    }
    
}
