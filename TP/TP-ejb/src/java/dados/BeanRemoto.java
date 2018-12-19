/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author gustavo
 */
@Stateful
public class BeanRemoto implements BeanRemotoRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    singletonLocalLocal sing;
    
    @Override
    public String mostraOla(){
        return this.sing.showOla();
    }
    
}
