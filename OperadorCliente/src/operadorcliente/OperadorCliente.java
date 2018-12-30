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
import tpdtos.CompanhiaDTO;
import tpdtos.OperadorDTO;
import tpdtos.PartidaDTO;

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
    static boolean user_aceite=false;
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
 
 public static void sigInOperador(){
     
    char c;
    String option;
     
    System.out.println("\n(Q)uit, (S)igIn");
    option=sc.next().toUpperCase(); sc.skip("\n");
     
    if(option.length() >= 1){
        c = option.charAt(0);
    }else{
        c = ' ';
    }
    
        switch(c){
        
        case 'S':
            try{
                System.out.println("\nIntroduza o seu email: ");
                String email=sc.nextLine();
                System.out.println("\nIntroduza a sua pass");
                String pass=sc.nextLine();
                OperadorDTO d=new OperadorDTO(email, pass);
                boolean existe=rf.encontrouOperador(d);
                if(existe==true){
                    user_aceite=true;/*USER ACEITE--> PODE PROSSEGUIR*/
                    System.out.println("\nPode prosseguir\n");
                }
                else{
                    System.out.println("\nUtilizador nao foi encontrado, tente novamente\n");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;
        case 'Q':
            System.exit(1);
            break;
        default:
            break;
    }
    
 }
 
  public static void interface_inicial(){
     
    char c;
    String option;
     
    System.out.println("\n(Q)uit, (C)ompanhia, (A)viao, (O)perador, (P)artidas\n");
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
        case 'P':
            interface_Partidas();
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
     
    System.out.println("\n(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)elect Companhias\n");
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
    
    System.out.println("\n(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)electAll");
    option=sc.next().toUpperCase(); 
    sc.skip("\n");
     
    if(option.length() >= 1){
        c = option.charAt(0);
    }else{
        c = ' ';
    }
    
    String email_op;
    String nome_op;
    String pass_op;
    OperadorDTO d_op=null;
    boolean retorno;
    
    switch(c){
        
        case 'I':
            try{
                System.out.println("\nIntroduza o seu email: ");
                email_op=sc.nextLine();
                System.out.println("\nIntroduza o seu nome: ");
                nome_op=sc.nextLine();
                System.out.println("\nIntroduza a sua pass: ");
                pass_op=sc.nextLine();
                d_op=new OperadorDTO(nome_op,email_op,pass_op);
                retorno=rf.insertOperador(d_op);
                if(retorno==false){
                    System.out.println("\nNao foi possivel inserir o operador\n");
                }
                else{
                    System.out.println("\nOperador inserido com sucesso\n");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;  
        case 'U':
            try{
                System.out.println("\nIndique o email do utilizador: ");
                email_op=sc.nextLine();
                System.out.println("\nIndique o novo nome: ");
                nome_op=sc.nextLine();
                retorno=rf.atualizaOperador(email_op, nome_op);
                if(retorno==true){
                    System.out.println("\nOperador Atualizado com sucesso\n");
                }
                else{
                    System.out.println("\nErro na atualizacao do Operador\n");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;
        case 'D':
                try{
                    System.out.println("\nIntroduza o email a eliminar: "); /*CRITERIO UTILIZADO PARA APAGAR O OPERADOR FOI O SEU EMAIL (UNICO) PARA CDA UTILIZADOR*/
                    email_op=sc.nextLine();
                    retorno=rf.deleteOperador(email_op);
                    if(retorno==false){
                        System.out.println("\nNao foi possivel apagar o operador\n");
                    }
                    else{
                        System.out.println("\nOperador apagado com sucesso\n");
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            break;
        case 'S':
            try{
                List<OperadorDTO> retornoOP=rf.selectAllOP();
                for(OperadorDTO x : retornoOP){
                    System.out.println(x.toString());
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
  
  
    public static void interface_Partidas(){
     
    char c;
    String option;
    String user; 
    
    System.out.println("\n(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)electAll, (1)Select Partida pelo Nome");
    option=sc.next().toUpperCase(); 
    sc.skip("\n");
     
    if(option.length() >= 1){
        c = option.charAt(0);
    }else{
        c = ' ';
    }
    
    String cidade;
    PartidaDTO partida=null;
    boolean retorno;
    
    switch(c){
        
        case 'I':
            try{
                System.out.println("\nIntroduza a cidade de partida: ");
                cidade=sc.nextLine();
                partida=new PartidaDTO(cidade);
                retorno=rf.insertPartida(partida);
                if(retorno==true){
                    System.out.println("\nPartida inserida com sucesso\n");
                }
                else{
                    System.out.println("\nErro ao inserir a partida\n");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;  
        case 'U':
            try{
                System.out.println("\nNao sei ainda que parametro alterar, o nome nao faz sentido digo eu\n");
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;
        case 'D':
                try{
                    System.out.println("\nIntroduza a cidade: ");
                    cidade=sc.nextLine();
                    retorno=rf.deletePartida(cidade);
                    if(retorno==true){
                        System.out.println("\nPartida apagada com sucesso\n");
                    }
                    else{
                        System.out.println("\nErro ao apagar partida\n");
                    }
                }    
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            break;
        case 'S':
            try{
                List<PartidaDTO> retorno_part=rf.selectAllPartidas();
                if(retorno_part!=null){
                    for(PartidaDTO x : retorno_part){
                        System.out.println(x.toString());
                    }
                }
                else{
                    System.out.println("\nNao existem partidas\n");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;
        case '1':
            try{
                System.out.println("\nQual o nome da cidade de partida a procurar: ");
                cidade=sc.nextLine();
                PartidaDTO retornoPart=rf.selectPartida(cidade);
                if(retornoPart!=null){
                    System.out.println(retornoPart.toString());
                }
                else{
                    System.out.println("\nErro na procura da cidade de partida indicada\n");
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
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        getRemoteReferences();
        
        do{
            sigInOperador();
        }while(user_aceite!=true);
        
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
