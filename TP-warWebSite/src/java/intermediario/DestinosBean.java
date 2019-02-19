/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import tpdtos.DestinoDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "destinosBean")
@ManagedBean
@SessionScoped
@DeclareRoles({"Operador"})
public class DestinosBean implements Serializable{

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
    
    @RolesAllowed("Operador")
    public boolean insereDestino(String Cidade_Destino,float PontuacaoMedia){
        return this.acessoLogica.getSingletonLogica().insereDestino(new DestinoDTO(cidade_destino,PontuacaoMedia));
    }
    
    @RolesAllowed("Operador")
    public void RedirectionaCriarDestino() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("http://192.168.56.175:8080/TP-warWebSite/faces/vistas/destinos/Create.xhtml");
    }
    
}
