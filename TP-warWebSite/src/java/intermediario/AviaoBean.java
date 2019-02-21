/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import controladores.AviaoController;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import tpdtos.AviaoDTO;
import tpdtos.CompanhiaDTO;
import tpdtos.ViagemDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "aviaoBean")
@ManagedBean
@SessionScoped
public class AviaoBean implements Serializable{
    private int id_aviao;
    private String nome_aviao;
    private int num_lugares;
    private String nome_companhia;
    private CompanhiaDTO companhia;
    /**
     * Creates a new instance of AviaoBean
     */
    @EJB
    intermedioLogicaLocal acessoLogica;
    
    @Inject
    AviaoController aviao;
    
    public AviaoBean() {
    }
    

    public int getId_aviao() {
        return id_aviao;
    }

    public String getNome_companhia() {
        return nome_companhia;
    }

    public void setNome_companhia(String nome_companhia) {
        this.nome_companhia = nome_companhia;
    }

    public CompanhiaDTO getCompanhia() {
        return companhia;
    }

    public void setCompanhia(CompanhiaDTO companhia) {
        this.companhia = companhia;
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

    public AviaoController getAviao() {
        return aviao;
    }

    public void setAviao(AviaoController aviao) {
        this.aviao = aviao;
    }
    
    public String insereAviao(){
        
        try{
            boolean estado_criacao_aviao=this.acessoLogica.getSingletonLogica().insertAviao(new AviaoDTO(nome_aviao, num_lugares),nome_companhia);
            if(estado_criacao_aviao==true){
                return "/index.xhtml?faces-redirect=true?";
            }
            return "/erro.xhtml?faces-redirect=true?";
        } catch(Exception e){
            System.out.println(""+e.getMessage());
            return null;
        }
               
    }
    
    //listar os avioes
    public List<AviaoDTO> listarAvioes(){
        List<AviaoDTO> lista_avioes=this.acessoLogica.getSingletonLogica().selectionaAviao();
        if(lista_avioes==null){
            return null;
        }
        return lista_avioes;
    }
    
    

}
