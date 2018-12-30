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
public class OperadorFacade extends AbstractFacade<Operador> implements OperadorFacadeLocal {

    @PersistenceContext(unitName = "TP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public Operador findbyName(String email){
        
        Operador x=null;
        try{
            Query qu=this.em.createNamedQuery("Operador.findByEmailOperador");
            qu.setParameter("emailOperador", email);
            x=(Operador)qu.getSingleResult();
        }
        catch(Exception e){
            return null;
        }
    
        return x;
    }

    public OperadorFacade() {
        super(Operador.class);
    }
    
}
