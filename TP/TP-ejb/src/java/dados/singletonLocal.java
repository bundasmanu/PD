/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import tpdtos.AviaoDTO;
import tpdtos.BagagemDTO;
import tpdtos.BilheteDTO;
import tpdtos.ClienteDTO;
import tpdtos.CompanhiaDTO;
import tpdtos.DestinoDTO;
import tpdtos.LogDTO;
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

    private static final Logger LOGGER = Logger.getLogger(singletonLocal.class.getName());

    @EJB
    LogsSendQueueBeanLocal logs;

    @EJB
    BagagensFacadeLocal bagagem;

    @EJB
    leilaoLocal leilao;

    @EJB
    LogsFacadeLocal logsTab;

    @Override
    public String showOla() {
        return "Ola";
    }

    @Override
    public boolean insertCompanhia(CompanhiaDTO d) {

        try {

            Companhia ret = this.companhia.findbyName(d.getNome());

            if (ret != null) {
                return false;
            }

            Companhia novaCompanhia = new Companhia(d.getNome());
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
                Operador novoOp = new Operador(d.getNome(), d.getMail(), d.getPass());
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

            /*VERIFICA SE EXISTE UM DESTINO COM A MSM CIDADE, SE EXISTIR COPIA A SUA PONTUACAO*/
            Destinos contratrio = this.destino.findbyName(part.getCidade());

            if (contratrio == null) {
                /*CASO AINDA NAO EXISTA, INSERE NA BD, COM A PONTUACAO A 0*/
                p = new Partidas(part.getCidade(), 0);/*VARIAVEL P SE CHEGAR AQUI ESTA A NULL, POSSO UTILIZA-LA*/
            } else {
                p = new Partidas(part.getCidade(), contratrio.getPontuacaoMedia());
            }

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
                    partSeleccionada.getViagens().add(new ViagemDTO(x.getHoraPartida(), x.getHoraChegada()));
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
            if (av.getNum_lugares() > 0) {
                Aviao v = new Aviao(av.getNomeAviao(), av.getNum_lugares());
                v.setIdCompanhia(encontrou);
                encontrou.getAviaoCollection().add(v);
                this.companhia.edit(encontrou);
                this.aviao.create(v);
            } else {
                return false;
            }
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

        /*EDITAR A COMPANHIA*/
        Companhia encontrada = encontrado.getIdCompanhia();
        encontrada.getAviaoCollection().remove(encontrado);
        this.companhia.edit(encontrada);

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

            existe = new Cliente(c.getNome(), pass, c.getEmail());
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
    public boolean atualizaContaCliente(String email, int valor) {

        try {

            /*VERIFICA SE O CLIENTE EXISTE*/
            Cliente c = this.cliente.findbyEmail(email);

            if (c == null) {
                return false;
            }

            c.setConta(valor);
            this.cliente.edit(c);

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
                clientes.add(this.seleccionaCliente(x.getEmailCliente()));
            }

            return clientes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<BilheteDTO> selectAllBilhetesFromACliente(int id_cliente) {
        try {

            List<Bilhete> lista = this.bilhete.findAll();

            if (lista.isEmpty() == true) {
                return null;
            }
            List<BilheteDTO> lista_bilhetes = new ArrayList<BilheteDTO>();
            Cliente encontrado = this.cliente.find(id_cliente);
            if (encontrado == null) {
                return null;
            }

            if (encontrado.getBilheteCollection().isEmpty() == true) {
                return null;
            }

            for (Bilhete x : encontrado.getBilheteCollection()) {
                BilheteDTO w = new BilheteDTO(x.getPrecoBilhete());
                w.setViagem(new ViagemDTO(x.getIdViagens().getIdViagens()));
                w.setLugar(x.getLugar());
                lista_bilhetes.add(w);

            }
            return lista_bilhetes;

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return null;
        }

    }

   
    
    @Override
    public boolean validaLogin(String email, String pass) {

        try {

            /*VERIFICA SE EXISTE ALGUM USER COM O EMAIL INDICADO, SE EXISTIR É SO UM, SO PODE EXISTIR UM UTILIZADOR LOGADO COM O MSM EMAIL*/
            Cliente retorno_cliente = this.cliente.findbyEmail(email);
            Gestao_Password gp= new Gestao_Password();
            /*SENAO EXISTE CLIENTE*/
            if (retorno_cliente == null || (retorno_cliente != null &&  gp.comparePassword(pass,retorno_cliente.getPassCliente()) == false)) {
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
            cli.setId(retorno_cliente.getIdCliente());
            cli.setConta(retorno_cliente.getConta());

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
            Pontuacao pont_a_criar = new Pontuacao(valor);
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

            /*EDICAO DO CAMPO PONTUCAO MEDIA NA TABELA COMPANHIA*/
            int valores_pontuacoes = 0;
            for (Pontuacao x : comp.getPontuacaoCollection()) {
                valores_pontuacoes += x.getValor();
            }

            float pontuacao_media = valores_pontuacoes / comp.getPontuacaoCollection().size();
            comp.setPontuacaoMedia(pontuacao_media);

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

            for (Companhia x : pont.getCompanhiaCollection()) {
                boolean ac = x.getPontuacaoCollection().remove(pont);
                if (ac == true) {
                    this.companhia.edit(x);
                }
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
    public boolean inserePontPartida(int valor, String emailCli, String nomePartida) {

        try {

            /*VERIFICAR INICIALMENTE SE EXISTE O CLIENTE E A PARTIDA*/
            Cliente c = this.cliente.findbyEmail(emailCli);
            Partidas part = this.partidas.findbyName(nomePartida);

            if (c == null || part == null) {
                return false;
            }

            Pontuacao pont_insert = new Pontuacao(valor);
            pont_insert.setIdCliente(c);/*DEFINIR QUAL O CLIENTE*/

 /*INSERCAO DA COLECCAO NAS PARTIDAS*/
            Collection<Partidas> colect_part = new ArrayList<Partidas>();
            colect_part.add(part);
            pont_insert.setPartidasCollection(colect_part);

            /*AGORA E NECESSARIO COLOCAR A PONTUACAO NAS PARTIDAS*/
            if (part.getPontuacaoCollection() == null) {
                Collection<Pontuacao> colect_pont = new ArrayList<Pontuacao>();
                colect_pont.add(pont_insert);
                part.setPontuacaoCollection(colect_pont);
            } else {
                part.getPontuacaoCollection().add(pont_insert);
            }

            /*EDICAO DO CAMPO PONTUCAO MEDIA NA TABELA PARTIDA*/
            int valores_pontuacoes = 0;
            for (Pontuacao x : part.getPontuacaoCollection()) {
                valores_pontuacoes += x.getValor();
            }

            float pontuacao_media = valores_pontuacoes / part.getPontuacaoCollection().size();
            part.setPontMedia(pontuacao_media);

            this.pontuacao.create(pont_insert);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean inserePontDestino(int valor, String emailCli, String nomeDestino) {

        try {

            /*VERIFICAR INICIALMENTE SE EXISTE O CLIENTE E A PARTIDA*/
            Cliente c = this.cliente.findbyEmail(emailCli);
            Destinos dest = this.destino.findbyName(nomeDestino);

            if (c == null || dest == null) {
                return false;
            }

            Pontuacao pont_insert = new Pontuacao(valor);
            pont_insert.setIdCliente(c);/*DEFINIR QUAL O CLIENTE*/

 /*INSERCAO DA COLECCAO DE DESTINOS NA PONTUACAO*/
            Collection<Destinos> colect_dest = new ArrayList<Destinos>();
            colect_dest.add(dest);
            pont_insert.setDestinosCollection(colect_dest);

            /*AGORA E NECESSARIO COLOCAR A PONTUACAO NAS PARTIDAS*/
            if (dest.getPontuacaoCollection() == null) {
                Collection<Pontuacao> colect_pont = new ArrayList<Pontuacao>();
                colect_pont.add(pont_insert);
                dest.setPontuacaoCollection(colect_pont);
            } else {
                dest.getPontuacaoCollection().add(pont_insert);
            }

            /*EDICAO DO CAMPO PONTUCAO MEDIA NA TABELA PARTIDA*/
            int valores_pontuacoes = 0;
            for (Pontuacao x : dest.getPontuacaoCollection()) {
                valores_pontuacoes += x.getValor();
            }

            float pontuacao_media = valores_pontuacoes / dest.getPontuacaoCollection().size();
            dest.setPontuacaoMedia(pontuacao_media);

            this.pontuacao.create(pont_insert);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean apagaPontPart(int idPont) {

        try {

            /*VERIFICAR SE EXISTE A PONTUACAO A ELIMINAR*/
            Pontuacao pont = this.pontuacao.find(idPont);

            if (pont == null) {
                return false;
            }

            for (Partidas x : pont.getPartidasCollection()) {
                boolean ac = x.getPontuacaoCollection().remove(pont);
                if (ac == true) {
                    this.partidas.edit(x);
                }
            }

            this.pontuacao.remove(pont);/*ELIMINA DA TABELA PONTUACAO E DA PONT_COMP, PORQUE ESTAO RELACIONADAS*/


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean utualizaPontPart(int idPont, int novaPont) {

        try {

            /*VERIFICA INICIALMENTE SE EXISTE A PONTUACAO*/
            Pontuacao pont = this.pontuacao.find(idPont);

            if (pont == null) {
                return false;
            }

            pont.setValor(novaPont);
            this.pontuacao.edit(pont);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
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
            List<PontuacaoDTO> pontos_cliente_companhia = this.seleccionaAllClientPontComp(idCli);
            List<PontuacaoDTO> pontos_cliente_partida = this.seleccionaAllClientPontPart(idCli);

            /*ADICAO DAS PONTUACOES DA COMPANHIA AO ARRAY DE PONTUACOES*/
            if (pontos_cliente_companhia != null) {
                for (PontuacaoDTO x : pontos_cliente_companhia) {
                    ponts.add(x);
                }
            }

            /*ADICAO DAS PONTUACOES DA PARTIDA AO ARRAY DE PONTUACOES*/
            if (pontos_cliente_partida.isEmpty() == false) {
                for (PontuacaoDTO x : pontos_cliente_partida) {
                    ponts.add(x);
                }
            }

            return ponts;
        } catch (Exception e) {
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

    public List<PontuacaoDTO> seleccionaAllClientPontPart(int id) {

        try {

            /*VERIFICAR PRIMEIRO SE EXISTE O CLIENTE*/
            Cliente cli = this.cliente.find(id);

            if (cli == null) {
                return null;
            }

            /*VERIFICAR SE O CLIENTE JÁ EFETUOU PONTUACOES A ALGUMAS PARTIDAS*/
            if (cli.getPontuacaoCollection().isEmpty() == true) {
                return null;
            }

            List<PontuacaoDTO> pontuacoes = new ArrayList<PontuacaoDTO>();
            for (Pontuacao x : cli.getPontuacaoCollection()) {
                PontuacaoDTO pt = new PontuacaoDTO(x.getValor());
                if (x.getPartidasCollection().isEmpty() == false) {
                    for (Partidas pa : x.getPartidasCollection()) {
                        pt.getPartidas().add(this.seleccionaPartida(pa.getCidadePartida()));
                    }
                }
                pontuacoes.add(pt);
            }

            return pontuacoes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public boolean insereBilhete(int id_viagem, int id_cliente) {

        try {

            /*VERIFICAR INICIALMENTE SE EXISTE O CLIENTE E A VIAGEM, SO SE EXISTIREM AMBAS É QUE É POSSIVEL A INSERCAO DO BILHETE*/
            Viagens viag_ret = this.viagens.find(id_viagem);
            Cliente cli_ret = this.cliente.find(id_cliente);

            if (viag_ret == null || cli_ret == null) {
                return false;
            }

            /*VERIFICAR SE A LOTACAO AINDA NAO FOI ESGOTADA*/
            if (viag_ret.getIdAviao().getNumLugares() == this.viagens.count()) {/*SE JA ATINGIU A LOTACAO MAXIMA, NAO DEIXA INSERIR MAIS*/
                return false;
            }

            /*O INSERE BILHETE SO FUNCIONA PARA VIAGENS QUE ESTAO EM PROCESSAMENTO*/
            if (viag_ret.getEstadoViagem().equals("Em Processo") == false) {
                return false;
            }

            /*VERIFICAR SE O CLIENTE POSSUI DINHEIRO, PARA EFETUAR A COMPRA DO BILHETE*/
            if (cli_ret.getConta() < viag_ret.getPreco()) {/*CASO NAO TENHA DINHEIRO, NAO PODE EFETUAR A COMPRA DO BILHETE*/
                return false;
            }

            /*CASO TENHA DINHEIRO FAZ A INSERCAO DO BILHETE*/
            Bilhete bil_insert = new Bilhete(viag_ret.getPreco());
            bil_insert.setLugar(this.bilhete.count() + 1);
            bil_insert.setIdCliente(cli_ret);
            bil_insert.setIdViagens(viag_ret);

            /*CRIA BILHETE*/
            this.bilhete.create(bil_insert);

            /*ADICAO DO BILHETE NA VIAGEM*/
            viag_ret.getBilheteCollection().add(bil_insert);

            /*DEPOIS DE EFETUAR A INSERCAO DOS BILHETES, VERIFICAR SE A VIAGEM, JA ATINGIU O LIMITE, E PASSA DPS A LEILAO*/
            if (viag_ret.getBilheteCollection().size() >= (viag_ret.getIdAviao().getNumLugares() * 0.9)) {
                viag_ret.setPreco(0);
                viag_ret.setEstadoViagem("Em leilao");
            }

            /*EDIT DA VIAGEM*/
            this.viagens.edit(viag_ret);

            /*RETIRA DINHEIRO DA CONTA DO UTILIZADOR, E ATUALIZA O PARAMETRO NA BD*/
            cli_ret.setConta(cli_ret.getConta() - viag_ret.getPreco());
            cli_ret.getBilheteCollection().add(bil_insert);
            this.cliente.edit(cli_ret);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        try {
            Future<Boolean> ret = this.logs.sendToQueue("Compra Bilhete");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    @Override
    public boolean insereBilheteLeilao(int id_viagem, int id_cliente, int preco_leiloado) {

        try {

            /*VERIFICAR INICIALMENTE SE EXISTE O CLIENTE E A VIAGEM, SO SE EXISTIREM AMBAS É QUE É POSSIVEL A INSERCAO DO BILHETE*/
            Viagens viag_ret = this.viagens.find(id_viagem);
            Cliente cli_ret = this.cliente.find(id_cliente);

            if (viag_ret == null || cli_ret == null) {
                return false;
            }

            /*VERIFICAR SE A LOTACAO AINDA NAO FOI ESGOTADA*/
            if (viag_ret.getIdAviao().getNumLugares() == viag_ret.getBilheteCollection().size()) {/*SE JA ATINGIU A LOTACAO MAXIMA, NAO DEIXA INSERIR MAIS*/
                return false;
            }

            /*O INSERE BILHETE SO FUNCIONA PARA VIAGENS QUE ESTAO EM LEILAO*/
            if (viag_ret.getEstadoViagem().equals("Em leilao") == false) {
                return false;
            }

            /*CRIA BILHETE E PASSA AO LEILAO*/
            BilheteDTO passar_leilao = new BilheteDTO(preco_leiloado);
            ClienteDTO cliente_leilao = new ClienteDTO(cli_ret.getNomeCliente(), cli_ret.getEmailCliente());
            cliente_leilao.setId(cli_ret.getIdCliente());
            cliente_leilao.setConta(cli_ret.getConta());
            ViagemDTO viagem_leilao = new ViagemDTO(viag_ret.getHoraPartida(), viag_ret.getHoraChegada());
            viagem_leilao.setId(viag_ret.getIdViagens());
            passar_leilao.setCli(cliente_leilao);
            passar_leilao.setViagem(viagem_leilao);

            return this.leilao.adicionaBilheteLeilao(passar_leilao);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public String verificaEstadoViagem(int vi) {

        try {

            /*VERIFICA ESTADO DA VIAGEM*/
            Viagens todas_viagens = this.viagens.find(vi);

            if (todas_viagens == null) {
                return "";
            }

            return todas_viagens.getEstadoViagem();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }

    }

    @Override
    public boolean apagaBilhete(int id) {

        try {

            /*VERIFICAR INICIALMENTE SE EXISTE O BILHETE QUE SE PRETENDE APAGAR*/
            Bilhete b = this.bilhete.find(id);

            if (b == null) {
                return false;
            }

            /*Viagens x=b.getIdViagens();
            for(Bilhete w : x.getBilheteCollection()){
                if(w==b){
                    x.getBilheteCollection().remove(w);
                }
            }*/
            Viagens editar_viagem = b.getIdViagens();
            editar_viagem.getBilheteCollection().remove(b);
            this.viagens.edit(editar_viagem);

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

    //stand by
    @Override
    public boolean insertBagagem(int peso_bagagem, int id_viagem, int id_cliente) {

        try {
            Viagens viagem = viagens.find(id_viagem);
            Cliente cli = this.cliente.find(id_cliente);

            if (viagem == null || cli == null) {

                return false;
            }

            if (cli.getBilheteCollection().isEmpty() == true) {

                Bagagens bagagem_a_criar = new Bagagens(peso_bagagem);
                bagagem_a_criar.setIdViagens(viagem);
                bagagem_a_criar.setIdCliente(cli);

                /*EDIT DO CLIENTE*/
                cli.getBagagensCollection().add(bagagem_a_criar);
                //this.cliente.edit(cli);

                this.bagagem.create(bagagem_a_criar);

            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean deleteBagagem(int id_bagagem) {

        try {
            Bagagens encontrado = bagagem.find(id_bagagem);
            if (encontrado == null) {
                return false;
            }

            /*EDITAR O CLIENTE*/
            Cliente es = encontrado.getIdCliente();
            es.getBagagensCollection().remove(encontrado);
            this.cliente.edit(es);

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
                BagagemDTO bag = new BagagemDTO(x.getIdBagagens(), x.getPesoBagagens());
                bag.setViagem(new ViagemDTO(x.getIdViagens().getHoraPartida(), x.getIdViagens().getHoraChegada()));
                part.add(bag);
            }

            return part;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<BagagemDTO> SelecionaBagagens(String email_cliente) {
        try {
            List<Bagagens> lista = this.bagagem.findAll();
            if (lista.isEmpty() == true) {
                return null;
            }
            List<BagagemDTO> lista_bagagens = new ArrayList<BagagemDTO>();
            Cliente encontrado = this.cliente.findbyEmail(email_cliente);

            if (encontrado == null) {
                return null;
            }

            if (encontrado.getBagagensCollection().isEmpty() == true) {
                return null;
            }

            for (Bagagens x : encontrado.getBagagensCollection()) {
                BagagemDTO w = new BagagemDTO(x.getIdBagagens(), x.getPesoBagagens());
                //w.getViagem().setId(x.getIdViagens().getIdViagens());
                ViagemDTO v = this.seleccionaViagem(x.getIdViagens().getIdViagens());
                w.setViagem(v);

                lista_bagagens.add(w);
            }
            return lista_bagagens;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return null;
        }

    }

    @Override
    public boolean insereDestino(DestinoDTO part) {

        try {

            Destinos p = this.destino.findbyName(part.getCidade());

            if (p != null) {
                return false;
            }

            /*VERIFICAR SE JA EXISTE ALGUMA PARTIDA COM A MSM CIDADE, CASO HAJA A PONTUACAO_MEDIA SERA IGUAL*/
            Partidas contrario = this.partidas.findbyName(part.getCidade());

            if (contrario == null) {
                /*AINDA NAO EXISTE CIDADE, FICA COM O VALOR 0*/
                p = new Destinos(part.getCidade(), 0);
            } else {
                p = new Destinos(part.getCidade(), contrario.getPontMedia());
            }

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
                part.add(new DestinoDTO(x.getCidadeDestino(), x.getPontuacaoMedia()));
            }

            return part;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public DestinoDTO selectDestino(String cidade) {
        try {

            /*VERIFICAR SE O DESTINO EXISTE*/
            Destinos p = this.destino.findbyName(cidade);

            if (p == null) {
                return null;
            }

            /*CASO EXISTA-->DEFINIR TODOS OS SEUS DADOS*/
            DestinoDTO partSeleccionada = new DestinoDTO(p.getCidadeDestino(), p.getPontuacaoMedia());

            /*COLOCAR AS VIAGENS DA DO DESTINO  NO DESTINODTO*/
            if (p.getViagensCollection().isEmpty() == false) {
                for (Viagens x : p.getViagensCollection()) {
                    partSeleccionada.getViagens().add(new ViagemDTO(x.getHoraPartida(), x.getHoraChegada()));
                }
            }

            /*COLOCAR AS PONTUACOES DO DESTINO NO DESTINO DTO*/
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
    public boolean insertPontDestino(int valor_pont, String emailCli, String nomeDest) {
        try {
            //verifica se o cliente foi encontrado pelo email
            Cliente cliente_encontrado = cliente.findbyEmail(emailCli);
            Destinos dest = this.destino.findbyName(nomeDest);
            if (cliente_encontrado == null || dest == null) {
                System.out.println("\nAlgum erro ocorreu");
                return false;
            }

            //inserir pontuacao
            Pontuacao pont_a_inserir = new Pontuacao(valor_pont);
            pont_a_inserir.setIdCliente(cliente_encontrado);
            if (pont_a_inserir.getDestinosCollection() == null) {
                Collection<Destinos> novoDestino = new ArrayList<Destinos>();
                novoDestino.add(dest);
                pont_a_inserir.setDestinosCollection(novoDestino);
            } else {
                pont_a_inserir.getDestinosCollection().add(dest);
            }

            //adicao da pontuacao na colecao de pontuacoes do destino, para que esta seja inserida na tablea pont_dest
            if (dest.getPontuacaoCollection() == null) {
                Collection<Pontuacao> novaColeccao = new ArrayList<Pontuacao>();
                novaColeccao.add(pont_a_inserir);
                dest.setPontuacaoCollection(novaColeccao);
            } else {
                dest.getPontuacaoCollection().add(pont_a_inserir);
            }

            this.pontuacao.create(pont_a_inserir);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePontDestino(int idPont) {
        try {
            //encontra a pontuacao pelo id
            Pontuacao pont = this.pontuacao.find(idPont);
            if (pont == null) {
                System.out.println("Algum erro ao encontrar a pontuacao");
                return false;
            }

            for (Destinos x : pont.getDestinosCollection()) {
                boolean ac = x.getPontuacaoCollection().remove(pont);
                if (ac == true) {
                    this.destino.edit(x);
                }
            }

            //elimina a pontuacao com esse id
            this.pontuacao.remove(pont);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePontDestino(int idPont, int novaPont) {
        try {
            Pontuacao pont = this.pontuacao.find(idPont);
            if (pont == null) {
                System.out.println("Algum erro ao encontrar a pontuacao");
                return false;
            }
            //atualizar com a nova pontuacao
            pont.setValor(novaPont);
            this.pontuacao.edit(pont);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean insereViagem(int hora_part, int hora_cheg, int id_aviao, int id_partida, int id_chegada, int preco) {

        try {

            /*VERIFICAR SE EXISTE O AVIAO, A PARTIDA E O DESTINO*/
            Aviao av_ret = this.aviao.find(id_aviao);
            Partidas part_ret = this.partidas.find(id_partida);
            Destinos dest_ret = this.destino.find(id_chegada);

            if (av_ret == null || part_ret == null || dest_ret == null) {
                return false;
            }

            Viagens viag = new Viagens();
            if (preco >= 0) {
                viag.setPreco(preco);
                viag.setHoraPartida(hora_part);
                viag.setHoraChegada(hora_cheg);
                viag.setIdAviao(av_ret);
                viag.setIdPartida(part_ret);
                viag.setIdDestino(dest_ret);
                /*ATRIBUTO ESTADO DA VIAGEM*/
                viag.setEstadoViagem("Em Processo");

                this.viagens.create(viag);
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        /*CHAMAR METODO ASSINCRONO NUM SINGLETON-->QUE VAI ENVIAR UMA MENSAGEM PARA UMA QUEUE, QUE DPS O MDB VAI LER ASSINCRONO ENVIAR PARA OS LOGS E ESCREVER NUM FICHEIRO*/
        Future<Boolean> async = logs.sendToQueue("Criada Viagem");
        try {
            Boolean k = async.get();
            //LOGGER.info(""+k);
        } catch (InterruptedException ex) {
            Logger.getLogger(singletonLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(singletonLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean apagaViagem(int idViagem) {

        try {

            /*VERIFICAR SE A VIAGEM EXISTE*/
            Viagens v_ret = this.viagens.find(idViagem);

            if (v_ret == null) {
                return false;
            }

            /*ATUALIZAR OS DESTINOS E PARTIDAS*/
            Destinos dest = v_ret.getIdDestino();
            dest.getViagensCollection().remove(v_ret);
            this.destino.edit(dest);

            Partidas pt = v_ret.getIdPartida();
            pt.getViagensCollection().remove(v_ret);
            this.partidas.edit(pt);

            this.viagens.remove(v_ret);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizaViagembyAviao(int id_viagem, int id_novo_aviao) {

        try {

            /*VERIFICA SE EXISTE A VIAGEM A ATUALIZAR E O NOVO AVIAO*/
            Viagens viag_existe = this.viagens.find(id_viagem);
            Aviao av_existe = this.aviao.find(id_novo_aviao);

            if (viag_existe == null || av_existe == null) {
                return false;
            }

            viag_existe.setIdAviao(av_existe);
            this.viagens.edit(viag_existe);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public ViagemDTO seleccionaViagem(int idViagem) {

        try {

            /*VERIFICAR INICIALMENTE SE EXISTE A VIAGEM*/
            Viagens viag_ret = this.viagens.find(idViagem);

            if (viag_ret == null) {
                return null;
            }

            LOGGER.info("" + viag_ret.getBilheteCollection().size());

            ViagemDTO vi = new ViagemDTO(viag_ret.getHoraPartida(), viag_ret.getHoraChegada());
            vi.setId(viag_ret.getIdViagens());
            vi.setPreco(viag_ret.getPreco());
            vi.setEstado_viagem(viag_ret.getEstadoViagem());
            vi.setPart(new PartidaDTO(viag_ret.getIdPartida().getCidadePartida(), viag_ret.getIdPartida().getPontMedia()));
            vi.setDest(new DestinoDTO(viag_ret.getIdDestino().getCidadeDestino(), viag_ret.getIdDestino().getPontuacaoMedia()));
            List<BilheteDTO> novos_bilhete = new ArrayList<BilheteDTO>();
            for (Bilhete x : viag_ret.getBilheteCollection()) {
                LOGGER.info("Entrei");
                BilheteDTO novo = this.seleccionaBilhete(x.getIdBilhete());
                vi.getBilhetes().add(novo);
            }

            //vi.setBilhetes(novos_bilhete);
            return vi;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public ViagemDTO seleccionaViagemInversa(int idViagem) {

        try {

            /*VERIFICAR SE A VIAGEM EXISTE*/
            Viagens retorno_viagem = this.viagens.find(idViagem);

            if (retorno_viagem == null) {
                return null;
            }

            /*CIDADES DE PARTIDA E CHEGADA DA VIAGEM, A VERIFICAR SE EXISTE ALGUMA INVERSA*/
            String cidade_partida = retorno_viagem.getIdPartida().getCidadePartida();
            String cidade_chegada = retorno_viagem.getIdDestino().getCidadeDestino();

            /*PASSAR AGORA PARA INVERSA, VERIFICANDO SE EXISTE*/
            Partidas part_inverse = partidas.findbyName(cidade_chegada);
            Destinos dest_inverse = destino.findbyName(cidade_partida);
            if (part_inverse == null || dest_inverse == null) {
                return null;
            }

            Viagens inversa = this.viagens.findInverse(part_inverse.getIdPartida(), dest_inverse.getIdDestino());

            if (inversa == null) {
                return null;
            }

            ViagemDTO transforma_inversa = new ViagemDTO(inversa.getHoraPartida(), inversa.getHoraChegada());
            transforma_inversa.setId(inversa.getIdViagens());
            transforma_inversa.setPart(new PartidaDTO(inversa.getIdPartida().getCidadePartida(), inversa.getIdPartida().getPontMedia()));
            transforma_inversa.setDest(new DestinoDTO(inversa.getIdDestino().getCidadeDestino(), inversa.getIdDestino().getPontuacaoMedia()));

            return transforma_inversa;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<ViagemDTO> seleccionaViagensPorPreco(int preco) {

        try {
            /*VERIFICAR QUAIS AS VIAGENS QUE APRESENTAM PRECO INFERIOR AO PASSADO POR PARAMETRO*/
            List<Viagens> allViagens = this.viagens.findAll();

            if (allViagens.isEmpty() == true) {
                return null;
            }

            List<ViagemDTO> viagens = new ArrayList<ViagemDTO>();
            for (Viagens x : allViagens) {
                if (x.getPreco() < preco) {
                    ViagemDTO inserir_viagem = new ViagemDTO(x.getHoraPartida(), x.getHoraChegada());
                    inserir_viagem.setId(x.getIdViagens());
                    inserir_viagem.setPart(new PartidaDTO(x.getIdPartida().getCidadePartida(), x.getIdPartida().getPontMedia()));
                    inserir_viagem.setDest(new DestinoDTO(x.getIdDestino().getCidadeDestino(), x.getIdDestino().getPontuacaoMedia()));
                    viagens.add(inserir_viagem);
                }
            }

            return viagens;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public boolean lugaresVagosViagem(int idViagem, int numeroLugaresPretendidos) {

        try {

            /*CHAMADA DA QUERY QUE ESTA NA ENTIDADE VIAGENS*/
            return this.viagens.viagemLugaresVagos(idViagem, numeroLugaresPretendidos);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public List<ViagemDTO> getViagemBarataDestino() {

        try {

            List<ViagemDTO> retorno_viagens = this.viagens.viagensMaisBaratasDestinos();
            if (retorno_viagens != null) {

                List<ViagemDTO> viagens_finais = new ArrayList<ViagemDTO>();
                for (ViagemDTO x : retorno_viagens) {
                    ViagemDTO viag = this.seleccionaViagem(x.getId());
                    if (viag != null) {
                        viag.setNumeroVagas(x.getNumeroVagas());
                        viagens_finais.add(viag);
                    }
                }

                return retorno_viagens;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return null;
    }

    @Override
    public List<ViagemDTO> seleccionaViagensPorDestino(String dest) {

        try {

            /*VERIFICAR INICIALMENTE SE O DESTINO EXISTE*/
            Destinos dest_ret = this.destino.findbyName(dest);

            if (dest_ret == null) {
                return null;
            }

            /*BUSCAR VIAGENS TODAS E VERIFICAR SE O DESTINO É O MESMO DO PASSADO POR PARAMETRO*/
            List<Viagens> retorno_viagens = this.viagens.findAll();

            if (retorno_viagens.isEmpty() == true) {
                return null;
            }

            List<ViagemDTO> viagens_destino = new ArrayList<ViagemDTO>();
            for (Viagens x : retorno_viagens) {
                if (x.getIdDestino().getCidadeDestino().equals(dest) == true) {
                    viagens_destino.add(this.seleccionaViagem(x.getIdViagens()));
                }
            }

            return viagens_destino;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<PontuacaoDTO> selectAllPontuacoesDestino(String emailCli) {
        try {
            //encontra o cliente
            Cliente cli = this.cliente.findbyEmail(emailCli);
            if (cli == null) {
                System.out.println("O cliente nao foi encontrado");
                return null;
            }

            if (cli.getPontuacaoCollection().isEmpty() == true) {
                return null;
            }
            List<PontuacaoDTO> ponts = new ArrayList<PontuacaoDTO>();

            for (Pontuacao x : cli.getPontuacaoCollection()) {
                PontuacaoDTO p = new PontuacaoDTO(x.getValor());
                if (x.getDestinosCollection().isEmpty() == false) {
                    List<DestinoDTO> dest = new ArrayList<DestinoDTO>();
                    for (Destinos d : x.getDestinosCollection()) {
                        DestinoDTO codto = this.selectDestino(d.getCidadeDestino());
                        dest.add(codto);
                    }
                    p.setDestinos(dest);
                }
                ponts.add(p);
            }
            return ponts;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return null;
        }

    }

    @Override
    public List<ViagemDTO> seleccionaAllViagens() {

        try {

            List<Viagens> viagens_ret = this.viagens.findAll();

            if (viagens_ret.isEmpty() == true) {
                return null;
            }

            List<ViagemDTO> lista_viagens = new ArrayList<ViagemDTO>();
            for (Viagens x : viagens_ret) {
                lista_viagens.add(this.seleccionaViagem(x.getIdViagens()));
            }

            return lista_viagens;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<CompanhiaDTO> selectAllCompaniesFromClient(int id_cliente) {

        try {
            List<Companhia> lista_companhias = this.cliente.findAllCompaniesFromClient(id_cliente);

            if (lista_companhias == null) {
                return null;
            }

            List<CompanhiaDTO> lista = new ArrayList<CompanhiaDTO>();

            for (Companhia x : lista_companhias) {
                CompanhiaDTO comp = this.selectCompanhia(x.getNomeCompanhia());
                if (comp != null) {
                    lista.add(comp);
                }
            }

            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<DestinoDTO> selectAllDestiniesFromClient(int id_cliente) {
        try {
            List<Destinos> lista_destinos = this.cliente.findAllDestiniesFromCliente(id_cliente);

            if (lista_destinos == null) {
                LOGGER.info("retornou null sem dados");
                return null;
            }

            LOGGER.info("para aqui");
            List<DestinoDTO> lista = new ArrayList<DestinoDTO>();

            for (Destinos d : lista_destinos) {
                DestinoDTO destino = this.selectDestino(d.getCidadeDestino());
                if (destino != null) {
                    lista.add(destino);
                }
            }

            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<ViagemDTO> queryViagemParametrizalWebS(String dest, int minP, int maxP, int maxVagas) {

        try {

            /*OBTER O ID DO DESTINO*/
            Destinos destt = null;
            if (dest.equals("") == false) {
                destt = this.destino.findbyName(dest);
            }

            List<Viagens> ret = null;
            if (destt != null) {/*SE EXISTE O DESTINO*/
                ret = this.viagens.findViagensParametrizaveis(destt.getIdDestino(), minP, maxP, maxVagas);
            } else if (dest.equals("") == true && destt == null) {/*SENAO PASSEI O DESTINO*/
                ret = this.viagens.findViagensParametrizaveis(0, minP, maxP, maxVagas);
            } else {/*SE PASSEI O DESTINO E ESTE NAO EXISTE*/
                return null;
            }

            if (ret == null) {
                return null;
            }

            List<ViagemDTO> retorno_viagens = new ArrayList<ViagemDTO>();
            for (Viagens x : ret) {
                retorno_viagens.add(this.seleccionaViagem(x.getIdViagens()));
            }

            return retorno_viagens;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<LogDTO> obtemLogs() {

        try {

            List<Logs> logsRet = logsTab.findAll();

            if (logsRet == null) {
                return null;
            }

            List<LogDTO> retorno_logs = new ArrayList<LogDTO>();
            for (Logs x : logsRet) {
                retorno_logs.add(new LogDTO(x.getInfoLog(), x.getDataLog()));
            }

            return retorno_logs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<ViagemDTO> getViagensEmLeilao() {

        try {

            /*OBTENCAO DAS VIAGENS QUE ESTAO EM LEILAO*/
            List<Viagens> retorno_viagens_em_leilao = this.viagens.getViagemLeilao();

            if (retorno_viagens_em_leilao == null) {
                return null;
            }

            List<ViagemDTO> retorno_viagens = new ArrayList<ViagemDTO>();
            for (Viagens x : retorno_viagens_em_leilao) {
                retorno_viagens.add(this.seleccionaViagem(x.getIdViagens()));
            }

            return retorno_viagens;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<ViagemDTO> getViagensNormais() {

        try {

            List<Viagens> retorno_viagens = this.viagens.getViagensNormais();

            if (retorno_viagens == null) {
                return null;
            }

            List<ViagemDTO> viagens_retorno = new ArrayList<ViagemDTO>();
            for (Viagens x : retorno_viagens) {
                ViagemDTO viagem_ret = this.seleccionaViagem(x.getIdViagens());
                viagem_ret.setNumeroVagas(x.getIdAviao().getNumLugares() - x.getBilheteCollection().size());
                viagens_retorno.add(viagem_ret);
            }

            return viagens_retorno;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}
