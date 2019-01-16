/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import tpdtos.BilheteDTO;
import tpdtos.ViagemDTO;

/**
 *
 * @author gustavo
 */
@Singleton
public class leilao implements leilaoLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    ViagensFacadeLocal viagens;
    
    @EJB
    ClienteFacadeLocal cliente;    
            
    List<ViagemDTO> bilhetes_em_leilao=new ArrayList<ViagemDTO>();
    
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
                this.viagens.edit(retorno);/*ATUALIZACAO DA VIAGEM*/
            }
            
            /*VERIFICAR SE A VIAGEM JA ESTA NO DTO*/
            int existe=0;
            for(ViagemDTO x : bilhetes_em_leilao){
                if(x.getId()==bil.getViagem().getId()){
                    existe++;
                }
            }
            
            /*SENAO EXISTE A VIAGEM NO ARRAY COLOCA LA*/
            if(existe==0){
                bilhetes_em_leilao.add(bil.getViagem());
                /*INSERE BILHETE NA VIAGEM*/
                List<BilheteDTO> insere_lista_bilhetes;
               if(bil.getViagem().getBilhetes().isEmpty()==true){
                insere_lista_bilhetes=new ArrayList<BilheteDTO>();
               }
               else{
                insere_lista_bilhetes= bil.getViagem().getBilhetes();
               }
               
               insere_lista_bilhetes.add(bil);
               bil.getViagem().setBilhetes(insere_lista_bilhetes);
            }
            
            
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
     
        return true;
    }
    
    
}
