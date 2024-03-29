/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import controladores.DestinosController;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import tpdtos.DestinoDTO;
import javax.inject.Inject;
import tpdtos.PontuacaoDTO;
import tpdtos.ViagemDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "destinosBean")
@ManagedBean
@SessionScoped
@DeclareRoles({"Operador"})
public class DestinosBean implements Serializable {

    /**
     * Creates a new instance of DestinosBean
     */
    @EJB
    intermedioLogicaLocal acessoLogica;

    public DestinosBean() {
    }

    private int id_destino;
    private String cidade_destino;
    private float pontuacao_media;

    @Inject
    DestinosController destinos;

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

    public DestinosController getDestinos() {
        return destinos;
    }

    public void setDestinos(DestinosController destinos) {
        this.destinos = destinos;
    }

    public String criaDestino() {
        try {
            //if (this.pontuacao_media >= 0.0 && this.pontuacao_media <= 10.0) {
                boolean retorno = this.acessoLogica.getSingletonLogica().insereDestino(new DestinoDTO(cidade_destino));
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
