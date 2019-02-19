/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import dados.TempoBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import dados.singletonLocalLocal;

/**
 *
 * @author gustavo
 */
@Singleton
public class intermedioLogica implements intermedioLogicaLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    singletonLocalLocal intermedio;
    
    @EJB
    TempoBeanLocal cr;
    
    @Override
    public singletonLocalLocal getSingletonLogica(){
        return intermedio;
    }
    
}
