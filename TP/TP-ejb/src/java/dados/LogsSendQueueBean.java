/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 *
 * @author gustavo
 */
@Stateless
public class LogsSendQueueBean implements LogsSendQueueBeanLocal, Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    @JMSConnectionFactory("jms/logs")
    private JMSContext context;
    
    @Resource(mappedName = "jms/logsqueue")
    private Queue queue;
    
    @EJB
    LogsFacadeLocal logs;
    
    @Override
    @Asynchronous
    public Future<Boolean> sendToQueue(String text){
        
        try{
            
            TextMessage mymsg = context.createTextMessage();
            mymsg.setText(text);
            /*ENVIO PARA A QUEUE*/
            context.createProducer().send((Destination) queue, mymsg); /*SE ESTOIRAR AQUI NAO FAZ O ENVIO PARA O FICHEIRO, QUE É O QUE SE PRETENDE*/
            /*ENVIO PARA O FICHEIRO*/
            this.acrescentaLogTabela(text);/*ACRESENTO DO LOG A TABELA*/
            
            return new AsyncResult<>(true);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return new AsyncResult<>(false);
        }
        
    }
    
    public void acrescentaLogTabela(String info){
        
       try{
           
           Logs l=new Logs(info, new Date());
           logs.create(l);
           
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       } 
       
    }
    
    @Override
    public boolean acrescentaLogsFicheiro(String info_txt){
        
        /*FICHEIRO FICA NA DIRETORIA, ONDE SE ENCONTRA O PROJETO*/
        /*NOME DO FICHEIRO IRÁ SER LOGS*/

        try (BufferedWriter out = new BufferedWriter(new FileWriter("//TP//tpLib//src//dados//logs.txt"))) {
            out.write(info_txt+"FHASFHDSHGFGHDSGHFDGHVFDSA");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
