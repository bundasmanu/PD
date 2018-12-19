/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operadorcliente;

import dados.BeanRemotoRemote;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author gustavo
 */
public class OperadorCliente {

    /**
     * @param args the command line arguments
     */
    
    static BeanRemotoRemote rf; //Remoto
    static Scanner sc = new Scanner (System.in);
    static boolean sair=false;
    static int estou_logado=0;
    static String id="";
    
            // propriedades
 public static void getRemoteReferences(){
   Properties prop = new Properties();

   prop.setProperty("java.naming.factory.initial",
      "com.sun.enterprise.naming.SerialInitContextFactory");

   prop.setProperty("java.naming.factory.url.pkgs",
                    "com.sun.enterprise.naming");

   prop.setProperty("java.naming.factory.state",
      "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

   prop.setProperty("org.omg.CORBA.ORBInitialHost", "192.168.56.175");
   prop.setProperty("org.omg.CORBA.ORBInitialPort", "3700");


// adaptar
   InitialContext ctx = null;
   try {
      ctx = new InitialContext(prop);
   }
   catch (NamingException e) {
      System.out.println(e.getMessage());
      System.exit(1);
   }


// adaptar
String rsfull_class_name = "java:global/TP/TP-ejb/BeanRemoto!dados.BeanRemotoRemote";

   try {
      System.out.println("Iniciar lookup");
      rf = (BeanRemotoRemote) ctx.lookup(rsfull_class_name);
   }
   catch (NamingException e) {
      System.out.println(e.getMessage());
   }
 }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        getRemoteReferences();
        System.out.println(rf.mostraOla());
        
    }
    
}
