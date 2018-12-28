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
    public boolean insertCompanhia(CompanhiaDTO d);
    public boolean atualizaCompanhia(String nomeComp, String novoNome);
    public boolean apagaCompanhia(CompanhiaDTO d);
    public List<CompanhiaDTO> selectAll();
    public List<AviaoDTO> selectAvioes(String nome_c);
}
