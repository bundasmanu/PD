/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import javax.ejb.Local;
import tpdtos.AviaoDTO;
import tpdtos.CompanhiaDTO;
import tpdtos.OperadorDTO;
import tpdtos.PartidaDTO;

/**
 *
 * @author gustavo
 */
@Local
public interface singletonLocalLocal {
    
    public String showOla();
    public boolean insertCompanhia(CompanhiaDTO d);
    public boolean atualizaCompanhia(String nomeComp, String novoNome);
    public boolean apagaCompanhia(CompanhiaDTO d);
    public List<CompanhiaDTO> selectAll();
    public CompanhiaDTO selectCompanhia(String nome_c);
    
    public boolean encontrouOperador(OperadorDTO d);
    public boolean insereOperador(OperadorDTO d);
    public boolean apagaOperador(String email);
    public boolean atualizaOp(String email, String novoNome);/*FORNECER O EMAIL DO OPERADOR E ALTERAR O SEU NOME*/
    public List<OperadorDTO> selectAllOPS();
    
    public boolean inserePartida(PartidaDTO part);/*NAO PODEM EXISTIR CIDADES COM O MESMO NOME*/
    public boolean apagaPartida(String cidade);
    public List<PartidaDTO> seleccionaAllPartidas();
    public PartidaDTO seleccionaPartida(String cidade);
    
}
