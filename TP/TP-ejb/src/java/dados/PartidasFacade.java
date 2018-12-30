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
public class PartidasFacade extends AbstractFacade<Partidas> implements PartidasFacadeLocal {

    @PersistenceContext(unitName = "TP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PartidasFacade() {
        super(Partidas.class);
    }
    
    @Override
    public Partidas findbyName(String nomeCidade){
        
        Partidas x=null;
        try{
            Query qu=this.em.createNamedQuery("Partidas.findByCidadePartida");
            qu.setParameter("cidadePartida", nomeCidade);
            x=(Partidas)qu.getSingleResult();
        }
        catch(Exception e){
            return null;
        }
    
        return x;
        
    }
    
}
