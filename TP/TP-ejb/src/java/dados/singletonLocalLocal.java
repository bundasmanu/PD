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

/**
 *
 * @author gustavo
 */
@Local
public interface singletonLocalLocal {
    
    public String showOla();
    //parte das companhias
    public boolean insertCompanhia(CompanhiaDTO d);
    public boolean atualizaCompanhia(String nomeComp, String novoNome);
    public boolean apagaCompanhia(CompanhiaDTO d);
    public List<CompanhiaDTO> selectAll();
    public List<AviaoDTO> selectAvioes(String nome_c);
    
    //parte do aviao
    public boolean insertAviao(AviaoDTO aviao,String nome_companhia);
    public boolean deleteAviao(String nome_aviao,String nome_companhia);
    public boolean updateAviao(String nome_aviao,Integer novo_num_lugares);
    public String selectionaAviao(String nome_aviao);
}
