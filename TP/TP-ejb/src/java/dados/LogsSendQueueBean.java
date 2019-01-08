/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.Queue;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

/**
 *
 * @author gustavo
 */
@Stateless
public class LogsSendQueueBean implements LogsSendQueueBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Inject
    @JMSConnectionFactory("java:comp/logs")
    private JMSContext context;
    
    @Resource(mappedName = "jms/logsqueue")
    private static Queue queue;
    
    @Override
    public void sendToQueue(String text){
        
        try{
            
            TextMessage mymsg = context.createTextMessage();
            mymsg.setText(text);
            context.createProducer().send((Destination) queue, mymsg);
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        
    }
    
    
}
