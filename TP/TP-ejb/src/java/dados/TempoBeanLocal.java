/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import javax.ejb.Local;

/**
 *
 * @author gustavo
 */
@Local
public interface TempoBeanLocal {

    public void CancelTimers();
    public void retificaTempo(int novoVal);
    public int getTempoAtual();
    
}
