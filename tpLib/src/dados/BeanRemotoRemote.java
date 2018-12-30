/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import javax.ejb.Remote;
import tpdtos.AviaoDTO;
import tpdtos.CompanhiaDTO;
import tpdtos.OperadorDTO;
import tpdtos.PartidaDTO;

/**
 *
 * @author gustavo
 */
@Remote
public interface BeanRemotoRemote extends AviaoRemotoFunc, CompanhiaRemotoFunc, OperadorRemotoFunc, PartidaRemotoFunc{
    
    @Override
    public String mostraOla();
    
    @Override
    public boolean insertCompanhia(CompanhiaDTO d);
    
    @Override
    public boolean updateCompanhia(String nomeComp, String novoNome);
    
    @Override
    public boolean deleteCompanhia(CompanhiaDTO d);
    
    @Override
    public List<CompanhiaDTO> selectList();
    
    @Override
    public CompanhiaDTO selectCompanhia(String nome_c);
    
    @Override
    public boolean encontrouOperador(OperadorDTO d);
    
    @Override
    public boolean insertOperador(OperadorDTO d);
    
    @Override
    public boolean deleteOperador(String email);
    
    @Override
    public boolean atualizaOperador(String email, String novoNome);/*FORNECER O EMAIL DO OPERADOR E ALTERAR O SEU NOME*/
    
    @Override
    public List<OperadorDTO> selectAllOP();
    
    @Override
    public boolean insertPartida(PartidaDTO part);/*NAO PODEM EXISTIR CIDADES COM O MESMO NOME*/
    
    @Override
    public boolean deletePartida(String cidade);
    
    @Override
    public List<PartidaDTO> selectAllPartidas();
    
    @Override
    public PartidaDTO selectPartida(String cidade);
    
}
