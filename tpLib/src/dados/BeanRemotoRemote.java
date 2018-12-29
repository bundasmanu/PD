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

/**
 *
 * @author gustavo
 */
@Remote
public interface BeanRemotoRemote extends AviaoRemotoFunc, CompanhiaRemotoFunc{
    @Override
    public boolean insertCompanhia(CompanhiaDTO d);
    @Override
    public boolean deleteCompanhia(CompanhiaDTO d);
    @Override
    public boolean updateCompanhia(String nomeComp, String novoNome);
    @Override
    public List<CompanhiaDTO> selectList();
    @Override
    public List<AviaoDTO> selectAvioes(String nome_c);
     public String mostraOla();
    
    @Override
    public boolean insertAviao( AviaoDTO aviao,String nome_companhia);
    @Override
    public boolean deleteAviao(String nome_aviao,String nome_companhia);
    @Override
    public boolean updateAviao(String nome_aviao,Integer novo_num_lugares);
    @Override
    public String selectionaAviao(String nome_aviao);
    
    
}
