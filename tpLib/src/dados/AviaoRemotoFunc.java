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
     
    public boolean insertAviao(AviaoDTO aviao,String nome_companhia);
    public boolean deleteAviao(int id);
    public boolean updateAviao(int id,Integer novo_num_lugares);
    public List<AviaoDTO> selectionaAviao();
    //public List<CompanhiaDTO> selectAvioes(String nome_c);
     
     
    
}
