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
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import tpdtos.BagagemDTO;
import tpdtos.ClienteDTO;
import tpdtos.ViagemDTO;

/**
 *
 * @author pedro
 */
@Named(value = "bagagemBean")
@ManagedBean
@SessionScoped
public class BagabemBean implements Serializable {

    private int id_bagagem;
    private int peso_bagagem;
    private int idViagem;
    private int id_cliente;

    @EJB
    intermedioLogicaLocal acessoLogica;

//     @EJB
//     ClienteBean cliente;
    public BagabemBean() {

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public int getId_bagagem() {
        return id_bagagem;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    
    public void setId_bagagem(int id_bagagem) {
        this.id_bagagem = id_bagagem;
    }

    public int getPeso_bagagem() {
        return peso_bagagem;
    }

    public void setPeso_bagagem(int peso_bagagem) {
        this.peso_bagagem = peso_bagagem;
    }

    public int getViagem() {
        return idViagem;
    }

    public void setViagem(int viagem) {
        this.idViagem = viagem;
    }

    public List<BagagemDTO> selecionaAllBagagens() {
        /*DEPOIS DE FAZER LOGIN, SEI QUAL É O UTILIZADOR, E ENCONTRA-SE DEFINIDO NA CLASSE SESSIONCONTEXT*/
        String cli = (String) SessionContext.getInstance().getAttribute("cli");
        //this.cliente.setEmail_cliente(cli);

        List<BagagemDTO> bagagens = this.acessoLogica.getSingletonLogica().SelecionaBagagens(cli);
        if (bagagens == null) {
            return null;
        }

        return bagagens;
    }

    public String insereBagagem() {
        try {
            
            if(this.peso_bagagem<=0){
                return "/erro.xhtml?faces-redirect=true?";
            }
            
            boolean estado_criacao_bagagem = this.acessoLogica.getSingletonLogica().insertBagagem(peso_bagagem, idViagem, id_cliente);
            if (estado_criacao_bagagem == true) {
                return "/vistas/bagagens/infoTodasBagagens.xhtml?faces-redirect=true?";
            }
            return "/erro.xhtml?faces-redirect=true?";
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return "/erro.xhtml?faces-redirect=true?";
        }
    }
    
    public List<ClienteDTO> obtemListaClientes(){
        try{
            List<ClienteDTO> lista_clientes=this.acessoLogica.getSingletonLogica().seleccionaAllClientes();
            if(lista_clientes==null){
                return null;
            }
            return lista_clientes;
        }catch(Exception e){
            System.out.println(""+e.getMessage());
            return null;
        }
    }
    
    public List<ViagemDTO> obtemListaViagens(){
        try{
            List<ViagemDTO> lista_viagens= this.acessoLogica.getSingletonLogica().seleccionaAllViagens();
            if(lista_viagens==null){
                return null;
            }
            return lista_viagens;
        } catch(Exception e){
            System.out.println(""+e.getMessage());
            return null;
        }
    }
    
    public List<BagagemDTO> obtemListaTodasBagagens(){
        try{
            List<BagagemDTO> lista_todas_bagagens= this.acessoLogica.getSingletonLogica().selecionaAllBagagens();
            if(lista_todas_bagagens==null){
                return null;
            }
            return lista_todas_bagagens;
        } catch(Exception e){
            System.out.println(""+e.getMessage());
            return null;
        }
    }
    
    
    


}
