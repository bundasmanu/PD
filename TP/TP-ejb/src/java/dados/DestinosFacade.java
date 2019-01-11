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
public class DestinosFacade extends AbstractFacade<Destinos> implements DestinosFacadeLocal {

    @PersistenceContext(unitName = "TP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DestinosFacade() {
        super(Destinos.class);
    }
    
    @Override
    public Destinos findbyName(String nomeCidade) {

        Destinos x = null;
        try {
            Query qu = this.em.createNamedQuery("Destinos.findByCidadeDestino");
            qu.setParameter("cidadeDestino", nomeCidade);
            x = (Destinos) qu.getSingleResult();
        } catch (Exception e) {
            return null;
        }

        return x;

    }

}
