/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import tpdtos.AviaoDTO;
import tpdtos.CompanhiaDTO;

/**
 *
 * @author gustavo
 */
@Stateful
@Interceptors(RemoteInterceptor.class)
public class BeanRemoto implements BeanRemotoRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    singletonLocalLocal sing;
    
    @Override
    public String mostraOla(){
        return this.sing.showOla();
    }
    
    @PostConstruct
    public void inicializacao(){
        System.out.println("\nConstruido\n");
    }
    
    @PreDestroy
    public void xau(){
        System.out.println("\nDestruindo\n");
    }
    
    @Override
    public boolean insertCompanhia(CompanhiaDTO d){
        return this.sing.insertCompanhia(d);
    }
    
    @Override
    public boolean deleteCompanhia(CompanhiaDTO d){
        return this.sing.apagaCompanhia(d);
    }
    
    @Override
    public boolean updateCompanhia(String nomeComp, String novoNome){
        return this.sing.atualizaCompanhia(nomeComp, novoNome);
    }
    
  
    @Override
    public List<AviaoDTO> selectAvioes(String nome_c){
        return this.sing.selectAvioes(nome_c);
    }

    @Override
    public boolean insertAviao(AviaoDTO aviao,String nome_companhia) {
       return this.sing.insertAviao(aviao, nome_companhia);
    }

    @Override
    public boolean deleteAviao(String nome_aviao,String nome_companhia) {
        return this.sing.deleteAviao(nome_aviao,nome_companhia);
    }

    @Override
    public boolean updateAviao(String nome_aviao,Integer novo_num_lugares) {
        return this.sing.updateAviao(nome_aviao, novo_num_lugares);
    }
    
    @Override
    public List<CompanhiaDTO> selectList(){
        return this.sing.selectAll();
    }

    @Override
    public String selectionaAviao(String nome_aviao){
        return this.sing.selectionaAviao(nome_aviao);
    }

   
    
}
