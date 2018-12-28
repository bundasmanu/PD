/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import tpdtos.AviaoDTO;
import tpdtos.CompanhiaDTO;

/**
 *
 * @author gustavo
 */
public interface CompanhiaRemotoFunc {
    
    public boolean insertCompanhia(CompanhiaDTO d);
    public boolean deleteCompanhia(CompanhiaDTO d);
    public boolean updateCompanhia(String nomeComp, String novoNome);
    public List<CompanhiaDTO> selectList();
    public List<AviaoDTO> selectAvioes(String nome_c);
}
