/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author gustavo
 */
@Named(value = "aviaoBean")
@ManagedBean
@SessionScoped
public class AviaoBean implements Serializable{

    /**
     * Creates a new instance of AviaoBean
     */
    public AviaoBean() {
    }
    
    private int id_aviao;
    private String nome_aviao;
    private int num_lugares;

    public int getId_aviao() {
        return id_aviao;
    }

    public String getNome_aviao() {
        return nome_aviao;
    }

    public int getNum_lugares() {
        return num_lugares;
    }

    public void setId_aviao(int id_aviao) {
        this.id_aviao = id_aviao;
    }

    public void setNome_aviao(String nome_aviao) {
        this.nome_aviao = nome_aviao;
    }

    public void setNum_lugares(int num_lugares) {
        this.num_lugares = num_lugares;
    }
    
}
