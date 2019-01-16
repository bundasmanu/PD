/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import javax.ejb.Local;
import tpdtos.BilheteDTO;
import tpdtos.ViagemDTO;

/**
 *
 * @author gustavo
 */
@Local
public interface leilaoLocal {
    
    public boolean adicionaBilheteLeilao(BilheteDTO bil);
    
}
