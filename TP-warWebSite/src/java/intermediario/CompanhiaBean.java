/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import tpdtos.CompanhiaDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "companhiaBean")
@ManagedBean
@SessionScoped
public class CompanhiaBean implements Serializable {

    /**
     * Creates a new instance of CompanhiaBean
     */
    @EJB
    intermedioLogicaLocal acessoLogica;

    public CompanhiaBean() {
    }

    private int id_companhia;
    private String nome_companhia;
    private float pontuacao_media;

    public int getId_companhia() {
        return id_companhia;
    }

    public String getNome_companhia() {
        return nome_companhia;
    }

    public float getPontuacao_media() {
        return pontuacao_media;
    }

    public void setId_companhia(int id_companhia) {
        this.id_companhia = id_companhia;
    }

    public void setNome_companhia(String nome_companhia) {
        this.nome_companhia = nome_companhia;
    }

    public void setPontuacao_media(float pontuacao_media) {
        this.pontuacao_media = pontuacao_media;
    }

    public String criaCompanhia() {
        try {
            //if (this.pontuacao_media >= 0.0 && this.pontuacao_media <= 10.0) {
                boolean retorno = this.acessoLogica.getSingletonLogica().insertCompanhia(new CompanhiaDTO(nome_companhia));
                if (retorno == true) {
                    return "/index.xhtml?faces-redirect=true?";
                }
            //}
            return "/erro.xhtml?faces-redirect=true?";
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return null;
        }
    }
  
}


