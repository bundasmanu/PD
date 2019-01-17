/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import tpdtos.BilheteDTO;
import tpdtos.ViagemDTO;

/**
 *
 * @author gustavo
 */
@Singleton
public class leilao implements leilaoLocal{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    ViagensFacadeLocal viagens;
    
    @EJB
    ClienteFacadeLocal cliente;    
            
    List<ViagemDTO> bilhetes_em_leilao=new ArrayList<ViagemDTO>();
    
    private static final Logger LOGGER = Logger.getLogger( leilao.class.getName() );
    
    @Override
    public boolean adicionaBilheteLeilao(BilheteDTO bil){ /*PASSAR UMA VIAGEM E O PRECO DO BILHETE E O CLIENTE QUE É, ID POR EXEMPLO*/
    
        try{
           
            /*BUSCAR A VIAGEM*/
            Viagens retorno=this.viagens.find(bil.getViagem().getId());
            
            /*EM PRINCIPIO ESTA TUDO BEM, MAS É MELHOR VERIFICAR*/
            if(retorno==null){
                return false;
            }

            /*VERIFICAR SE O PRECO DA VIAGEM JÁ ESTA A 0, SENAO ESTIVER COLOCAR A 0*/
            if(retorno.getPreco()!=0){
                retorno.setPreco(0);/*A REFERENCIA DO OBJETO JA ESTA A 0*/
                retorno.setEstadoViagem("Em leilao");
                this.viagens.edit(retorno);/*ATUALIZACAO DA VIAGEM*/
            }
            
            /*VERIFICAR SE A VIAGEM JA ESTA NO DTO*/
            int existe=0;
            for(ViagemDTO x : bilhetes_em_leilao){
                if(x.getId()==retorno.getIdViagens()){
                    existe++;
                }
            }
            
            /*SENAO EXISTE A VIAGEM NO ARRAY COLOCA LA*/
            if(existe==0){
                /*CRIACAO DA VIAGEM, E INSERCAO NO ARRAY*/
                ViagemDTO novaViagem = new ViagemDTO(retorno.getHoraPartida(), retorno.getHoraChegada());
                novaViagem.setId(retorno.getIdViagens());               
                bilhetes_em_leilao.add(novaViagem);
                
                /*INSERE BILHETE NA VIAGEM-->NA VIAGEMDTO OS BILHETES SAO INICIALIZADOS NO CONSTRUCTOR*/
                novaViagem.getBilhetes().add(bil);
                
            }
            else{ /*SE JA EXISTE A VIAGEM, JA EXISTE BILHETE*/
            
                for(ViagemDTO x : bilhetes_em_leilao){
                    if(x.getId()==retorno.getIdViagens()){
                        x.getBilhetes().add(bil);
                    }
                }
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
     
        return true;
    }
    
    /*RETORNA A LISTA DE BILHETES LEILOADOS, E QUE FORAM SELECCIONADOS*/
    @Override
    public List<BilheteDTO> retornoBilhetesGanhosLeilaoPorViagem(Viagens v){
        
        /*VERIFICAR SE A VIAGEM ESTA DENTRO DO ARRAY, SE ESTIVER RETORNAR OS BILHETES LICITADOS MAIS ALTOS COM OS LUGARES DISPONIVEIS EM LEILAO*/
        try{
            int ex=0;
            for(ViagemDTO x : this.bilhetes_em_leilao){
                if(x.getId()==v.getIdViagens()){
                    /*CHAMA METODO DE ORDENAGEM DE BILHETES*/
                    List<BilheteDTO> ordenado=this.retornaBilhetesViagemLeilaoOrdenados(x);
                    if(ordenado!=null){
                        int quantos_lugares_colocar=v.getIdAviao().getNumLugares()-v.getBilheteCollection().size();
                        List<BilheteDTO> bilhetes_sucesso=this.verificaSeClientesTemDinheiro(ordenado, quantos_lugares_colocar);
                        return bilhetes_sucesso;
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
        return null;
        
    } 
    
    /*PERANTE TODOS OS BILHETES LEILOADAS, COLOCA-OS POR ORDEM*/
    @Override
    public List<BilheteDTO> retornaBilhetesViagemLeilaoOrdenados(ViagemDTO v){
        
        try{
            
            /*VERIFICAR SE EXISTEM BILHETES DE LEILAO NA VIAGEM*/
            if(v.getBilhetes().isEmpty()==true){
                return null;
            }
            
            /*SE EXISTIREM OS BILHETES COLOCO-OS POR ORDEM, NO INICIO OS QUE APRESENTAM MAIOR VALOR*/
            List<BilheteDTO> retorno_bilhetes_ordenados=new ArrayList<BilheteDTO>();
            retorno_bilhetes_ordenados.add(v.getBilhetes().get(0));/*NAO DA NULL, PORQUE TEM SEMPRE ALGO, PORQUE SE ESTA AQUI NAO RETORNOU NA EXCECAO DO EMPTY*/
            
            LOGGER.info("\nMax "+v.getBilhetes().size());
            
            int max=0;
            for(BilheteDTO x : v.getBilhetes().subList(1, v.getBilhetes().size())){
                if(x.getPreco_bilhete()>max){
                    retorno_bilhetes_ordenados.add(0, x);
                    max=x.getPreco_bilhete();
                }
                else{
                    retorno_bilhetes_ordenados.add(retorno_bilhetes_ordenados.size() + 1, x);
                }
                
            }
            
            return retorno_bilhetes_ordenados;
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    /*PERANTE A LISTA ORDENADA DE BILHETES DE UMA VIAGEM, SELECCIONA OS MELHORES E NOS QUAIS OS CLIENTES PODEM PAGAR*/
    @Override
    public List<BilheteDTO> verificaSeClientesTemDinheiro(List<BilheteDTO> b, int lugares_possiveis){
        
        try{
            
            int quantos_seleccionados=0;
            List<BilheteDTO> nova_lista_bilhetes=new ArrayList<BilheteDTO>();
            for(BilheteDTO x : b){
                if(x.getCli().getConta()>x.getPreco_bilhete()){/*SE O CLIENTE TIVER O DINHEIRO QUE APOSTOU TUDO BEM, SENAO PASSA PARA O PROXIMO*/
                    nova_lista_bilhetes.add(x);
                    quantos_seleccionados++;
                }
                if(quantos_seleccionados==lugares_possiveis){
                    return nova_lista_bilhetes;
                }
            }
            
            return nova_lista_bilhetes;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    /*ELIMINA VIAGEM DO ARRAY DPS DE PARTIR*/
    public boolean eliminaViagemArray(int id_viagem_eliminar){
        
        try{
            
            for(ViagemDTO x : this.bilhetes_em_leilao){
                if(x.getId()==id_viagem_eliminar){
                    return this.bilhetes_em_leilao.remove(x);
                }
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
        return false;
    }
    
}
