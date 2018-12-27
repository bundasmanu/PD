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
import tpdtos.AgenciaDTO;
import tpdtos.OperadorDTO;

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
 
  public static void interface_inicial(){
     
    char c;
    String option;
     
    System.out.println("(Q)uit, (C)ompanhia, (A)viao, (O)perador\n");
    option=sc.next().toUpperCase();
     
    if(option.length() >= 1){
        c = option.charAt(0);
    }else{
        c = ' ';
    }
    
    switch(c){
        
        case 'C':
            interface_Companhia();
            break;  
        case 'A':
            interface_aviao();
            break;
        case 'O':
            interface_Operador();
            break;
        case 'Q':
            sair=true;
            break;
        default:
            break;
    }
    
     
 }
    
 public static void interface_Companhia(){
     
    char c;
    String option;
     
    System.out.println("(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)elect Loja\n");
    option=sc.next().toUpperCase();
     
    if(option.length() >= 1){
        c = option.charAt(0);
    }else{
        c = ' ';
    }
    
    switch(c){
        
        case 'I':

            break;  
        case 'U':

            break;
        case 'D':

            break;  
        case 'S':

            break;
        case 'Q':
            sair=true;
            break;
        default:
            break;
    }

 }
 
  public static void interface_Operador(){
     
    char c;
    String option;
    String user; 
    
    System.out.println("(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)elect Empregado\n");
    option=sc.next().toUpperCase();
     
    if(option.length() >= 1){
        c = option.charAt(0);
    }else{
        c = ' ';
    }
    
    switch(c){
        
        case 'I':

            break;  
        case 'U':

            break;
        case 'D':

            break;
        case 'S':

            break;
        case 'Q':
            sair=true;
            break;
        default:
            break;
    }

 }
  
  public static void interface_aviao(){
      
    char c;
    String option;
    String user; 
    
    System.out.println("(Q)uit, (I)nsert\n");
    option=sc.next().toUpperCase();
     
    if(option.length() >= 1){
        c = option.charAt(0);
    }else{
        c = ' ';
    }
    
    switch (c) {
        case 'I':

        break;
        default:
            break;
    }
      
  }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        getRemoteReferences();
        AgenciaDTO agencia=new AgenciaDTO("JJAJA");
        OperadorDTO op=new OperadorDTO("JOAO","SGGSDGAMI.COM",agencia.getId());
        op.setAgencia(agencia);
        agencia.getOperadores().add(op);
        System.out.println(agencia.toString());
        System.out.println(rf.mostraOla());
        
    }
    
}
