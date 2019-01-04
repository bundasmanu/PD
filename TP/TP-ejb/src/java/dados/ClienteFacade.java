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
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

    @PersistenceContext(unitName = "TP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    @Override
    public Cliente findbyEmail(String email){ /*POR EMAIL, SO RETORNA NO MAXIMO UM CLIENTE, VISTO QUE NAO EXISTE CLIENTES COM O MSM EMAIL*/
        
        Cliente x=null;
        try{
            Query qu=this.em.createNamedQuery("Cliente.findByEmailCliente");
            qu.setParameter("emailCliente", email);
            x=(Cliente)qu.getSingleResult();
        }
        catch(Exception e){
            return null;
        }
    
        return x;
        
    }
    
}
