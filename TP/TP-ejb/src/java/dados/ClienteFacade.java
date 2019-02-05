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
    public Cliente findbyEmail(String email) {
        /*POR EMAIL, SO RETORNA NO MAXIMO UM CLIENTE, VISTO QUE NAO EXISTE CLIENTES COM O MSM EMAIL*/

        Cliente x = null;
        try {
            Query qu = this.em.createNamedQuery("Cliente.findByEmailCliente");
            qu.setParameter("emailCliente", email);
            x = (Cliente) qu.getSingleResult();
        } catch (Exception e) {
            return null;
        }

        return x;

    }

    @Override
    public List<Companhia> findAllCompaniesFromClient(int id_cliente) {
        return em.createQuery("SELECT DISTINCT c.* \n"
                + "FROM bilhete b \n"
                + "INNER JOIN Viagens v ON b.id_viagens=v.id_viagens \n"
                + "INNER JOIN Aviao a ON v.id_aviao=a.id_aviao\n"
                + "INNER JOIN Companhia c ON a.id_companhia=c.id_companhia\n"
                + "WHERE id_cliente= :id_cliente")
                .setParameter("id_cliente", id_cliente)
                .getResultList();
    }

    @Override
    public List<Destinos> findAllDestiniesFromCliente(int id_cliente) {
//        return em.createQuery("SELECT DISTINCT d "
//                + "FROM Bilhete b "
//                + "INNER JOIN Viagens v ON b.idViagens=v.idViagens "
//                + "INNER JOIN Destinos d ON v.idDestino=d.idDestino "
//                + "WHERE b.idCliente= :id_cliente")
//                .setParameter("id_cliente", id_cliente)
//                .getResultList();
         try{
              return em.createQuery("SELECT DISTINCT d "
                + "FROM Bilhete b "
                 +"INNER JOIN Viagens v ON b.idViagens=v.idViagens "
                 +"INNER JOIN Destinos d ON v.idDestino=b.idDestino "
                + "WHERE b.idCliente= :id_cliente")
                .setParameter("id_cliente", id_cliente)
                .getResultList();
         }
         catch(Exception e){
             System.out.println(""+e.getMessage());
             return null;
         }
        
        
    }

    
}
