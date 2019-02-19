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
import tpdtos.BilheteDTO;

/**
 *
 * @author gustavo
 */
@Named(value = "bilheteBean")
@ManagedBean
@SessionScoped
public class BilheteBean implements Serializable {

    /**
     * Creates a new instance of BilheteBean
     */
    @EJB
    intermedioLogicaLocal acessoLogica;

    @Inject
    ClienteBean cliente;

    public BilheteBean() {
    }

    private int id;
    private int preco_bilhete;
    private int lugar;
    

    public int getId() {
        return id;
    }

    public int getPreco_bilhete() {
        return preco_bilhete;
    }

    public int getLugar() {
        return lugar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPreco_bilhete(int preco_bilhete) {
        this.preco_bilhete = preco_bilhete;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public List<BilheteDTO> selecionaTodosBilhetesDeUmCliente() {
        String cli = (String) SessionContext.getInstance().getAttribute("cli");
        int id_cliente = this.cliente.getId_cliente();
        List<BilheteDTO> bilhetes = this.acessoLogica.getSingletonLogica().selectAllBilhetesFromACliente(id_cliente);
        if (bilhetes == null) {
            return null;
        }

        return bilhetes;
    }

}
