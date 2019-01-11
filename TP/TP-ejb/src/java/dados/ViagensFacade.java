/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gustavo
 */


@Stateless
public class ViagensFacade extends AbstractFacade<Viagens> implements ViagensFacadeLocal {
  
    @PersistenceContext(unitName = "TP-ejbPU")
    private EntityManager em;
    static Logger LOGGER= Logger.getLogger(ViagensFacade.class.getName());
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ViagensFacade() {
        super(Viagens.class);
    }

    @Override
    public List<Viagens> findbyPrice(int preco){
        
       try{
           
           Query qu=this.em.createNamedQuery("Viagens.findByPreco");
           qu.setParameter("preco", preco);
           List<Viagens> retorno_pesquisa=(List<Viagens>)qu.getResultList();
           return retorno_pesquisa;
       }
       catch(Exception e){
           System.out.println(e.getMessage());
           return null;
       }
        
    }

}
