/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import dados.TempoBeanLocal;
import dados.singletonLocalLocal;
import javax.ejb.Local;

/**
 *
 * @author gustavo
 */
@Local
public interface intermedioLogicaLocal {
    
    singletonLocalLocal getSingletonLogica();
    
}
