/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import javax.ejb.Remote;
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
@Remote
public interface BeanRemotoRemote extends AviaoRemotoFunc, CompanhiaRemotoFunc, OperadorRemotoFunc, PartidaRemotoFunc, ClienteRemotoFunc, PontuacaoRemoteFunc, BilheteRemotoFunc, ViagemRemotoFunc, BagagensRemotoFunc,DestinoRemotoFunc{
    
    @Override
    public String mostraOla();
    
        //TEMPO
    public int tempoAtual();
    public void retificaIncrementoTempo(int valor);
    
    @Override
    public boolean insertCompanhia(CompanhiaDTO d);
    
    @Override
    public boolean deleteCompanhia(CompanhiaDTO d);
    
    @Override
    public boolean updateCompanhia(String nomeComp, String novoNome);
    
    @Override
    public List<CompanhiaDTO> selectList();
    
    @Override
    public CompanhiaDTO selectCompanhia(String nome_c);
    
    @Override
    public boolean encontrouOperador(OperadorDTO d);
    
    @Override
    public boolean insertOperador(OperadorDTO d);
    
    @Override
    public boolean deleteOperador(String email);
    
    @Override
    public boolean atualizaOperador(String email, String novoNome);/*FORNECER O EMAIL DO OPERADOR E ALTERAR O SEU NOME*/
    
    @Override
    public List<OperadorDTO> selectAllOP();
    
    @Override
    public boolean insertPartida(PartidaDTO part);/*NAO PODEM EXISTIR CIDADES COM O MESMO NOME*/
    
    @Override
    public boolean deletePartida(String cidade);
    
    @Override
    public List<PartidaDTO> selectAllPartidas();
    
    @Override
    public PartidaDTO selectPartida(String cidade);

    @Override
    public boolean insertAviao( AviaoDTO aviao,String nome_companhia);
    
    @Override
    public boolean deleteAviao(int id);
    
    @Override
    public boolean updateAviao(int id,Integer novo_num_lugares);
    
    @Override
    public List<AviaoDTO> selectionaAviao();
    
    @Override
    public boolean insertCliente(ClienteDTO c, String pass);/*POR SEGURANÇA A PASS É PASSADA POR ATRIBUTO*/
    
    @Override
    public boolean deleteCliente(String email);/*COMO O EMAIL É UNICO, BASTA PASSAR ESSE PARAMETRO*/
    
    @Override
    public boolean updateCliente(String email,String nova_pass);/*ATENCAO NAO É PRECISA VALIDAR O UTILIZADOR, ISSO É FEITO NA PARTE WEB*/
    
    @Override
    public List<ClienteDTO> selectAllClientes();
    
     @Override
    public List<BilheteDTO> selectAllBilhetesFromACliente(int id_cliente);
    
    @Override
    public boolean verificaLogin(String email, String pass);
    
    @Override
    public ClienteDTO selectCliente(String email);
    
    @Override
    public boolean insertPontComp(int valor, String emailCli, String nomeComp);
    
    @Override
    public boolean deletePontComp(int idPont);
 
    @Override
    public boolean updatePontComp(int idPont, int novaPont);
    
    @Override
    public boolean insertPontPartida(int valor, String emailCli, String namePartida);
    
    @Override
    public List<PontuacaoDTO> selectAllClientPont(int idCli); //TAMBEM DAVA PELO EMAIL, MAS POR AGORA FIZ ASSIM
    
    @Override
    public boolean insertPontDestino(int valor_pont,String emailCli,String nomeDest);
    
    @Override
    public boolean deletePontDestino(int idPont);
    
    @Override
    public boolean updatePontDestino(int idPont,int novaPont);
    
    @Override
    public List<PontuacaoDTO> selectAllPontuacoesDestino(String emailCli);
    
    @Override
    public boolean insertBilhete(int preco_bilhete, int id_viagem, int id_cliente);
    
    @Override
    public boolean deleteBilhete(int id);
    
    @Override
    public BilheteDTO selectBilhete(int id_bilhete);
    
    @Override
    public List<BilheteDTO> selectAllBilhetes();
    
    @Override
    public boolean insertViagem(int hora_part, int hora_cheg, int id_aviao, int id_partida, int id_chegada); /*PODERIA SER UTILIZADO O NOME AVIAO E OS NOMES DAS CIDADES, PORQUE SAO UNICAS*/
    
    @Override
    public boolean deleteViagem(int idViagem);
    
    @Override
    public boolean updateViagembyAviao(int id_viagem, int id_novo_aviao); /*PODERIA SER UTILIZADO O NOME DO AVIAO*/
    
    @Override
    public ViagemDTO selectViagem(int idViagem);
    
    @Override
    public List<ViagemDTO> selectAllViagens();
    
   
    
    
    @Override
    public boolean insertBagagem(int peso_bagagem,int id_viagem, int id_cliente);
    
    @Override
    public boolean deleteBagagem(int id_bagagem);
    
    @Override
    public boolean updateBagagem(int id_bagagem, int novo_peso);
    
    @Override
    public List<BagagemDTO> selecionaAllBagagens();
    
    @Override
    public boolean insereDestino(DestinoDTO part);
    
    @Override
    public boolean deleteDestino(String cidade);
 
    @Override
    public List<DestinoDTO> selectAllDestinos();
    
    @Override
    public DestinoDTO selectDestino(String cidade);
}
