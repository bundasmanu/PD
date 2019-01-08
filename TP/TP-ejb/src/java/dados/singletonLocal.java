/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;
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
@Singleton

public class singletonLocal implements singletonLocalLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    CompanhiaFacadeLocal companhia;

    @EJB
    OperadorFacadeLocal operador;

    @EJB
    PartidasFacadeLocal partidas;

    @EJB
    DestinosFacadeLocal destino;

    @EJB
    AviaoFacadeLocal aviao;

    @EJB
    ClienteFacadeLocal cliente;

    @EJB
    PontuacaoFacadeLocal pontuacao;

    @EJB
    ViagensFacadeLocal viagens;

    @EJB
    BilheteFacadeLocal bilhete;
    
    @EJB
    DestinosFacadeLocal destino;
    
    private static final Logger LOGGER = Logger.getLogger( singletonLocal.class.getName() );
    
    @EJB 
    LogsSendQueueBeanLocal logs;
    

    @EJB
    BagagensFacadeLocal bagagem;

    @Override
    public String showOla() {
        return "Ola";
    }
 
    @Override
    public boolean insertCompanhia(CompanhiaDTO d) {

        try {
            Companhia novaCompanhia = new Companhia(this.companhia.count() + 1, d.getNome());
            //novaCompanhia.setIdAgencia();
            this.companhia.create(novaCompanhia);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizaCompanhia(String nomeComp, String novoNome) {

        /*PROCURA COMPANHIA*/
        try {
            Companhia estado = this.companhia.findbyName(nomeComp);

            if (estado == null) {
                return false;
            }

            /*CASO EXISTA OBJETO NA BD*/
            estado.setNomeCompanhia(novoNome);
            this.companhia.edit(estado);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean apagaCompanhia(CompanhiaDTO d) {

        /*PROCURA INICIALMENTE A COMPANHIA*/
        try {
            Companhia c = this.companhia.findbyName(d.getNome());

            if (c == null) {
                return false;
            }

            /*SENAO APAGA A COMPANHIA*/
            this.companhia.remove(c);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public CompanhiaDTO selectCompanhia(String nome_c) {

        /*RECEBE NOME DA COMPANHIA*/
        try {

            Companhia comp = this.companhia.findbyName(nome_c);

            if (comp == null) {
                return null;
            }

            /*CASO EXISTA RETORNA UMA COMPANHIA DTO*/
            CompanhiaDTO rComp = new CompanhiaDTO(comp.getNomeCompanhia(), comp.getPontuacaoMedia());

            if (comp.getAviaoCollection().isEmpty() == false) {
                for (Aviao x : comp.getAviaoCollection()) {
                    rComp.getAvioes().add(new AviaoDTO(x.getNomeAviao(), x.getNumLugares()));
                }
            }

            if (comp.getPontuacaoCollection().isEmpty() == false) {
                for (Pontuacao x : comp.getPontuacaoCollection()) {
                    rComp.getPontuacoes().add(new PontuacaoDTO(x.getValor()));
                }
            }

            return rComp;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<CompanhiaDTO> selectAll() {

        /*FIND ALL TODAS AS COMPANHIAS*/
        List<CompanhiaDTO> lista_c_dto = null;
        try {
            List<Companhia> lista_comp = this.companhia.findAll();

            if (lista_comp == null) {
                return null;
            }

            /*CONVERSAO DE COMPANHIA PARA COMPANHIA DTO*/
            lista_c_dto = new ArrayList<CompanhiaDTO>();
            for (Companhia x : lista_comp) {
                CompanhiaDTO nova = this.selectCompanhia(x.getNomeCompanhia());
                /*PODE VIR A SER NECESSARIO COLOCAR OS AVIOES E AS PONTUACOES DENTRO DE CADA COMPANHIA*/

                lista_c_dto.add(nova);
            }
        } catch (Exception e) {
            return null;
        }
        return lista_c_dto;
    }

    @Override
    public boolean encontrouOperador(OperadorDTO d) {

        try {

            /*VERIFICAR SE O OPERADOR EXISTE*/
            Operador encontrou = operador.findbyName(d.getMail());

            if (encontrou == null) {
                return false;
            }

            /*JA SABEMOS QUE O EMAIL COINCIDE, AGORA VAMOS VERIFICAR A PASS*/
            if (encontrou.getPassOperador().equals(d.getPass()) != true) {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean insereOperador(OperadorDTO d) {

        try {
            /*VERIFICAR INICIALMENTE SE NAO EXISTEM OPERADORES REGISTADOS COM O MSM EMAIL*/
            Operador ja_existe = operador.findbyName(d.getMail());

            /*SENAO EXISTIR NENHUM UTILIZADOR REGISTADO COM O MESMO EMAIL ENTAO INSIRO-O NA BD*/
            if (ja_existe == null) {
                Operador novoOp = new Operador(this.operador.count() + 1, d.getNome(), d.getMail(), d.getPass());
                this.operador.create(novoOp);
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    @Override
    public boolean apagaOperador(String email) {

        try {

            /*VERIFICAR INICIALMENTE SE EXISTE O OPERADOR A SER ELIMINADO*/
            Operador apagar = this.operador.findbyName(email);

            /*SE NAO EXISTIR, NAO POSSO APAGAR*/
            if (apagar == null) {
                return false;
            }

            /*SE EXISTIR APAGO O OPERADOR*/
            this.operador.remove(apagar);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizaOp(String email, String novoNome) {

        try {

            /*VERIFICAR INICIALMENTE SE EXISTE OPERADOR QUE SE PRETENDE ATUALIZAR*/
            Operador retornoOP = this.operador.findbyName(email);
            if (retornoOP == null) {
                return false;
                /*NAO EXISTE*/
            }

            /*CASO EXISTA, DEFINO O SEU NOVO NOME E EDITO O OBJETO NA BD*/
            retornoOP.setNomeOperador(novoNome);

            this.operador.edit(retornoOP);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public List<OperadorDTO> selectAllOPS() {

        /*RETORNO DE TODOS OS OPERADORES*/
        try {

            List<Operador> operadores = this.operador.findAll();

            if (operadores.isEmpty() == true) {
                return null;
            }

            /*CRIO LISTA DE OPERADORES DTO*/
            List<OperadorDTO> retornoAllOperadores = new ArrayList<OperadorDTO>();

            for (Operador x : operadores) {
                OperadorDTO op = new OperadorDTO(x.getNomeOperador(), x.getEmailOperador(), x.getPassOperador());
                retornoAllOperadores.add(op);
            }

            return retornoAllOperadores;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean inserePartida(PartidaDTO part) {

        /*NAO PODEM EXISTIR CIDADES COM O MESMO NOME*/
        try {

            Partidas p = this.partidas.findbyName(part.getCidade());

            /*VERIFICA SE JA EXISTE UMA PARTIDA COM A MESMA CIDADE, SE JA EXISTE NAO ACRESCENTA OUTRA*/
            if (p != null) {
                return false;
            }

            /*CASO AINDA NAO EXISTA, INSERE NA BD*/
            p = new Partidas(this.partidas.count() + 1, part.getCidade(), 0);/*VARIAVEL P SE CHEGAR AQUI ESTA A NULL, POSSO UTILIZA-LA*/
            this.partidas.create(p);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean apagaPartida(String cidade) {

        /*VERIFICAR INICIALMENTE SE A PARTIDA EXISTE*/
        try {

            Partidas part = this.partidas.findbyName(cidade);

            /*SENAO EXISTE NAO PODE APAGAR*/
            if (part == null) {
                return false;
            }

            /*CASO EXISTA APAGA A PARTIDA*/
            this.partidas.remove(part);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public List<PartidaDTO> seleccionaAllPartidas() {

        try {

            List<Partidas> retorno_partidas = this.partidas.findAll();

            if (retorno_partidas.isEmpty() == true) {
                return null;
            }

            List<PartidaDTO> part = new ArrayList<PartidaDTO>();
            for (Partidas x : retorno_partidas) {
                part.add(new PartidaDTO(x.getCidadePartida(), x.getPontMedia()));
            }

            return part;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public PartidaDTO seleccionaPartida(String cidade) {

        /*CASO ENCONTRE TENHO DE COLOCAR TODOS OS DADOS DA PARTIDA RETORNADA NA PARTIDA DTO*/
        try {

            /*VERIFICAR SE A PARTIDA EXISTE*/
            Partidas p = this.partidas.findbyName(cidade);

            if (p == null) {
                return null;
            }

            /*CASO EXISTA-->DEFINIR TODOS OS SEUS DADOS*/
            PartidaDTO partSeleccionada = new PartidaDTO(p.getCidadePartida(), p.getPontMedia());

            /*COLOCAR AS VIAGENS DA PARTIDA NA PARTIDA DTO*/
            if (p.getViagensCollection().isEmpty() == false) {
                for (Viagens x : p.getViagensCollection()) {
                    partSeleccionada.getViagens().add(new ViagemDTO( x.getHoraPartida(), x.getHoraChegada()));
                }
            }

            /*COLOCAR AS PONTUACOES DA PARTIDA NA PARTIDA DTO*/
            if (p.getPontuacaoCollection().isEmpty() == false) {
                for (Pontuacao x : p.getPontuacaoCollection()) {
                    partSeleccionada.getPontuacoes().add(new PontuacaoDTO(x.getValor()));
                }
            }

            return partSeleccionada;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public boolean insertAviao(AviaoDTO av, String nome_companhia) {

        Companhia encontrou = companhia.findbyName(nome_companhia);
        if (encontrou == null) {
            return false;
        }
        try {
            Aviao v = new Aviao(this.aviao.count() + 1, av.getNomeAviao(), av.getNum_lugares(), encontrou);
            this.aviao.create(v);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAviao(int id) {

        Aviao encontrado = aviao.find(id);
        if (encontrado == null) {
            return false;
        }

        aviao.remove(encontrado);

        return true;
    }

    @Override
    public boolean updateAviao(int id, Integer novo_num_lugares) {
        try {
            Aviao av = aviao.find(id);
            if (av == null) {
                return false;
            }

            av.setNumLugares(novo_num_lugares);
            AviaoDTO aviaodto = new AviaoDTO(av.getNomeAviao(), av.getNumLugares());
            aviao.edit(av);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public List<AviaoDTO> selectionaAviao() {

        try {
            List<Aviao> av = aviao.findAll();
            if (av.isEmpty() == true) {
                return null;
            }
            List<AviaoDTO> aviaor = new ArrayList<AviaoDTO>();
            for (Aviao x : av) {
                AviaoDTO avt = new AviaoDTO(x.getNomeAviao(), x.getNumLugares());
                aviaor.add(avt);
            }

            return aviaor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean insereCliente(ClienteDTO c, String pass) {

        try {

            /*VERIFICAR PRIMEIRO SE JÁ EXISTE ALGUEM COM O EMAIL PASSADO POR PARAMETRO*/
            Cliente existe = this.cliente.findbyEmail(c.getEmail());

            if (existe != null) {
                return false;
            }

            existe = new Cliente(this.cliente.count() + 1, c.getNome(), pass, c.getEmail());
            this.cliente.create(existe);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean apagaCliente(String email) {

        try {

            /*VERIFCAR SE EXISTE A PESSOA A ELIMINAR*/
            Cliente cl = this.cliente.findbyEmail(email);

            if (cl == null) {
                return false;
            }

            this.cliente.remove(cl);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizaCliente(String email, String nova_pass) {

        try {

            /*VERIFICAR SE O CLIENTE EXISTE*/
            Cliente retorno = this.cliente.findbyEmail(email);

            if (retorno == null) {
                return false;
            }

            retorno.setPassCliente(nova_pass);
            this.cliente.edit(retorno);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<ClienteDTO> seleccionaAllClientes() {

        try {

            /*RETORNAR TODOS OS CLIENTES*/
            List<Cliente> retornoCli = this.cliente.findAll();

            if (retornoCli.isEmpty() == true) {
                return null;
            }

            List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
            for (Cliente x : retornoCli) {
                clientes.add(new ClienteDTO(x.getNomeCliente(), x.getEmailCliente()));
            }

            return clientes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean validaLogin(String email, String pass) {

        try {

            /*VERIFICA SE EXISTE ALGUM USER COM O EMAIL INDICADO, SE EXISTIR É SO UM, SO PODE EXISTIR UM UTILIZADOR LOGADO COM O MSM EMAIL*/
            Cliente retorno_cliente = this.cliente.findbyEmail(email);

            /*SENAO EXISTE CLIENTE*/
            if (retorno_cliente == null || (retorno_cliente != null && retorno_cliente.getPassCliente().equals(pass) == false)) {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public ClienteDTO seleccionaCliente(String email) {

        try {

            /*VERIFICAR SE O CLIENTE EXISTE*/
            Cliente retorno_cliente = this.cliente.findbyEmail(email);

            if (retorno_cliente == null) {
                return null;
            }

            /*SE EXISTIR*/
            ClienteDTO cli = new ClienteDTO(retorno_cliente.getNomeCliente(), retorno_cliente.getEmailCliente());

            /*TRANSFORMAR OS BILHETES, BAGAGENS E PONTUACOES E ADICIONAR AO CLIENTE DTO*/
            if (retorno_cliente.getBagagensCollection().isEmpty() == false) {
                for (Bagagens x : retorno_cliente.getBagagensCollection()) {
                    BagagemDTO bag = new BagagemDTO(x.getPesoBagagens());
                    ViagemDTO viag = new ViagemDTO(x.getIdViagens().getHoraPartida(), x.getIdViagens().getHoraChegada());
                    bag.setViagem(viag);
                    cli.getBagagens().add(bag);
                }
            }

            if (retorno_cliente.getBilheteCollection().isEmpty() == false) {
                for (Bilhete x : retorno_cliente.getBilheteCollection()) {
                    BilheteDTO bilhete = new BilheteDTO(x.getPrecoBilhete());
                    ViagemDTO viag = new ViagemDTO(x.getIdViagens().getHoraPartida(), x.getIdViagens().getHoraChegada());
                    bilhete.setViagem(viag);
                    bilhete.setLugar(x.getLugar());
                    cli.getBilhetes().add(bilhete);
                }
            }

            /*FALTA FAZER AS PONTUACOES*/
            return cli;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public boolean inserePontComp(int valor, String emailCli, String nomeComp) {

        try {

            /*VERIFICAR INICIALMENTE SE O CLIENTE E A COMPANHIA EXISTEM PREVIAMENTE*/
            Cliente cliente_retorno = this.cliente.findbyEmail(emailCli);
            Companhia comp = this.companhia.findbyName(nomeComp);

            if (cliente_retorno == null || comp == null) {
                /*BASTA UM DELES SE NULL, PARA RETORNAR LOGO*/
                System.out.println("\nStress\n");
                return false;
            }

            /*ADICAO DA COMPANHIA NA COLLECCAO DE COMPANHIAS DA PONTUACAO, PARA QUE SEJA INSERIDA NA TABELA PONT_COMP*/
            Pontuacao pont_a_criar = new Pontuacao(this.pontuacao.count() + 1, valor);
            pont_a_criar.setIdCliente(cliente_retorno);
            if (pont_a_criar.getCompanhiaCollection() == null) {
                Collection<Companhia> novaColeccao = new ArrayList<Companhia>();
                novaColeccao.add(comp);
                pont_a_criar.setCompanhiaCollection(novaColeccao);
            } else {
                pont_a_criar.getCompanhiaCollection().add(comp);
            }

            /*ADICAO DA PONTUACAO NA COLLECCAO DE PONTUACOES DA COMPANHIA, PARA QUE SEJA INSERIDA NA TABELA PONT_COMP*/
            if (comp.getPontuacaoCollection() == null) {
                Collection<Pontuacao> novaColeccao = new ArrayList<Pontuacao>();
                novaColeccao.add(pont_a_criar);
                comp.setPontuacaoCollection(novaColeccao);
            } else {
                comp.getPontuacaoCollection().add(pont_a_criar);
            }

            this.pontuacao.create(pont_a_criar);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean apagaPontComp(int idPont) {

        try {

            /*VERIFICAR SE EXISTE A PONTUACAO A ELIMINAR*/
            Pontuacao pont = this.pontuacao.find(idPont);

            if (pont == null) {
                return false;
            }

            this.pontuacao.remove(pont);/*ELIMINA DA TABELA PONTUACAO E DA PONT_COMP, PORQUE ESTAO RELACIONADAS*/

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizaPontComp(int idPont, int novoValorPont) {

        try {

            /*VERIFICA INICIALMENTE SE EXISTE A PONTUACAO*/
            Pontuacao pont = this.pontuacao.find(idPont);

            if (pont == null) {
                return false;
            }

            pont.setValor(novoValorPont);
            this.pontuacao.edit(pont);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean inserePontPartida(int valor, String emailCli, String nomePartida){
        
        try{
            
            /*VERIFICAR INICIALMENTE SE EXISTE O CLIENTE E A PARTIDA*/
            Cliente c=this.cliente.findbyEmail(emailCli);
            Partidas part=this.partidas.findbyName(nomePartida);
            
            if(c==null || part==null){
                return false;
            }
            
            Pontuacao pont_insert=new Pontuacao(this.pontuacao.count()+1, valor);
            pont_insert.setIdCliente(c);/*DEFINIR QUAL O CLIENTE*/
            
            /*INSERCAO DA COLECCAO NAS PARTIDAS*/
            Collection<Partidas> colect_part=new ArrayList<Partidas>();
            colect_part.add(part);
            pont_insert.setPartidasCollection(colect_part);
            
            /*AGORA E NECESSARIO COLOCAR A PONTUACAO NAS PARTIDAS*/
            if(part.getPontuacaoCollection()==null){
                Collection<Pontuacao> colect_pont=new ArrayList<Pontuacao>();
                colect_pont.add(pont_insert);
                part.setPontuacaoCollection(colect_pont);
            }
            else{
                part.getPontuacaoCollection().add(pont_insert);
            }
            
            this.pontuacao.create(pont_insert);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
    }
    
    @Override
    public List<PontuacaoDTO> seleccionaAllClientPont(int idCli){
        
        try{
            
    public List<PontuacaoDTO> seleccionaAllClientPont(int idCli) {

        try {

            /*VERIFICAR SE O CLIENTE EXISTE*/
            Cliente cli = this.cliente.find(idCli);

            if (cli == null) {
                return null;
            }

            if (cli.getPontuacaoCollection().isEmpty() == true) {
                return null;
            }

            /*JUNCAO DE TODOS OS PONTOS*/
            List<PontuacaoDTO> ponts = new ArrayList<PontuacaoDTO>();

            /*PONTOS DADOS POR UM CLIENTE APENAS ÀS COMPANHIAS*/
            List<PontuacaoDTO> pontos_cliente_companhia=this.seleccionaAllClientPontComp(idCli);
            List<PontuacaoDTO> pontos_cliente_partida=this.seleccionaAllClientPontPart(idCli);
            
            /*ADICAO DAS PONTUACOES DA COMPANHIA AO ARRAY DE PONTUACOES*/
            if(pontos_cliente_companhia!=null){
                for(PontuacaoDTO x : pontos_cliente_companhia){
                    ponts.add(x);
                }
            }
            
            /*ADICAO DAS PONTUACOES DA PARTIDA AO ARRAY DE PONTUACOES*/
            if(pontos_cliente_partida.isEmpty()==false){
                for(PontuacaoDTO x : pontos_cliente_partida){
                    ponts.add(x);
                }
            }
            
            return ponts;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<PontuacaoDTO> seleccionaAllClientPontComp(int idCli) {

        try {

            /*VERIFICAR SE O CLIENTE EXISTE*/
            Cliente cli = this.cliente.find(idCli);

            if (cli == null) {
                return null;
            }

            if (cli.getPontuacaoCollection().isEmpty() == true) {
                return null;
            }

            List<PontuacaoDTO> ponts = new ArrayList<PontuacaoDTO>();

            for (Pontuacao x : cli.getPontuacaoCollection()) {
                PontuacaoDTO p = new PontuacaoDTO(x.getValor());
                if (x.getCompanhiaCollection().isEmpty() == false) {
                    List<CompanhiaDTO> comp = new ArrayList<CompanhiaDTO>();
                    for (Companhia c : x.getCompanhiaCollection()) {
                        CompanhiaDTO codto = this.selectCompanhia(c.getNomeCompanhia());
                        comp.add(codto);
                    }
                    p.setCompanhias(comp);
                }
                ponts.add(p);
            }

            return ponts;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<PontuacaoDTO> seleccionaAllClientPontPart(int id){
        
        try{
            
            /*VERIFICAR PRIMEIRO SE EXISTE O CLIENTE*/
            Cliente cli=this.cliente.find(id);
            
            if(cli==null){
                return null;
            }
            
            /*VERIFICAR SE O CLIENTE JÁ EFETUOU PONTUACOES A ALGUMAS PARTIDAS*/
            if(cli.getPontuacaoCollection().isEmpty()==true){
                return null;
            }
            
            List<PontuacaoDTO> pontuacoes=new ArrayList<PontuacaoDTO>();
            for(Pontuacao x : cli.getPontuacaoCollection()){
                PontuacaoDTO pt=new PontuacaoDTO(x.getValor());
                if(x.getPartidasCollection().isEmpty()==false){
                    for(Partidas pa : x.getPartidasCollection()){
                        pt.getPartidas().add(this.seleccionaPartida(pa.getCidadePartida()));
                    }
                }
                pontuacoes.add(pt);
            }
            
            return pontuacoes;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    @Override
    public boolean insereBilhete(int preco_bilhete, int id_viagem, int id_cliente){
        
        try{
            
           /*VERIFICAR INICIALMENTE SE EXISTE O CLIENTE E A VIAGEM, SO SE EXISTIREM AMBAS É QUE É POSSIVEL A INSERCAO DO BILHETE*/
           Viagens viag_ret=this.viagens.find(id_viagem);
           Cliente cli_ret=this.cliente.find(id_cliente);
            
           if(viag_ret==null || cli_ret==null){
               return false;
           }
           
           Bilhete bil_insert=new Bilhete(this.bilhete.count()+1,preco_bilhete);
           bil_insert.setLugar(this.bilhete.count()+1);
           bil_insert.setIdCliente(cli_ret);
           bil_insert.setIdViagens(viag_ret);
           
           this.bilhete.create(bil_insert);
           
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean apagaBilhete(int id){
        
        try{
            
            /*VERIFICAR INICIALMENTE SE EXISTE O BILHETE QUE SE PRETENDE APAGAR*/
            Bilhete b=this.bilhete.find(id);
            
            if(b==null){
                return false;
            }

            Bilhete bil_insert = new Bilhete(this.bilhete.count() + 1, preco_bilhete);
            //o id 1 fica com o lugar 1 e sempre assim sucessivamente..
            bil_insert.setLugar(this.bilhete.count()+1);
            bil_insert.setIdCliente(cli_ret);
            bil_insert.setIdViagens(viag_ret);

            this.bilhete.create(bil_insert);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean apagaBilhete(int id) {

        try {

            /*VERIFICAR INICIALMENTE SE EXISTE O BILHETE QUE SE PRETENDE APAGAR*/
            Bilhete b = this.bilhete.find(id);

            if (b == null) {
                return false;
            }

            this.bilhete.remove(b);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public BilheteDTO seleccionaBilhete(int id_bilhete) {

        try {

            /*VERIFICAR INICIALMENTE SE O BILHETE EXISTE*/
            Bilhete b = this.bilhete.find(id_bilhete);

            if (b == null) {
                return null;
            }

            BilheteDTO bil = new BilheteDTO(b.getPrecoBilhete());
            /*PODE NAO SER NECESSARIO*/
            ClienteDTO ret_cli = this.seleccionaCliente(b.getIdCliente().getEmailCliente());
            bil.setLugar(b.getLugar());
            bil.setCli(ret_cli);
            /*FALTA FAZER O SET DA VIAGEM*/

            return bil;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<BilheteDTO> seleccionaAllBilhetes() {

        try {

            /*PROCURAR INICIALMENTE TODOS OS BILHETES QUE EXISTEM*/
            List<Bilhete> lista_bilhetes_retorno = this.bilhete.findAll();

            if (lista_bilhetes_retorno.isEmpty() == true) {
                return null;
            }

            /*BASTA AGORA PARA CADA BILHETE, CHAMAR O METODO SELECCIONA BILHETE QUE O TRANSFORMA EM BILHETE DTO*/
            List<BilheteDTO> lista_bilhetes = new ArrayList<BilheteDTO>();
            for (Bilhete x : lista_bilhetes_retorno) {
                lista_bilhetes.add(this.seleccionaBilhete(x.getIdBilhete()));
            }

            return lista_bilhetes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public boolean insereViagem(int num_lugares, int hora_part, int hora_cheg, int id_aviao, int id_partida, int id_chegada) {

        try {

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    @Override
    public boolean apagaViagem(int idViagem) {

        try {

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    @Override
    public boolean atualizaViagembyAviao(int id_viagem, int id_novo_aviao) {

        try {

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    @Override
    public ViagemDTO seleccionaViagem(int idViagem) {

        try {

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return null;
    }

    @Override
    public List<ViagemDTO> seleccionaAllViagens() {

        try {

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return null;
    }

    //stand by
    @Override
    public boolean insertBagagem(int peso_bagagem, int id_viagem, int id_cliente) {

        try {
            Viagens viagem = viagens.find(id_viagem);
            Cliente cli = this.cliente.find(id_cliente);

            if (viagem == null || cli == null) {

                return false;
            }

            if (cli.getBilheteCollection().isEmpty() == false) {

                Bagagens bagagem_a_criar = new Bagagens(this.bagagem.count() + 1, peso_bagagem);
                bagagem_a_criar.setIdViagens(viagem);
                bagagem_a_criar.setIdCliente(cli);
                this.bagagem.create(bagagem_a_criar);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBagagem(int id_bagagem) {
        try {
            Bagagens encontrado = bagagem.find(id_bagagem);
            if (encontrado == null) {
                return false;
            }

            this.bagagem.remove(encontrado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean updateBagagem(int id_bagagem, int novo_peso) {
        try {
            Bagagens encontrado = bagagem.find(id_bagagem);
            if (encontrado == null) {
                return false;
            }
            encontrado.setPesoBagagens(novo_peso);
            this.bagagem.edit(encontrado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<BagagemDTO> selecionaAllBagagens() {
        try {

            List<Bagagens> bagagens = this.bagagem.findAll();

            if (bagagens.isEmpty() == true) {
                return null;
            }

            List<BagagemDTO> part = new ArrayList<BagagemDTO>();
            for (Bagagens x : bagagens) {
                part.add(new BagagemDTO(x.getIdBagagens(), x.getPesoBagagens()));
            }

            return part;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String selecionaBagagem(int id_bagagem) {
        return "";
    }

    @Override
    public boolean insereDestino(DestinoDTO part) {
        try {
            Destinos p = this.destino.findbyName(part.getCidade());

            if (p != null) {
                return false;
            }

            p = new Destinos(this.destino.count() + 1, part.getCidade(), 0);
            this.destino.create(p);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteDestino(String cidade) {
        try {
            Destinos dest = this.destino.findbyName(cidade);

            if (dest == null) {
                return false;
            }

            this.destino.remove(dest);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<DestinoDTO> selectAllDestinos() {

        try {

            List<Destinos> retorno_destino = this.destino.findAll();

            if (retorno_destino.isEmpty() == true) {
                return null;
            }

            List<DestinoDTO> part = new ArrayList<DestinoDTO>();
            for (Destinos x : retorno_destino) {
                part.add(new DestinoDTO(x.getCidadeDestino(),x.getPontuacaoMedia()));
            }

            return part;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public DestinoDTO selectDestino(String cidade) {
         try{
            
            /*VERIFICAR SE O DESTINO EXISTE*/
            Destinos p=this.destino.findbyName(cidade);
            
            if(p==null){
                return null;
            }
            
            /*CASO EXISTA-->DEFINIR TODOS OS SEUS DADOS*/
            DestinoDTO partSeleccionada=new DestinoDTO(p.getCidadeDestino(),p.getPontuacaoMedia());
            
            /*COLOCAR AS VIAGENS DA DO DESTINO  NO DESTINODTO*/
            if(p.getViagensCollection().isEmpty()==false){
                for(Viagens x : p.getViagensCollection()){
                    partSeleccionada.getViagens().add(new ViagemDTO( x.getHoraPartida(), x.getHoraChegada()));
                }
            }
            
            /*COLOCAR AS PONTUACOES DO DESTINO NO DESTINO DTO*/
            if(p.getPontuacaoCollection().isEmpty()==false){
                for(Pontuacao x : p.getPontuacaoCollection()){
                    partSeleccionada.getPontuacoes().add(new PontuacaoDTO(x.getValor()));
                }
            }
            
            return partSeleccionada;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
}
    }

    @Override
    public boolean insertPontDestino(int valor_pont, String emailCli, String nomeDest) {
      try{
          //verifica se o cliente foi encontrado pelo email
          Cliente cliente_encontrado= cliente.findbyEmail(emailCli);
          Destinos dest= this.destino.findbyName(nomeDest);
          if(cliente_encontrado==null || dest==null){
              System.out.println("\nAlgum erro ocorreu");
              return false;
          }
          
          //inserir pontuacao
          Pontuacao pont_a_inserir= new Pontuacao(this.pontuacao.count()+1,valor_pont);
          pont_a_inserir.setIdCliente(cliente_encontrado);
          if(pont_a_inserir.getDestinosCollection()==null){
              Collection<Destinos> novoDestino= new ArrayList<Destinos>();
              novoDestino.add(dest);
              pont_a_inserir.setDestinosCollection(novoDestino);
          }
          else{
              pont_a_inserir.getDestinosCollection().add(dest);
          }
          
          //adicao da pontuacao na colecao de pontuacoes do destino, para que esta seja inserida na tablea pont_dest
          if(dest.getPontuacaoCollection()==null){
              Collection<Pontuacao> novaColeccao= new ArrayList<Pontuacao>();
              novaColeccao.add(pont_a_inserir);
              dest.setPontuacaoCollection(novaColeccao);
          }
          else{
              dest.getPontuacaoCollection().add(pont_a_inserir);
          }
          
          this.pontuacao.create(pont_a_inserir);
          
      }catch(Exception e){
          System.out.println(e.getMessage());
          return false;      
      }
      return true;
    }

    @Override
    public boolean deletePontDestino(int idPont) {
        try{
            //encontra a pontuacao pelo id
            Pontuacao pont= this.pontuacao.find(idPont);
            if(pont==null){
                System.out.println("Algum erro ao encontrar a pontuacao");
                return false;
            }
            
            //elimina a pontuacao com esse id
            this.pontuacao.remove(pont);
        }catch(Exception e){
            System.out.println(e.getMessage());
          return false;  
        }
        return true;
    }

    @Override
    public boolean updatePontDestino(int idPont, int novaPont) {
        try{
            Pontuacao pont= this.pontuacao.find(idPont);
            if(pont==null){
                System.out.println("Algum erro ao encontrar a pontuacao");
                return false;
            }
            //atualizar com a nova pontuacao
            pont.setValor(novaPont);
            this.pontuacao.edit(pont);
        }catch(Exception e){
            System.out.println(e.getMessage());
          return false;  
        }
        return true;
    }
    
    @Override
    public List<PontuacaoDTO> selectAllPontuacoesDestino(String emailCli){
    public BilheteDTO seleccionaBilhete(int id){
        
        try{
            
            /*VERIFICAR INICIALMENTE SE O BILHETE EXISTE*/
            Bilhete b=this.bilhete.find(id);
            
            if(b==null){
            //encontra o cliente
            Cliente cli= this.cliente.findbyEmail(emailCli);
            if(cli==null){
                System.out.println("O cliente nao foi encontrado");
                return null;
            }
            
            BilheteDTO bil=new BilheteDTO(b.getPrecoBilhete());
            /*PODE NAO SER NECESSARIO*/
            ClienteDTO ret_cli=this.seleccionaCliente(b.getIdCliente().getEmailCliente());
            bil.setCli(ret_cli);
            /*FALTA FAZER O SET DA VIAGEM*/
            
            return bil;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    @Override
    public List<BilheteDTO> seleccionaAllBilhetes(){
        
        try{
            /*PROCURAR INICIALMENTE TODOS OS BILHETES QUE EXISTEM*/
            List<Bilhete> lista_bilhetes_retorno=this.bilhete.findAll();
            
            if(lista_bilhetes_retorno.isEmpty()==true){
            if(cli.getPontuacaoCollection().isEmpty()==true){
                return null;
            }
            List<PontuacaoDTO> ponts= new ArrayList<PontuacaoDTO>();
            
            for(Pontuacao x: cli.getPontuacaoCollection()){
                PontuacaoDTO p = new PontuacaoDTO(x.getValor());
                if(x.getDestinosCollection().isEmpty()==false){
                    List<DestinoDTO> dest= new ArrayList<DestinoDTO>();
                    for(Destinos d: x.getDestinosCollection()){
                        DestinoDTO codto= this.selectDestino(d.getCidadeDestino());
                        dest.add(codto);
                    }
                    p.setDestinos(dest);
                }
                ponts.add(p);
            }
            return ponts;
        }catch(Exception e){
            System.out.println(""+e.getMessage());
            return null;
        }    
        
    }
    
    @Override
    public boolean insereViagem(int hora_part, int hora_cheg, int id_aviao, int id_partida, int id_chegada){
        
        try{
            
            /*VERIFICAR SE EXISTE O AVIAO, A PARTIDA E O DESTINO*/
            Aviao av_ret=this.aviao.find(id_aviao);
            Partidas part_ret=this.partidas.find(id_partida);
            Destinos dest_ret=this.destino.find(id_chegada);
            
            if(av_ret==null || part_ret==null || dest_ret==null){
                return false;
            }
            
            Viagens viag=new Viagens(this.viagens.count()+1);
            viag.setHoraPartida(hora_part);
            viag.setHoraChegada(hora_cheg);
            viag.setIdAviao(av_ret);
            viag.setIdPartida(part_ret);
            viag.setIdDestino(dest_ret);         
            
            this.viagens.create(viag);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
        /*CHAMAR METODO ASSINCRONO NUM SINGLETON-->QUE VAI ENVIAR UMA MENSAGEM PARA UMA QUEUE, QUE DPS O MDB VAI LER ASSINCRONO ENVIAR PARA OS LOGS E ESCREVER NUM FICHEIRO*/
        logs.sendToQueue("Criada Viagem");
        return true;
    }
    
    @Override
    public boolean apagaViagem(int idViagem){
        
        try{
           
            /*VERIFICAR SE A VIAGEM EXISTE*/
            Viagens v_ret=this.viagens.find(idViagem);
            
            if(v_ret==null){
                return false;
            }
            
            this.viagens.remove(v_ret);
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean atualizaViagembyAviao(int id_viagem, int id_novo_aviao){
        
        try{
            
            /*VERIFICA SE EXISTE A VIAGEM A ATUALIZAR E O NOVO AVIAO*/
            Viagens viag_existe=this.viagens.find(id_viagem);
            Aviao av_existe=this.aviao.find(id_novo_aviao);
            
            if(viag_existe==null || av_existe==null){
                return false;
            }
 
            viag_existe.setIdAviao(av_existe);
            this.viagens.edit(viag_existe);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
        
        return true;
    }
    
    @Override
    public ViagemDTO seleccionaViagem(int idViagem){
        
        try{
           
            /*VERIFICAR INICIALMENTE SE EXISTE A VIAGEM*/
            Viagens viag_ret=this.viagens.find(idViagem);
            
            if(viag_ret==null){
                return null;
            }
            
            ViagemDTO vi=new ViagemDTO(viag_ret.getNumLugares(), viag_ret.getHoraPartida(), viag_ret.getHoraChegada());
            vi.setPart(new PartidaDTO(viag_ret.getIdPartida().getCidadePartida(), viag_ret.getIdPartida().getPontMedia()));
            vi.setDest(new DestinoDTO(viag_ret.getIdDestino().getCidadeDestino(),viag_ret.getIdDestino().getPontuacaoMedia()));
            
            return vi;
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
    
    @Override
    public List<ViagemDTO> seleccionaAllViagens(){
        
        try{
            
            List<Viagens> viagens_ret=this.viagens.findAll();
            
            if(viagens_ret.isEmpty()==true){
                return null;
            }
            
            List<ViagemDTO> lista_viagens=new ArrayList<ViagemDTO>();
            for(Viagens x : viagens_ret){
                lista_viagens.add(this.seleccionaViagem(x.getIdViagens()));
            }
            
            return lista_viagens;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
    
}
