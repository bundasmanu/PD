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
import tpdtos.ViagemDTO;

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

    @Override
    public Viagens findInverse(int p, int d){
        
        try{          
            /*TROCAR O DESTINO E A PARTIDA, DE ACORDO COM A PARTIDA E O DESTINO DA VIAGEM RETORNADA POR PARAMETRO*/
            Query qu=this.em.createQuery("select v from Viagens as v join v.idPartida p join v.idDestino d where p.idPartida="+p+" and d.idDestino="+d+"");
            Viagens retorno=(Viagens)qu.getSingleResult();
            return retorno;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    @Override
    public List<Viagens> findViagensParametrizaveis(int dest, int minPreco, int maxPreco, int maxVagas){
        
        try{
            Query qu;
            if(dest==0){
                qu=this.em.createQuery("select v from Viagens as v join fetch v.idAviao a where (v.estadoViagem='Em Processo' OR v.estadoViagem='Em leilao') and v.preco>"+minPreco+" and v.preco<"+maxPreco+" and (a.numLugares - (select count(bi.idBilhete) as x from Bilhete bi join bi.idViagens vi on vi=v)<="+maxVagas+")");
            }
            else{
                qu=this.em.createQuery("select v from Viagens as v join v.idDestino d where (v.estadoViagem='Em Processo' OR v.estadoViagem='Em leilao') and v.preco>"+minPreco+" and v.preco<"+maxPreco+" and d.idDestino='"+dest+"'");
            }
            
            List<Viagens> retorno=(List<Viagens>)qu.getResultList();
            
            List<Viagens> apenas_as_que_tem_x_vagas=new ArrayList<Viagens>();
            for(Viagens x : retorno){
                if(x.getIdAviao().getNumLugares()-x.getBilheteCollection().size()<maxVagas){
                    apenas_as_que_tem_x_vagas.add(x);
                }
            }
            
            return apenas_as_que_tem_x_vagas;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
}
