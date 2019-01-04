/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import javax.ejb.Local;
import tpdtos.AviaoDTO;
import tpdtos.BilheteDTO;
import tpdtos.ClienteDTO;
import tpdtos.CompanhiaDTO;
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
    public List<ClienteDTO> seleccionaAllClientes();
    public boolean validaLogin(String email, String pass);
    public ClienteDTO seleccionaCliente(String email);
    
    //parte das pontuacoes
    public boolean inserePontComp(int valor, String emailCli, String nomeComp);
    public boolean apagaPontComp(int idPont);
    public boolean atualizaPontComp(int idPont, int novaPont);
    public List<PontuacaoDTO> seleccionaAllClientPont(int idCli); //TAMBEM DAVA PELO EMAIL, MAS POR AGORA FIZ ASSIM
    public List<PontuacaoDTO> seleccionaAllClientPontComp(int idCli);
    /*FALTA FAZER O SELECT DAS PARTIDAS E DOS DESTINOS*/
    
    //parte das viagens
    public boolean insereViagem(int num_lugares, int hora_part, int hora_cheg, int id_aviao, int id_partida, int id_chegada); /*PODERIA SER UTILIZADO O NOME AVIAO E OS NOMES DAS CIDADES, PORQUE SAO UNICAS*/
    public boolean apagaViagem(int idViagem);
    public boolean atualizaViagembyAviao(int id_viagem, int id_novo_aviao); /*PODERIA SER UTILIZADO O NOME DO AVIAO*/
    public ViagemDTO seleccionaViagem(int idViagem);
    public List<ViagemDTO> seleccionaAllViagens();
    
    //parte dos bilhetes
    public boolean insereBilhete(int preco_bilhete, int id_viagem, int id_cliente);
    public boolean apagaBilhete(int id);
    public BilheteDTO seleccionaBilhete(int id_bilhete);
    public List<BilheteDTO> seleccionaAllBilhetes();
    
}
