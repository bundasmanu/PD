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
    
    /*QUERY LUGARES VAGOS UMA VIAGEM*/
    @Override
    public boolean viagemLugaresVagos(int idViagem, int numeroVagas){
        
        try{
            
            /*SO RETORNA SE TIVER MAIS VAGAS, QUE AS PRETENDIDAS*/
            String query_param="SELECT v FROM Viagens v inner join Aviao a on a=v.idAviao inner join Bilhete b on b=v.idViagens  where v.idViagens= "+idViagem+" and (a.numLugares - (select count(bi.idBilhete) as x from Bilhete bi join bi.idViagens vi on vi=v)>="+numeroVagas+")";
            Query qu=this.em.createQuery(query_param);
            int n=qu.getMaxResults();
            if(n>0){
                return true;
            }
            return false;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
    }
    
    @Override
    public List<ViagemDTO> viagensMaisBaratasDestinos(){
        
        try{
            
            List<ViagemDTO> viagens=new ArrayList<ViagemDTO>();
            List<Integer> numeroLugaresAviao=new ArrayList<Integer>();
            
            String query_retorna_dados="SELECT d.idDestino , a.numLugares, min(vi.idViagens), MIN(vi.preco)\n" +
                                        "FROM Destinos d \n" +
                                        "INNER JOIN Viagens vi\n" +
                                        "ON (d = vi.idDestino)\n" +
                                        "inner join Aviao a \n" +
                                        "on a=vi.idAviao\n" +
                                        "where vi.estadoViagem='Em Processo'\n" +
                                        "GROUP BY d.idDestino, a.numLugares";
            Query qu=this.em.createQuery(query_retorna_dados);
            List<Object[]> lst=(List<Object[]>)qu.getResultList();
            for (Object o[] : lst) {
                numeroLugaresAviao.add((int)o[1]);
                viagens.add(new ViagemDTO((int)o[2]));
            }
            
            /*QUERY QUE VERIFICA QUANTOS BILHETES EXISTEM PARA AS VIAGENS MAIS BARATAS, POR DESTINO (UMA APENAS POR DESTINO)*/
            List<Integer> numero_bilhetes_Viagem=new ArrayList<Integer>();
            
            for(ViagemDTO x : viagens){
                String query_retona_numero_bilhetes_Viagem = "SELECT count(bi.idBilhete)\n"
                        + "FROM Bilhete bi\n"
                        + "inner join Viagens v\n"
                        + "on bi.idViagens=v\n"
                        + "where v.idViagens="+x.getId()+"";
                Query qu2 = this.em.createQuery(query_retona_numero_bilhetes_Viagem);
                //qu2.setParameter("valor_idViagem", x.getId());
                Long ret_numeroBilhetes = (Long)qu2.getSingleResult();
                Integer numeroBilhetes= (int) (long) ret_numeroBilhetes;
                numero_bilhetes_Viagem.add(numeroBilhetes);
            }
            
            /*SUBTRACCAO DOS LUGARES QUE O AVIAO QUE FAZ A VIAGEM (MAIS BARATA PARA CADA DESTINO= - BILHETES VENDIDOS*/
            //List<Integer> lugares_vagos=new ArrayList<Integer>();
            for(int i=0;i<numeroLugaresAviao.size();++i){
                viagens.get(i).setNumeroVagas(numeroLugaresAviao.get(i)-numero_bilhetes_Viagem.get(i));
            }
            
            return viagens;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    @Override
    public List<Viagens> getViagemLeilao(){
        
        try{
            
            String query_viagens_leilao="select v from Viagens v where v.estadoViagem='Em leilao'";
            Query q=this.em.createQuery(query_viagens_leilao);
            List<Viagens> retorno_viagens_leilao=(List<Viagens>)q.getResultList();
            
            return retorno_viagens_leilao;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
}
