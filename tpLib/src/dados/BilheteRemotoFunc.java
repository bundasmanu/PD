/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import tpdtos.BilheteDTO;

/**
 *
 * @author gustavo
 */
public interface BilheteRemotoFunc {
    
    public boolean insertBilhete(int id_viagem, int id_cliente);
    public boolean deleteBilhete(int id);
    /*UPDATE ACHO QUE NAO FAZ SENTIDO PORQUE O PRECO NAO PODE SER ALTERADO DPS DE O BILHETE ESTAR COMPRADO*/ 
    public BilheteDTO selectBilhete(int id_bilhete);
    public List<BilheteDTO> selectAllBilhetes();
    public boolean insertBilheteLeilao(int id_viagem, int id_cliente, int preco_leiloado);
    
}
