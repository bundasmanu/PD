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
        try{
            
            String query_param=("SELECT DISTINCT d "
                 + "FROM Bilhete b " 
                 + "INNER JOIN Cliente c ON b.idCliente=c "
                 +" INNER JOIN Viagens v ON b.idViagens=v "
                 +" INNER JOIN Aviao a on v.idAviao=a"   
                 +" INNER JOIN Companhia d ON a.idCompanhia=d "
                 + " WHERE c.idCliente= '"+id_cliente+"'");
            Query qu=this.em.createQuery(query_param);
            List<Companhia> comp=(List<Companhia>)qu.getResultList();
            
            return comp;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
            
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
            String query_param=("SELECT DISTINCT d "
                 + "FROM Bilhete b "
                 + "INNER JOIN Cliente c ON b.idCliente=c "
                 +" INNER JOIN Viagens v ON b.idViagens=v "
                 +" INNER JOIN Destinos d ON v.idDestino=d "
                 + "WHERE c.idCliente= '"+id_cliente+"'");
            Query qu=this.em.createQuery(query_param);
            List<Destinos> lista_retorno=(List<Destinos>)qu.getResultList();
            
            return lista_retorno;
         }
         catch(Exception e){
             System.out.println(""+e.getMessage());
             return null;
         }
        
        
    }

    
}
