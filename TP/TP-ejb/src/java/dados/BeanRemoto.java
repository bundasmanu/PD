/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import tpdtos.AviaoDTO;
import tpdtos.CompanhiaDTO;
import tpdtos.OperadorDTO;
import tpdtos.PartidaDTO;

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
    public List<CompanhiaDTO> selectList(){
        return this.sing.selectAll();
    }
    
    @Override
    public CompanhiaDTO selectCompanhia(String nome_c){
        return this.sing.selectCompanhia(nome_c);
    }
    
    @Override
    public boolean encontrouOperador(OperadorDTO d){
        return this.sing.encontrouOperador(d);
    }
    
    @Override
    public boolean insertOperador(OperadorDTO d){
        return this.sing.insereOperador(d);
    }
    
    @Override
    public boolean deleteOperador(String email){
        return this.sing.apagaOperador(email);
    }
    
    @Override
    public boolean atualizaOperador(String email, String novoNome){
        return this.sing.atualizaOp(email, novoNome);
    }
    
    @Override
    public List<OperadorDTO> selectAllOP(){
        return this.sing.selectAllOPS();
    }
    
    @Override
    public boolean insertPartida(PartidaDTO part){
        return this.sing.inserePartida(part);
    }
    
    @Override
    public boolean deletePartida(String cidade){
        return this.sing.apagaPartida(cidade);
    }
    
    @Override
    public List<PartidaDTO> selectAllPartidas(){
        return this.sing.seleccionaAllPartidas();
    }
    
    @Override
    public PartidaDTO selectPartida(String cidade){
        return this.sing.seleccionaPartida(cidade);
    }
    
}
