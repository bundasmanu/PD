/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operadorcliente;

import dados.BeanRemotoRemote;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tpdtos.AgenciaDTO;
import tpdtos.AviaoDTO;
import tpdtos.BagagemDTO;
import tpdtos.BilheteDTO;
import tpdtos.ClienteDTO;
import tpdtos.CompanhiaDTO;
import tpdtos.DestinoDTO;
import tpdtos.OperadorDTO;
import tpdtos.PartidaDTO;
import tpdtos.PontuacaoDTO;
import tpdtos.ViagemDTO;

/**
 *
 * @author gustavo
 */
public class OperadorCliente {

    /**
     * @param args the command line arguments
     */
    static BeanRemotoRemote rf; //Remoto
    static Scanner sc = new Scanner(System.in);
    static boolean sair = false;
    static boolean user_aceite = false;
    static int estou_logado = 0;
    static String id = "";
    private static AgenciaDTO agencia = new AgenciaDTO("JJAJA");

    // propriedades
    public static void getRemoteReferences() {
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
        } catch (NamingException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

// adaptar
        String rsfull_class_name = "java:global/TP/TP-ejb/BeanRemoto!dados.BeanRemotoRemote";

        try {
            System.out.println("Iniciar lookup");
            rf = (BeanRemotoRemote) ctx.lookup(rsfull_class_name);
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sigInOperador() {

        char c;
        String option;

        System.out.println("\n(Q)uit, (S)igIn");
        option = sc.next().toUpperCase();
        sc.skip("\n");

        if (option.length() >= 1) {
            c = option.charAt(0);
        } else {
            c = ' ';
        }

        switch (c) {

            case 'S':
                try {
                    System.out.println("\nIntroduza o seu email: ");
                    String email = sc.nextLine();
                    System.out.println("\nIntroduza a sua pass");
                    String pass = sc.nextLine();
                    OperadorDTO d = new OperadorDTO(email, pass);
                    boolean existe = rf.encontrouOperador(d);
                    if (existe == true) {
                        user_aceite = true;/*USER ACEITE--> PODE PROSSEGUIR*/
                        System.out.println("\nPode prosseguir\n");
                    } else {
                        System.out.println("\nUtilizador nao foi encontrado, tente novamente\n");
    
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
        }
    }
 
  public static void interface_inicial(){
     
    char c;
    String option;
     
    System.out.println("\n(Q)uit, (C)ompanhia, (A)viao, (O)perador, (P)artidas, (1)Clientes, (2)Pontuacao, (V)iagens, (B)ilhete, (T)empo, (3)Bagagens, (D)estinos \n ");
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
        case '1':
            interface_cliente();
            break;
        case '2':
            interface_Pontuacao();
            break;
        case 'V':
            interface_Viagem();
            break;
        case 'B':
            interface_Bilhete();
            break;
        case 'T':
                System.out.println(rf.tempoAtual());
                rf.retificaIncrementoTempo(100);
            break;
        case '3':
            interface_Bagagens();
        break;
        case 'D':
            interface_Destinos();
        break;    
        case 'Q':
            sair=true;
            break;
        default:
            break;
    }
    
     
 }

    public static void interface_Companhia() {

        char c;
        String option;

        System.out.println("\n(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)elect Companhias\n");
        option = sc.next().toUpperCase();

        if (option.length() >= 1) {
            c = option.charAt(0);
        } else {
            c = ' ';
        }

        switch (c) {

            case 'I':
                try {
                    System.out.println("\nIntroduza o nome da companhia\n");
                    String nome_comp = sc.next();
                    CompanhiaDTO novaComp = new CompanhiaDTO(nome_comp);
                    boolean aconteceu = rf.insertCompanhia(novaComp);
                    if (aconteceu == true) {
                        System.out.println("\nInserida a companhia com sucesso\n");
                    } else {
                        System.out.println("\nOcorreu um problema\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'U':
                try {
                    System.out.println("\nIntroduza o nome da companhia a atualizar\n");
                    String nome_comp = sc.next();
                    System.out.println("\nIntroduza o novo nome da companhia\n");
                    String novo_nome = sc.next();
                    boolean retorno = rf.updateCompanhia(nome_comp, novo_nome);
                    if (retorno == false) {
                        System.out.println("\nErro na atualizacao\n");
                    } else {
                        System.out.println("\nCompanhia atualizada com sucesso\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'D':
                try {
                    System.out.println("\nQual o nome da companhia a apagar\n");
                    String nomc = sc.next();
                    CompanhiaDTO comp_apag = new CompanhiaDTO(nomc);
                    boolean deleteOP = rf.deleteCompanhia(comp_apag);
                    if (deleteOP == false) {
                        System.out.println("\nErro na Operacao\n");
                    } else {
                        System.out.println("\nOperacao bem sucedida\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'S':
                try {
                    List<CompanhiaDTO> lista_companhia = rf.selectList();
                    if (lista_companhia == null) {
                        System.out.println("\nErro na Operacao\n");
                    } else {
                        for (CompanhiaDTO x : lista_companhia) {
                            System.out.println(x.toString());
                        }
                    }
                } catch (Exception e) {
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

    public static void interface_Operador() {

        char c;
        String option;
        String user;

        System.out.println("\n(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)electAll");
        option = sc.next().toUpperCase();
        sc.skip("\n");

        if (option.length() >= 1) {
            c = option.charAt(0);
        } else {
            c = ' ';
        }

        String email_op;
        String nome_op;
        String pass_op;
        OperadorDTO d_op = null;
        boolean retorno;

        switch (c) {

            case 'I':
                try {
                    System.out.println("\nIntroduza o seu email: ");
                    email_op = sc.nextLine();
                    System.out.println("\nIntroduza o seu nome: ");
                    nome_op = sc.nextLine();
                    System.out.println("\nIntroduza a sua pass: ");
                    pass_op = sc.nextLine();
                    d_op = new OperadorDTO(nome_op, email_op, pass_op);
                    retorno = rf.insertOperador(d_op);
                    if (retorno == false) {
                        System.out.println("\nNao foi possivel inserir o operador\n");
                    } else {
                        System.out.println("\nOperador inserido com sucesso\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'U':
                try {
                    System.out.println("\nIndique o email do utilizador: ");
                    email_op = sc.nextLine();
                    System.out.println("\nIndique o novo nome: ");
                    nome_op = sc.nextLine();
                    retorno = rf.atualizaOperador(email_op, nome_op);
                    if (retorno == true) {
                        System.out.println("\nOperador Atualizado com sucesso\n");
                    } else {
                        System.out.println("\nErro na atualizacao do Operador\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'D':
                try {
                    System.out.println("\nIntroduza o email a eliminar: ");
                    /*CRITERIO UTILIZADO PARA APAGAR O OPERADOR FOI O SEU EMAIL (UNICO) PARA CDA UTILIZADOR*/
                    email_op = sc.nextLine();
                    retorno = rf.deleteOperador(email_op);
                    if (retorno == false) {
                        System.out.println("\nNao foi possivel apagar o operador\n");
                    } else {
                        System.out.println("\nOperador apagado com sucesso\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'S':
                try {
                    List<OperadorDTO> retornoOP = rf.selectAllOP();
                    for (OperadorDTO x : retornoOP) {
                        System.out.println(x.toString());
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'Q':
                sair = true;
                break;
            default:
                break;
        }

    }

    public static void interface_aviao() {

        char c;
        String option;
        String user;

        System.out.println("(Q)uit, (I)nsert, (D)elete, (U)pdate, (S)elect aviao\n");
        option = sc.next().toUpperCase();

        if (option.length() >= 1) {
            c = option.charAt(0);
        } else {
            c = ' ';
        }

        switch (c) {
            case 'I':
                try {
                    System.out.println("\nIntroduza o nome do aviao\n");
                    String nome_aviao = sc.next();
                    System.out.println("\nIntroduza o numero de lugares\n");
                    Integer num_lug = sc.nextInt();
                    System.out.println("\nIntroduza o nome da companhia\n");
                    String nome_comp = sc.next();
                    AviaoDTO aviao = new AviaoDTO(nome_aviao, num_lug);
                    boolean aconteceu = rf.insertAviao(aviao, nome_comp);
                    if (aconteceu == true) {
                        System.out.println("\nInserido o aviao com sucesso\n");
                    } else {
                        System.out.println("\nOcorreu um problema\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'D':
                try {
                    System.out.println("\nQual o id do aviao a apagar: ");
                    int noma = sc.nextInt();
                    boolean deleteAviao = rf.deleteAviao(noma);
                    if (deleteAviao == false) {
                        System.out.println("\nErro na Operacao\n");
                    } else {
                        System.out.println("\nOperacao bem sucedida\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'U':
                try {
                    System.out.println("\nQual o id do aviao a atualizar: ");
                    int nome_av = sc.nextInt();
                    System.out.println("\nQual o numero de lugares a atualizar para esse aviao: ");
                    int numlu = sc.nextInt();
                    //AviaoDTO avi_apag= new AviaoDTO(noma, numLugares);
                    boolean atualizaAviao = rf.updateAviao(nome_av, numlu);
                    if (atualizaAviao == false) {
                        System.out.println("\nErro na Operacao\n");
                    } else {
                        System.out.println("\nOperacao bem sucedida\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'S':
                try {
                    List<AviaoDTO> retav = rf.selectionaAviao();
                    if (retav.isEmpty() == false) {
                        for (AviaoDTO x : retav) {
                            System.out.println(x.toString());
                        }
                    } else {
                        System.out.println("\nNao existem avioes\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                break;
        }

    }

    public static void interface_Bagagens() {

        char c;
        String option;

        System.out.println("\n(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)elect All Bagagens\n");
        option = sc.next().toUpperCase();

        if (option.length() >= 1) {
            c = option.charAt(0);
        } else {
            c = ' ';
        }

        switch (c) {

            case 'I':
                try {
                    System.out.println("\nIntroduza o peso da babagem\n");
                    Integer peso_bagagem = sc.nextInt();
                    System.out.println("\nIntroduza o id da viagem\n");
                    Integer id_viagem = sc.nextInt();
                    System.out.println("\nIntroduza o id do cliente\n");
                    Integer id_cliente = sc.nextInt();
                    boolean verificaBagagemInserida = rf.insertBagagem(peso_bagagem, id_viagem, id_cliente);
                    if (verificaBagagemInserida == true) {
                        System.out.println("\nInserida a a bagagem com sucesso\n");
                    } else {
                        System.out.println("\nOcorreu um problema\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'U':
                try {
                    System.out.println("\nIntroduza o id da babagem a atualizar\n");
                    Integer id_bag = sc.nextInt();
                    System.out.println("\nIntroduza o novo peso da bagagem\n");
                    Integer novo_peso = sc.nextInt();
                    boolean retorno = rf.updateBagagem(id_bag, novo_peso);
                    if (retorno == false) {
                        System.out.println("\nErro na atualizacao\n");
                    } else {
                        System.out.println("\nBagagem atualizada com sucesso\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'D':
                try {
                    System.out.println("\nQual o id da bagagem a apagar\n");
                    Integer id_b = sc.nextInt();
                    boolean deleteBagagem = rf.deleteBagagem(id_b);
                    if (deleteBagagem == false) {
                        System.out.println("\nErro na Operacao\n");
                    } else {
                        System.out.println("\nOperacao bem sucedida\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'S':
                try {
                    List<BagagemDTO> lista_bagagens = rf.selecionaAllBagagens();
                    if (lista_bagagens == null) {
                        System.out.println("\nErro na Operacao\n");
                    } else {
                        for (BagagemDTO x : lista_bagagens) {
                            System.out.println(x.toString());
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'Q':
                sair = true;
                break;
            default:
                break;
        }

    }

    public static void interface_Partidas() {

        char c;
        String option;
        String user;

        System.out.println("\n(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)electAll, (1)Select Partida pelo Nome");
        option = sc.next().toUpperCase();
        sc.skip("\n");

        if (option.length() >= 1) {
            c = option.charAt(0);
        } else {
            c = ' ';
        }

        String cidade;
        PartidaDTO partida = null;
        boolean retorno;

        switch (c) {

            case 'I':
                try {
                    System.out.println("\nIntroduza a cidade de partida: ");
                    cidade = sc.nextLine();
                    partida = new PartidaDTO(cidade);
                    retorno = rf.insertPartida(partida);
                    if (retorno == true) {
                        System.out.println("\nPartida inserida com sucesso\n");
                    } else {
                        System.out.println("\nErro ao inserir a partida\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'U':
                try {
                    System.out.println("\nNao sei ainda que parametro alterar, o nome nao faz sentido digo eu\n");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'D':
                try {
                    System.out.println("\nIntroduza a cidade: ");
                    cidade = sc.nextLine();
                    retorno = rf.deletePartida(cidade);
                    if (retorno == true) {
                        System.out.println("\nPartida apagada com sucesso\n");
                    } else {
                        System.out.println("\nErro ao apagar partida\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'S':
                try {
                    List<PartidaDTO> retorno_part = rf.selectAllPartidas();
                    if (retorno_part != null) {
                        for (PartidaDTO x : retorno_part) {
                            System.out.println(x.toString());
                        }
                    } else {
                        System.out.println("\nNao existem partidas\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case '1':
                try {
                    System.out.println("\nQual o nome da cidade de partida a procurar: ");
                    cidade = sc.nextLine();
                    PartidaDTO retornoPart = rf.selectPartida(cidade);
                    if (retornoPart != null) {
                        System.out.println(retornoPart.toString());
                    } else {
                        System.out.println("\nErro na procura da cidade de partida indicada\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'Q':
                sair = true;
                break;
            default:
                break;
        }

    }

    public static void interface_Destinos() {

        char c;
        String option;
        String user;

        System.out.println("\n(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)electAll, (1)Select Destino pelo Nome");
        option = sc.next().toUpperCase();
        sc.skip("\n");

        if (option.length() >= 1) {
            c = option.charAt(0);
        } else {
            c = ' ';
        }

        String cid;
        DestinoDTO destino = null;
        boolean retorno;

        switch (c) {

            case 'I':
                try {
                    System.out.println("\nIntroduza a cidade de destino: ");
                    cid = sc.next();
                    destino = new DestinoDTO(cid);
                    retorno = rf.insereDestino(destino);
                    if (retorno == true) {
                        System.out.println("\nDestino inserido com sucesso\n");
                    } else {
                        System.out.println("\nErro ao inserir a partida\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'U':
                try {
                    System.out.println("\nNao sei ainda que parametro alterar, o nome nao faz sentido digo eu\n");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'D':
                try {
                    System.out.println("\nIntroduza a cidade: ");
                    cid = sc.nextLine();
                    retorno = rf.deleteDestino(cid);
                    if (retorno == true) {
                        System.out.println("\nDestino apagado com sucesso\n");
                    } else {
                        System.out.println("\nErro ao apagar destino\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'S':
                try {
                    List<DestinoDTO> retorno_dest = rf.selectAllDestinos();
                    if (retorno_dest != null) {
                        for (DestinoDTO x : retorno_dest) {
                            System.out.println(x.toString());
                        }
                    } else {
                        System.out.println("\nNao existem destinos\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case '1':
                try {
                    System.out.println("\nQual o nome da cidade de destino a procurar: ");
                    cid = sc.nextLine();
                    DestinoDTO retornoDest = rf.selectDestino(cid);
                    if (retornoDest != null) {
                        System.out.println(retornoDest.toString());
                    } else {
                        System.out.println("\nErro na procura da cidade de destino indicada\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'Q':
                sair = true;
                break;
            default:
                break;
        }

    }

    public static void interface_cliente() {

        char c;
        String option;

        System.out.println("\n(Q)uit, (I)nsert, (D)elete, (U)pdate, (S)elect All, (V)alida Login, (1)Select, (2)Update Conta : ");
        option = sc.next().toUpperCase();
        sc.skip("\n");

        if (option.length() >= 1) {
            c = option.charAt(0);
        } else {
            c = ' ';
        }

        String nome;
        String email;
        String pass;
        ClienteDTO cli = null;
        boolean retorno = false;

        switch (c) {

            case 'I':
                try {
                    System.out.println("\nIntroduza o seu email: ");
                    email = sc.nextLine();
                    System.out.println("\nIntroduza o seu nome: ");
                    nome = sc.nextLine();
                    System.out.println("\nIntroduza a sua pass: ");
                    pass = sc.nextLine();
                    cli = new ClienteDTO(nome, email);
                    retorno = rf.insertCliente(cli, pass);
                    if (retorno == true) {
                        System.out.println("\nCliente inserido com sucesso\n");
                    } else {
                        System.out.println("\nErro na inserção do cliente\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'D':
                try {
                    System.out.println("\nIntroduza o seu email: ");
                    email = sc.nextLine();
                    retorno = rf.deleteCliente(email);
                    /*ANTES DE APAGAR NA PARTE WEB ELE VERIFICA TAMBEM A PASS, CASO ESTEJA SUCESSO, SO PRECISA DE PASSAR O EMAIL PARA ELIMINAR O CLIENTE*/
                    if (retorno == true) {
                        System.out.println("\nCliente apagado com sucesso\n");
                    } else {
                        System.out.println("\nErro ao apagar o cliente\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'U':
                try {
                    System.out.println("\nIntroduza o email do cliente que pretende atualizar: ");
                    email = sc.nextLine();
                    System.out.println("\nIntroduza a nova pass deste cliente: ");
                    pass = sc.nextLine();
                    retorno = rf.updateCliente(email, pass);
                    if (retorno == true) {
                        System.out.println("\nCliente atualizado com sucesso\n");
                    } else {
                        System.out.println("\nErro ao atualizar o clieente\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'S':
                try {
                    List<ClienteDTO> retorno_select_clientes = rf.selectAllClientes();
                    if (retorno_select_clientes.isEmpty() == false) {
                        for (ClienteDTO x : retorno_select_clientes) {
                            System.out.println(x.toString());
                        }
                    } else {
                        System.out.println("\nNao existem ainda clientes\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'V':
                try {
                    System.out.println("\nIntroduza o email do cliente: ");
                    email = sc.nextLine();
                    System.out.println("\nIntroduza a pass do cliente: ");
                    pass = sc.nextLine();
                    retorno = rf.verificaLogin(email, pass);
                    if (retorno == true) {
                        System.out.println("\nO cliente existe\n");
                    } else {
                        System.out.println("\nO cliente não existe\n");
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            break;
        case '1':
            try{
                System.out.println("\nIntroduza o email do cliente: ");
                email=sc.nextLine();
                cli=rf.selectCliente(email);
                if(cli!=null){
                    System.out.println(cli.toString());
                }
                else{
                    System.out.println("\nO cliente nao existe\n");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;
        case '2':
            try{
                System.out.println("\nQual o email do cliente: ");
                email=sc.nextLine();
                System.out.println("\nIntroduza o valor a depositar: ");
                int valor=sc.nextInt();
                retorno=rf.updateContaCliente(email, valor);
                if(retorno==true){
                    System.out.println("\nDeposito efetuado com sucesso\n");
                }
                else{
                    System.out.println("\nErro ao efetuar o deposito\n");
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
    
  public static void interface_Pont_Comp(){
     
    char c;
    String option;
    String user; 
    
    System.out.println("\n(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)electAll: ");
    option=sc.next().toUpperCase();  
    sc.skip("\n");
     
    if(option.length() >= 1){
        c = option.charAt(0);
    }else{
        c = ' ';
    }
    
    String emailCli;
    String nomeComp;
    int idPont;
    int idCli;
    int valorPont;
    PontuacaoDTO pont=null;
    boolean retorno;
    
    switch(c){
        
        case 'I':
            try{
                System.out.println("\nIntroduza o email do cliente: ");
                emailCli=sc.nextLine();
                System.out.println("\nIntroduza o nome da companhia: ");
                nomeComp=sc.nextLine();
                System.out.println("\nIntroduza o valor da pontuacao: ");
                valorPont=sc.nextInt();
                retorno=rf.insertPontComp(valorPont, emailCli, nomeComp);
                if(retorno==true){
                    System.out.println("\nPontuacao inserida com sucesso\n");
                }
                else{
                    System.out.println("\nErro na insercao da pontuacao\n");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;  
        case 'U':
            try{
                System.out.println("\nIntroduza o id da pontuacao a alterar: ");
                idPont=sc.nextInt();
                System.out.println("\nIntroduza o novo valor da pontuacao: ");
                valorPont=sc.nextInt();
                retorno=rf.updatePontComp(idPont, valorPont);
                if(retorno==true){
                    System.out.println("\nPontuacao atualizada com sucesso\n");
                }
                else{
                    System.out.println("\nErro ao atualizar a pontuacao\n");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;
        case 'D':
            try {
                System.out.println("\nIntroduza o id da pontuacao a eliminar");
                idPont = sc.nextInt();
                retorno = rf.deletePontComp(idPont);
                if (retorno == true) {
                    System.out.println("\nPontuacao apagada com sucesso\n");
                } else {
                    System.out.println("\nErro ao apagar a pontuacao\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        case 'S':
            try{
                System.out.println("\nIntroduza o id do cliente");
                idCli = sc.nextInt();
                List<PontuacaoDTO> pontuacoes_cli=rf.selectAllClientPont(idCli);
                if(pontuacoes_cli.isEmpty()==false){
                    for(PontuacaoDTO x : pontuacoes_cli){
                        System.out.println(x.toString());
                    }
                }
            } 
            catch (Exception e) {
                    System.out.println(e.getMessage());
            }
            break;
            case 'Q':
                sair = true;
                break;
            default:
                break;
        }

    }

    public static void interface_Pontuacao() {

        char c;
        String option;
        String user;

        System.out.println("\n(Q)uit, (C)ompanhias, (P)artidas, (D)estinos");
        option = sc.next().toUpperCase();
        sc.skip("\n");

        if (option.length() >= 1) {
            c = option.charAt(0);
        } else {
            c = ' ';
        }

        switch (c) {

            case 'C':
                try {
                    interface_Pont_Comp();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'P':
                try {
                    interface_Pont_Part();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'D':
                try {
                    interface_Pont_Dest();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'Q':
                sair = true;
                break;
            default:
                break;
        }

    }

    public static void interface_Pont_Dest() {

        char c;
        String option;
        String user;

        System.out.println("\n(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)electAll: ");
        option = sc.next().toUpperCase();
        sc.skip("\n");

        if (option.length() >= 1) {
            c = option.charAt(0);
        } else {
            c = ' ';
        }

        String emailCli;
        String nomeDest;
        int idPont;
        int idCli;
        int valorPont;
        PontuacaoDTO pont = null;
        boolean retorno;

        switch (c) {

            case 'I':
                try {
                    System.out.println("\nIntroduza o email do cliente: ");
                    emailCli = sc.nextLine();
                    System.out.println("\nIntroduza o nome do destino: ");
                    nomeDest = sc.nextLine();
                    System.out.println("\nIntroduza o valor da pontuacao: ");
                    valorPont = sc.nextInt();
                    retorno = rf.insertPontDestino(valorPont, emailCli, nomeDest);
                    if (retorno == true) {
                        System.out.println("\nPontuacao do destino inserida com sucesso\n");
                    } else {
                        System.out.println("\nErro na insercao da pontuacao do destino\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'U':
                try {
                    System.out.println("\nIntroduza o id da pontuacao a alterar: ");
                    idPont = sc.nextInt();
                    System.out.println("\nIntroduza o novo valor da pontuacao: ");
                    valorPont = sc.nextInt();
                    retorno = rf.updatePontDestino(idPont, valorPont);
                    if (retorno == true) {
                        System.out.println("\nPontuacao do destino atualizada com sucesso\n");
                    } else {
                        System.out.println("\nErro ao atualizar a pontuacao do destino\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'D':
                try {
                    System.out.println("\nIntroduza o id da pontuacao a eliminar");
                    idPont = sc.nextInt();
                    retorno = rf.deletePontDestino(idPont);
                    if (retorno == true) {
                        System.out.println("\nPontuacao do destino apagada com sucesso\n");
                    } else {
                        System.out.println("\nErro ao apagar a pontuacao do destino\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'S':
                try {
                    System.out.println("\nIntroduza o email do cliente");
                    emailCli = sc.next();
                    List<PontuacaoDTO> pontuacoes_cli = rf.selectAllPontuacoesDestino(emailCli);
                    if (pontuacoes_cli.isEmpty() == false) {
                        for (PontuacaoDTO x : pontuacoes_cli) {
                            System.out.println(x.toString());
                        }
                    } else {
                        System.out.println("\nAinda nao efetou nenhumas pontuacoes\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'Q':
                sair = true;
                break;
            default:
                break;
        }

    }
  
  
  public static void interface_Pont_Part(){
     
    char c;
    String option;
    String user; 
    
    System.out.println("\n(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)electAll: ");
    option=sc.next().toUpperCase();  
    sc.skip("\n");
     
    if(option.length() >= 1){
        c = option.charAt(0);
    }else{
        c = ' ';
    }
    
    String emailCli;
    String nomePart;
    int idPont;
    int idCli;
    int valorPont;
    PontuacaoDTO pont=null;
    boolean retorno;
    
    switch(c){
        
        case 'I':
            try{
                System.out.println("\nIntroduza o email do cliente: ");
                emailCli=sc.nextLine();
                System.out.println("\nIntroduza o nome da partida: ");
                nomePart=sc.nextLine();
                System.out.println("\nIntroduza o valor da pontuacao: ");
                valorPont=sc.nextInt();
                retorno=rf.insertPontPartida(valorPont, emailCli, nomePart);
                if(retorno==true){
                    System.out.println("\nPontuacao inserida com sucesso\n");
                }
                else{
                    System.out.println("\nErro na insercao da pontuacao\n");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;  
        case 'U':
            try{
                System.out.println("\nIntroduza o id da pontuacao a alterar: ");
                idPont=sc.nextInt();
                System.out.println("\nIntroduza o novo valor da pontuacao: ");
                valorPont=sc.nextInt();
                retorno=rf.updatePontComp(idPont, valorPont);
                if(retorno==true){
                    System.out.println("\nPontuacao atualizada com sucesso\n");
                }
                else{
                    System.out.println("\nErro ao atualizar a pontuacao\n");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;
        case 'D':
            try {
                System.out.println("\nIntroduza o id da pontuacao a eliminar");
                idPont = sc.nextInt();
                retorno = rf.deletePontComp(idPont);
                if (retorno == true) {
                    System.out.println("\nPontuacao apagada com sucesso\n");
                } else {
                    System.out.println("\nErro ao apagar a pontuacao\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        case 'S':
            try{
                System.out.println("\nIntroduza o id do cliente");
                idCli = sc.nextInt();
                List<PontuacaoDTO> pontuacoes_cli=rf.selectAllClientPont(idCli);
                if(pontuacoes_cli.isEmpty()==false){
                    for(PontuacaoDTO x : pontuacoes_cli){
                        System.out.println(x.toString());
                    }
                }
                else{
                    System.out.println("\nAinda nao efetou nenhumas pontuacoes\n");
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

    public static void interface_Bilhete() {

        char c;
        String option;
        String user;

        System.out.println("\n(Q)uit, (I)nsert, (U)pdate by Aviao, (D)elete, (S)elect, (1)Select All, (2)Selecionar todos bilhetes de um cliente");
        option = sc.next().toUpperCase();
        sc.skip("\n");

        if (option.length() >= 1) {
            c = option.charAt(0);
        } else {
            c = ' ';
        }

        int preco;
        int codigo_bil;
        int codigo_cli;
        int codigo_viagem;
        int codig_viagem;
        BilheteDTO bilhete = null;
        boolean retorno = false;

        switch (c) {

            case 'I':
                try {
                    System.out.println("\nQual o id do cliente: ");
                    codigo_cli = sc.nextInt();
                    System.out.println("\nQual o id da viagem: ");
                    codig_viagem = sc.nextInt();
                    retorno = rf.insertBilhete(codig_viagem, codigo_cli);
                    if (retorno == true) {
                        System.out.println("\nBilhete inserido com sucesso");
                    } else {
                        System.out.println("\nErro ao inserir o bilhete\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'D':
                try {
                    System.out.println("\nQual o id do bilhete: ");
                    codigo_bil = sc.nextInt();
                    retorno = rf.deleteBilhete(codigo_bil);
                    if (retorno == true) {
                        System.out.println("\nBilhete apagado com sucesso\n");
                    } else {
                        System.out.println("\nErro ao apagar o bilhete\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'U':
                try {

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'S':
                try {
                    System.out.println("\nQual o id do bilhete: ");
                    codigo_bil = sc.nextInt();
                    bilhete = rf.selectBilhete(codigo_bil);
                    if (bilhete != null) {
                        System.out.println(bilhete.toString());
                    } else {
                        System.out.println("\nErro na seleccao do cliente\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case '1':
                try {
                    List<BilheteDTO> bilhetes = rf.selectAllBilhetes();
                    if (bilhetes != null) {
                        for (BilheteDTO x : bilhetes) {
                            System.out.println(x.toString());
                        }
                    } else {
                        System.out.println("\nAinda nao existem bilhetes\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case '2':
                int cod=0;
                int num_bilhete=0;
                try{
                    System.out.println("\nQual o id do cliente para a qual pretende ver os bilhetes");
                    
                    int cod_cli= sc.nextInt();
                    List<BilheteDTO> lista_bilhete= rf.selectAllBilhetesFromACliente(cod_cli);
                    if(lista_bilhete!=null){
                        for(int i=0;i<lista_bilhete.size();i++){
                            cod=++num_bilhete;
                            System.out.println("Bilhete nº: "+cod);
                            System.out.println("Lugar: "+lista_bilhete.get(i).getLugar()+"\t"+ "Id da viagem:" +lista_bilhete.get(i).getViagem().getId());
                        }
                    }
                    else{
                        System.out.println("Erro na operacao");
                    }
                }
                catch(Exception e){
                    System.out.println(""+e.getMessage());
                   
                }
            case 'Q':
                sair = true;
                break;
            default:
                break;
        }

    }

    public static void interface_Viagem() {

        char c;
        String option;
        String user;

        System.out.println("\n(Q)uit, (I)nsert, (U)pdate, (D)elete, (S)elect, (1)Select All,(R)emove todas as viagens apos hora terminar, (G)et Viagem Inversa");
        option = sc.next().toUpperCase();
        sc.skip("\n");

        if (option.length() >= 1) {
            c = option.charAt(0);
        } else {
            c = ' ';
        }

        int num_lugares;
        int hora_c;
        int hora_part;
        int hora_cheg;
        int id_aviao;
        int id_partida;
        int id_chegada;
        int id_viagem;
        int preco;
        ViagemDTO viagem = null;
        boolean retorno = false;

        switch (c) {

            case 'I':
                try {
                    System.out.println("\nHora de Partida: ");
                    hora_part = sc.nextInt();
                    System.out.println("\nHora de Chegada: ");
                    hora_cheg = sc.nextInt();
                    System.out.println("\nQual o id do aviao: ");
                    id_aviao = sc.nextInt();
                    System.out.println("\nQual o id da cidade de partida: ");
                    id_partida = sc.nextInt();
                    System.out.println("\nQual o id da cidade de chegada: ");
                    id_chegada = sc.nextInt();
                    System.out.println("\nQual o preco da viagem: ");
                    preco = sc.nextInt();
                    retorno = rf.insertViagem(hora_part, hora_cheg, id_aviao, id_partida, id_chegada,preco);
                    if (retorno == true) {
                        System.out.println("\nViagem inserida com sucesso\n");
                    } else {
                        System.out.println("\nErro na insercao da viagem\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'R':
                try {
                    /*System.out.println("\nQual a hora de chegada: ");
                    hora_c = sc.nextInt();
                    boolean status = rf.removeTodasViagensAposHoraTerminar(hora_c);
                    if (status == true) {
                        System.out.println("\nRemovidas todas as viagem com hora de chegada superior a passada como parametro");
                    } else {
                        System.out.println("\nFalha ao remover todas as viagens");
                    }*/
                } catch (Exception e) {
                    System.out.println("" + e.getMessage());
                }
                break;
            case 'D':
                try {
                    System.out.println("\nQual o id da Viagem: ");
                    id_viagem = sc.nextInt();
                    retorno = rf.deleteViagem(id_viagem);
                    if (retorno == true) {
                        System.out.println("\nViagem apagada com sucesso\n");
                    } else {
                        System.out.println("\nErro ao apagar a viagem\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'U':
                try {
                    System.out.println("\nQual o id da Viagem: ");
                    id_viagem = sc.nextInt();
                    System.out.println("\nQual o id do novo aviao: ");
                    id_aviao = sc.nextInt();
                    retorno = rf.updateViagembyAviao(id_viagem, id_aviao);
                    if (retorno == true) {
                        System.out.println("\nViagem atualizada com sucesso\n");
                    } else {
                        System.out.println("\nErro ao atualizar a viagem\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'S':
                try {
                    System.out.println("\nQual o id da Viagem: ");
                    id_viagem = sc.nextInt();
                    viagem = rf.selectViagem(id_viagem);
                    if (viagem != null) {
                        System.out.println(viagem.toString());
                    } else {
                        System.out.println("\nErro ao seleccionar a viagem\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case '1':
                try {
                    List<ViagemDTO> viagens = rf.selectAllViagens();
                    if (viagens != null) {
                        for (ViagemDTO x : viagens) {
                            System.out.println(x.toString());
                        }
                    } else {
                        System.out.println("\nNao existem viagens\n");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 'G':
                try{
                    viagem=rf.selectViagemInversa(12);
                    if(viagem!=null){
                        System.out.println(viagem.getId()+"\t"+viagem.getPart().getCidade()+"\t"+viagem.getDest().getCidade());
                    }
                    else{
                        System.out.println("\nNao existe viagem inversa\n");
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                
                break;
            case 'Q':
                sair = true;
                break;
            default:
                break;
        }

    }

    public static void main(String[] args) {
        // TODO code application logic here

        getRemoteReferences();

        do {
            sigInOperador();
        } while (user_aceite != true);

        while (sair != true) {
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
