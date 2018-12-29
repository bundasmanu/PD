/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operadorcliente;

import dados.BeanRemotoRemote;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tpdtos.AgenciaDTO;
import tpdtos.AviaoDTO;
import tpdtos.CompanhiaDTO;
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
    private static AgenciaDTO agencia=new AgenciaDTO("JJAJA");
    
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
     
    System.out.println("(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)elect Companhias\n");
    option=sc.next().toUpperCase();
     
    if(option.length() >= 1){
        c = option.charAt(0);
    }else{
        c = ' ';
    }
    
    switch(c){
        
        case 'I':
            try{
                System.out.println("\nIntroduza o nome da companhia\n");
                String nome_comp=sc.next();
                CompanhiaDTO novaComp=new CompanhiaDTO(nome_comp);
                boolean aconteceu=rf.insertCompanhia(novaComp);
                if(aconteceu==true){
                    System.out.println("\nInserida a companhia com sucesso\n");
                }
                else{
                    System.out.println("\nOcorreu um problema\n");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;  
        case 'U':
            try{
                System.out.println("\nIntroduza o nome da companhia a atualizar\n");
                String nome_comp=sc.next();
                System.out.println("\nIntroduza o novo nome da companhia\n");
                String novo_nome=sc.next();
                boolean retorno=rf.updateCompanhia(nome_comp, novo_nome);
                if(retorno==false){
                    System.out.println("\nErro na atualizacao\n");
                }
                else{
                    System.out.println("\nCompanhia atualizada com sucesso\n");
                }
            }    
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;
        case 'D':
               try{
                   System.out.println("\nQual o nome da companhia a apagar\n");
                   String nomc=sc.next();
                   CompanhiaDTO comp_apag=new CompanhiaDTO(nomc);
                   boolean deleteOP=rf.deleteCompanhia(comp_apag);
                   if(deleteOP==false){
                       System.out.println("\nErro na Operacao\n");
                   }
                   else{
                       System.out.println("\nOperacao bem sucedida\n");
                   }
               } 
               catch(Exception e){
                   System.out.println(e.getMessage());
               }
            break;  
        case 'S':
                try{
                    List<CompanhiaDTO> lista_companhia=rf.selectList();
                    if(lista_companhia==null){
                        System.out.println("\nErro na Operacao\n");
                    }
                    else{
                        for(CompanhiaDTO x : lista_companhia){
                            System.out.println(x.toString());
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
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
    
    System.out.println("(Q)uit, (I)nsert, (D)elete, (U)pdate, (S)elect aviao\n");
    option=sc.next().toUpperCase();
     
    if(option.length() >= 1){
        c = option.charAt(0);
    }else{
        c = ' ';
    }
    
    switch (c) {
        case 'I':
            try{
                System.out.println("\nIntroduza o nome do aviao\n");
                String nome_aviao=sc.next();
                System.out.println("\nIntroduza o numero de lugares\n");
                Integer num_lug=sc.nextInt();
                System.out.println("\nIntroduza o nome da companhia\n");
                String nome_comp= sc.next();
                AviaoDTO aviao= new AviaoDTO(nome_aviao,num_lug);
                boolean aconteceu=rf.insertAviao(aviao,nome_comp);
                if(aconteceu==true){
                    System.out.println("\nInserido o aviao com sucesso\n");
                }
                else{
                    System.out.println("\nOcorreu um problema\n");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        break;
        case 'D':
           try{
                   System.out.println("\nQual o nome do aviao a apagar\n");
                   String noma=sc.next();
                   System.out.println("\nQual o nome da companhia  que tinha\n");
                   String nomecompanhia=sc.next();
                   boolean deleteAviao=rf.deleteAviao(noma,nomecompanhia);
                   if(deleteAviao==false){
                       System.out.println("\nErro na Operacao\n");
                   }
                   else{
                       System.out.println("\nOperacao bem sucedida\n");
                   }
               } 
               catch(Exception e){
                   System.out.println(e.getMessage());
               }
           break;
        case 'U':
             try{
                   System.out.println("\nQual o nome do aviao a atualizar\n");
                   String nome_av=sc.next();
                   System.out.println("\nQual o numero de lugares a atualizar para esse aviao\n");
                   Integer numlu=sc.nextInt();
                   //AviaoDTO avi_apag= new AviaoDTO(noma, numLugares);
                   boolean atualizaAviao=rf.updateAviao(nome_av,numlu);
                   if(atualizaAviao==false){
                       System.out.println("\nErro na Operacao\n");
                   }
                   else{
                       System.out.println("\nOperacao bem sucedida\n");
                   }
               } 
               catch(Exception e){
                   System.out.println(e.getMessage());
               }
           break;
             case 'S':
                try{
                   System.out.println("\nQual o nome do aviao que pretende visualizar\n");
                   String nome_av2=sc.next();
                   
                   //AviaoDTO avi_apag= new AviaoDTO(noma, numLugares);
                   System.out.println(""+rf.selectionaAviao(nome_av2));
               } 
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            break;
        default:
            break;
    }
      
  }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        getRemoteReferences();
        while(sair!=true){
           //interface_banco();
           interface_inicial();
        }
        /*OperadorDTO op=new OperadorDTO("JOAO","SGGSDGAMI.COM",agencia.getId());
        op.setAgencia(agencia);
        agencia.getOperadores().add(op);
        System.out.println(agencia.toString());
        System.out.println(rf.mostraOla());*/
        
    }
    
}
