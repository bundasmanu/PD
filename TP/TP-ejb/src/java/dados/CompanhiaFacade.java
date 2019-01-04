/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gustavo
 */
@Stateless
public class CompanhiaFacade extends AbstractFacade<Companhia> implements CompanhiaFacadeLocal {

    @PersistenceContext(unitName = "TP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Companhia findbyName(String nome){
    
        Companhia x=null;    
        try{    
            Query qu=this.em.createNamedQuery("Companhia.findByNomeCompanhia");
            qu.setParameter("nomeCompanhia", nome);
            x= (Companhia)qu.getSingleResult();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return x;
    }
    
    public CompanhiaFacade() {
        super(Companhia.class);
    }
    
}
