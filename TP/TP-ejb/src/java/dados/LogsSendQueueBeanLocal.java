/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import javax.ejb.Remote;

/**
 *
 * @author gustavo
 */
@Remote
public interface LogsSendQueueBeanLocal {
    
    void sendToQueue(String text);
    
}
