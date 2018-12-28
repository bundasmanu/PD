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
    public List<AviaoDTO> selectAvioes(String nome_c);
    
}
