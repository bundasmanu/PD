/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author gustavo
 */

/*CONFIGURACAO DO TIPO E DO LOOKUP*/
@MessageDriven(
        activationConfig = {
            @ActivationConfigProperty(
                    propertyName = "destinationType",
                    propertyValue = "javax.jms.Queue"),
            @ActivationConfigProperty(
                    propertyName = "destinationLookup",
                    propertyValue = "jms/logsqueue"
            )
        }
    )

public class logs_mdb implements MessageListener{
    
    /*LOGGER--> ENVIO PARA O SERVIDOR DO PAYARA*/
    static final Logger logger = Logger.getLogger("SimpleMessageBean");
    
    @Resource
    private MessageDrivenContext mdc;
    
    public logs_mdb(){
        
    }
    
    @Override
    public void onMessage(Message message) {
        
        // Envia mensagem para o log do servidor
        TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                if ( msg != null)
                    logger.info("MESSAGE BEAN: Message received: " + msg.getText());
            }
        } catch (Throwable te) {
            te.printStackTrace();
        }
        
    }
    
}
