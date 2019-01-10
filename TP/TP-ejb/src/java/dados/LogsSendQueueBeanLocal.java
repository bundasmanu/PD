/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.concurrent.Future;
import javax.ejb.Asynchronous;
import javax.ejb.Local;

/**
 *
 * @author gustavo
 */
@Local
public interface LogsSendQueueBeanLocal {
    
    @Asynchronous
    Future<Boolean> sendToQueue(String text);
    
    boolean acrescentaLogsFicheiro(String info_txt);
    
}
