/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gustavo
 */
@Stateless
public class BagagensFacade extends AbstractFacade<Bagagens> implements BagagensFacadeLocal {

    @PersistenceContext(unitName = "TP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BagagensFacade() {
        super(Bagagens.class);
    }
    
}
