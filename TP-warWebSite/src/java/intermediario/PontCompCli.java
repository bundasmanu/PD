/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import tpdtos.CompanhiaDTO;
import tpdtos.PontuacaoDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "pontCompCli")
@ManagedBean
@SessionScoped
public class PontCompCli implements Serializable {

    /**
     * Creates a new instance of PontCompCli
     */
    
    private String nome_companhia;
    private int valor_atribuido;
    private List<CompanhiaDTO> comp;
    
    
    public PontCompCli() {
    }

    @EJB
    intermedioLogicaLocal acessoLogica;

    public intermedioLogicaLocal getAcessoLogica() {
        return acessoLogica;
    }

    public void setAcessoLogica(intermedioLogicaLocal acessoLogica) {
        this.acessoLogica = acessoLogica;
    }

    public String getNome_companhia() {
        return nome_companhia;
    }

    public void setNome_companhia(String nome_companhia) {
        this.nome_companhia = nome_companhia;
    }

    public int getValor_atribuido() {
        return valor_atribuido;
    }

    public void setValor_atribuido(int valor_atribuido) {
        this.valor_atribuido = valor_atribuido;
    }

    public List<CompanhiaDTO> getComp() {
        return comp;
    }

    public void setComp(List<CompanhiaDTO> comp) {
        this.comp = comp;
    }

    public List<CompanhiaDTO> getCompanhiaPossoDarPontuacao() {

        try {

            return this.getAcessoLogica().getSingletonLogica().selectAllCompaniesFromClient((int) SessionContext.getInstance().getAttribute("id"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
    
    //seleciona todas as pont atribuidas a uma comp pelo cliente de uma sessao
    public List<PontuacaoDTO> selecionaTodasPontComp() {
        try {
            int id_cli = (int) SessionContext.getInstance().getAttribute("id");
            List<PontuacaoDTO> lista_pont_comp = this.acessoLogica.getSingletonLogica().seleccionaAllClientPontComp(id_cli);
            if (lista_pont_comp == null) {
                return null;
            }
            return lista_pont_comp;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return null;
        }
    }
    
     
    public List<CompanhiaDTO> obtemListaCompanhias(){
        try{
           this.comp= this.acessoLogica.getSingletonLogica().selectAll();
           if(comp==null){
               return null;
           }
           return comp;
        }catch(Exception e){
            System.out.println(""+e.getMessage());
            return null;
        }
    }
    
   
    
   
           

}

