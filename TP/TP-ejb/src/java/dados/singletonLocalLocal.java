/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import javax.ejb.Local;
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
@Local
public interface singletonLocalLocal {
    
    public String showOla();
    
    //parte das companhias
    public boolean insertCompanhia(CompanhiaDTO d);
    public boolean atualizaCompanhia(String nomeComp, String novoNome);
    public boolean apagaCompanhia(CompanhiaDTO d);
    public List<CompanhiaDTO> selectAll();
    
    //parte do aviao
    public boolean insertAviao(AviaoDTO aviao,String nome_companhia);
    public boolean deleteAviao(int id);
    public boolean updateAviao(int id,Integer novo_num_lugares);
    public List<AviaoDTO> selectionaAviao();
    public CompanhiaDTO selectCompanhia(String nome_c);
    
    //parte do operador
    public boolean encontrouOperador(OperadorDTO d);
    public boolean insereOperador(OperadorDTO d);
    public boolean apagaOperador(String email);
    public boolean atualizaOp(String email, String novoNome);/*FORNECER O EMAIL DO OPERADOR E ALTERAR O SEU NOME*/
    public List<OperadorDTO> selectAllOPS();
    
    //parte das partidas
    public boolean inserePartida(PartidaDTO part);/*NAO PODEM EXISTIR CIDADES COM O MESMO NOME*/
    public boolean apagaPartida(String cidade);
    public List<PartidaDTO> seleccionaAllPartidas();
    public PartidaDTO seleccionaPartida(String cidade);
    
    //parte dos clientes
    public boolean insereCliente(ClienteDTO c, String pass);/*POR SEGURANÇA A PASS É PASSADA POR ATRIBUTO*/
    public boolean apagaCliente(String email);/*COMO O EMAIL É UNICO, BASTA PASSAR ESSE PARAMETRO*/
    public boolean atualizaCliente(String email,String nova_pass);/*ATENCAO NAO É PRECISA VALIDAR O UTILIZADOR, ISSO É FEITO NA PARTE WEB*/
    public boolean atualizaContaCliente(String email, int valor);
    public List<ClienteDTO> seleccionaAllClientes();
    public boolean validaLogin(String email, String pass);
    public ClienteDTO seleccionaCliente(String email);
    public List<BilheteDTO> selectAllBilhetesFromACliente(int id_cliente);
    //usando queries jpql
    public List<CompanhiaDTO> selectAllCompaniesFromClient(int id_cliente);
    public List<DestinoDTO> selectAllDestiniesFromClient(int id_cliente);
    
    //parte das pontuacoes
    public boolean inserePontComp(int valor, String emailCli, String nomeComp);
    public boolean apagaPontComp(int idPont);
    public boolean atualizaPontComp(int idPont, int novaPont);
    public boolean inserePontPartida(int valor, String emailCli, String nomePartida);
    public boolean apagaPontPart(int idPont);
    public boolean utualizaPontPart(int idPont,int novaPont);
    public List<PontuacaoDTO> seleccionaAllClientPontComp(int idCli);
    public List<PontuacaoDTO> seleccionaAllClientPont(int idCli); //TAMBEM DAVA PELO EMAIL, MAS POR AGORA FIZ ASSIM
    /*FALTA FAZER O SELECT DAS PARTIDAS E DOS DESTINOS*/
    
    //parte das pontuacoes->relativa ao Destino
    public boolean insertPontDestino(int valor_pont,String emailCli,String nomeDest);
    public boolean deletePontDestino(int idPont);
    public boolean updatePontDestino(int idPont,int novaPont);
    public List<PontuacaoDTO> selectAllPontuacoesDestino(String emailCli);
    //Falta fazer o select ..
    
    //parte das viagens
    public boolean insereViagem(int hora_part, int hora_cheg, int id_aviao, int id_partida, int id_chegada, int preco); /*PODERIA SER UTILIZADO O NOME AVIAO E OS NOMES DAS CIDADES, PORQUE SAO UNICAS*/
    public boolean apagaViagem(int idViagem);
    public boolean atualizaViagembyAviao(int id_viagem, int id_novo_aviao); /*PODERIA SER UTILIZADO O NOME DO AVIAO*/
    public ViagemDTO seleccionaViagem(int idViagem);
    public ViagemDTO seleccionaViagemInversa(int idViagem);
    public List<ViagemDTO> seleccionaViagensPorPreco(int preco);
    public List<ViagemDTO> seleccionaViagensPorDestino(String dest);
    public List<ViagemDTO> seleccionaAllViagens();
    public String verificaEstadoViagem(int vi);
    public List<ViagemDTO> queryViagemParametrizalWebS(String dest, int minP, int maxP, int maxVagas);
    
    //parte dos bilhetes
    public boolean insereBilhete(int id_viagem, int id_cliente);
    public boolean apagaBilhete(int id);
    public BilheteDTO seleccionaBilhete(int id_bilhete);
    public List<BilheteDTO> seleccionaAllBilhetes();
    public boolean insereBilheteLeilao(int id_viagem, int id_cliente, int preco_leiloado);
    
    //parte das bagagens
    public boolean insertBagagem(int peso_bagagem,int id_viagem, int id_cliente);
    public boolean deleteBagagem(int id_bagagem);
    public boolean updateBagagem(int id_bagagem, int novo_peso);
    public String selecionaBagagem(int id_bagagem);
    public List<BagagemDTO> selecionaAllBagagens();
    
    
    //parte dos destinos
    public boolean insereDestino(DestinoDTO part);
    public boolean deleteDestino(String cidade);
    public List<DestinoDTO> selectAllDestinos();
    public DestinoDTO selectDestino(String cidade);
    
}
