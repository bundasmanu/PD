/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gustavo
 */
@Stateless
public class AviaoFacade extends AbstractFacade<Aviao> implements AviaoFacadeLocal {

    @PersistenceContext(unitName = "TP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AviaoFacade() {
        super(Aviao.class);
    }

    @Override
    public List<Aviao> findNomeAviao(String nome) {
        
        try{
            Query qu = this.em.createNamedQuery("Aviao.findByNomeAviao");
            qu.setParameter("nomeAviao", nome);
            List<Aviao> retorno_pesquisa=qu.getResultList();
            return retorno_pesquisa;
        }
        catch(Exception e){
            return null;
        }
        
    }

}
