/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;

import tpdtos.AviaoDTO;

/**
 *
 * @author gustavo
 */

public interface AviaoRemotoFunc {
    
     public String mostraOla();
     
    public boolean insertAviao( AviaoDTO aviao,String nome_companhia);
    public boolean deleteAviao(String nome_aviao,String nome_companhia);
    public boolean updateAviao(String nome_aviao,Integer novo_num_lugares);
      public String selectionaAviao(String nome_aviao);
    //public List<CompanhiaDTO> selectAvioes(String nome_c);
     
     
    
}
