/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateful;
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
@Stateful
@Interceptors(RemoteInterceptor.class)
public class BeanRemoto implements BeanRemotoRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    singletonLocalLocal sing;
    
    @EJB
    TempoBeanLocal crono;
    
    @Override
    public String mostraOla(){
        return this.sing.showOla();
    }
    
    //TEMPO
    @Override
    public int tempoAtual(){
        return this.crono.getTempoAtual();
    }
    
    @Override
    public void retificaIncrementoTempo(int valor){
        this.crono.retificaTempo(valor);
    } 
    
    @PostConstruct
    public void inicializacao(){
        System.out.println("\nConstruido\n");
    }
    
    @PreDestroy
    public void xau(){
        System.out.println("\nDestruindo\n");
    }
    
    @Override
    public boolean insertCompanhia(CompanhiaDTO d){
        return this.sing.insertCompanhia(d);
    }
    
    @Override
    public boolean deleteCompanhia(CompanhiaDTO d){
        return this.sing.apagaCompanhia(d);
    }
    
    @Override
    public boolean updateCompanhia(String nomeComp, String novoNome){
        return this.sing.atualizaCompanhia(nomeComp, novoNome);
    }
    
  
    @Override
    public CompanhiaDTO selectCompanhia(String nome_c){
        return this.sing.selectCompanhia(nome_c);
    }
    
    @Override
    public boolean encontrouOperador(OperadorDTO d){
        return this.sing.encontrouOperador(d);
    }
    
    @Override
    public boolean insertOperador(OperadorDTO d){
        return this.sing.insereOperador(d);
    }
    
    @Override
    public boolean deleteOperador(String email){
        return this.sing.apagaOperador(email);
    }
    
    @Override
    public boolean atualizaOperador(String email, String novoNome){
        return this.sing.atualizaOp(email, novoNome);
    }
    
    @Override
    public List<OperadorDTO> selectAllOP(){
        return this.sing.selectAllOPS();
    }
    
    @Override
    public boolean insertPartida(PartidaDTO part){
        return this.sing.inserePartida(part);
    }
    
    @Override
    public boolean deletePartida(String cidade){
        return this.sing.apagaPartida(cidade);
    }
    
    @Override
    public List<PartidaDTO> selectAllPartidas(){
        return this.sing.seleccionaAllPartidas();
    }
    
    @Override
    public PartidaDTO selectPartida(String cidade){
        return this.sing.seleccionaPartida(cidade);
    }

    @Override
    public boolean insertAviao(AviaoDTO aviao,String nome_companhia) {
       return this.sing.insertAviao(aviao, nome_companhia);
    }

    @Override
    public boolean deleteAviao(int id) {
        return this.sing.deleteAviao(id);
    }

    @Override
    public boolean updateAviao(int id,Integer novo_num_lugares) {
        return this.sing.updateAviao(id, novo_num_lugares);
    }
    
    @Override
    public List<CompanhiaDTO> selectList(){
        return this.sing.selectAll();
    }

    @Override
    public List<AviaoDTO> selectionaAviao(){
        return this.sing.selectionaAviao();
    }
    
    @Override
    public boolean insertCliente(ClienteDTO c, String pass){
        return this.sing.insereCliente(c, pass);
    }
    
    @Override
    public boolean deleteCliente(String email){
        return this.sing.apagaCliente(email);
    }
    
    @Override
    public boolean updateCliente(String email,String nova_pass){
        return this.sing.atualizaCliente(email, nova_pass);
    }
    
    @Override
    public boolean updateContaCliente(String email, int valor){
        return this.sing.atualizaContaCliente(email, valor);
    }
    
    @Override
    public List<ClienteDTO> selectAllClientes(){
        return this.sing.seleccionaAllClientes();
    }
    
    @Override
    public boolean verificaLogin(String email, String pass){
        return this.sing.validaLogin(email, pass);
    }
    
    @Override
    public ClienteDTO selectCliente(String email){
        return this.sing.seleccionaCliente(email);
    }
    
    @Override
    public boolean insertPontComp(int valor, String emailCli, String idComp){
        return this.sing.inserePontComp(valor, emailCli, idComp);
    }
    
    @Override
    public boolean deletePontComp(int id){
        return this.sing.apagaPontComp(id);
    }
    
    @Override
    public boolean updatePontComp(int idPont, int novoValorPont){
        return this.sing.atualizaPontComp(idPont, novoValorPont);
    }
    
    @Override
    public boolean insertPontPartida(int valor, String emailCli, String namePartida){
        return this.sing.inserePontPartida(valor, emailCli, namePartida);
    }
    
    @Override
    public List<PontuacaoDTO> selectAllClientPont(int idCli){
        return this.sing.seleccionaAllClientPontComp(idCli);
    }
    
    @Override
    public boolean insertBilhete(int id_viagem, int id_cliente){
        return this.sing.insereBilhete(id_viagem, id_cliente);
    }
    
    @Override
    public boolean insertBilheteLeilao(int id_viagem, int id_cliente, int preco_leiloado){
        return this.sing.insereBilheteLeilao(id_viagem, id_cliente, preco_leiloado);
    }
    
    @Override
    public boolean deleteBilhete(int id){
        return this.sing.apagaBilhete(id);
    }
    
    @Override
    public BilheteDTO selectBilhete(int id_bilhete){
        return this.sing.seleccionaBilhete(id_bilhete);
    }

    @Override
    public List<BilheteDTO> selectAllBilhetes(){
        return this.sing.seleccionaAllBilhetes();
    }
    
    @Override
    public boolean insertViagem(int hora_part, int hora_cheg, int id_aviao, int id_partida, int id_chegada,int preco){
        return this.sing.insereViagem(hora_part, hora_cheg, id_aviao, id_partida, id_chegada,preco);
    }
    
    @Override
    public String verificaEstadoViagem(int vi){
        return this.sing.verificaEstadoViagem(vi);
    }
    
    @Override
    public boolean deleteViagem(int idViagem){
        return this.sing.apagaViagem(idViagem);
    }
    
    @Override
    public boolean updateViagembyAviao(int id_viagem, int id_novo_aviao){
        return this.sing.atualizaViagembyAviao(id_viagem, id_novo_aviao);
    }
    
    @Override
    public ViagemDTO selectViagem(int idViagem){
        return this.sing.seleccionaViagem(idViagem);
    }
    
    @Override
    public ViagemDTO selectViagemInversa(int idViagem){
        return this.sing.seleccionaViagemInversa(idViagem);
    }
    
    @Override
    public List<ViagemDTO> selectViagensByPrice(int price){
        return null;
    }
    
    @Override
    public List<ViagemDTO> selectViagensByDest(String dest){
        return null;
    }
    
    @Override
    public List<ViagemDTO> selectAllViagens(){
        return this.sing.seleccionaAllViagens();
    }
    
    @Override
    public List<BilheteDTO> selectAllBilhetesFromACliente(int id_cliente){
        return this.sing.selectAllBilhetesFromACliente(id_cliente);
    }

    @Override
    public boolean insertBagagem(int peso_bagagem, int id_viagem, int id_cliente) {
       return this.sing.insertBagagem(peso_bagagem, id_viagem, id_cliente);
    }

    @Override
    public boolean deleteBagagem(int id_bagagem) {
        return this.sing.deleteBagagem(id_bagagem);
    }

    @Override
    public boolean updateBagagem(int id_bagagem, int novo_peso) {
        return this.sing.updateBagagem(id_bagagem, novo_peso);
    }

    @Override
    public List<BagagemDTO> selecionaAllBagagens() {
        return this.sing.selecionaAllBagagens();
    }

    @Override
    public boolean insereDestino(DestinoDTO part) {
        return this.sing.insereDestino(part);
    }

    @Override
    public boolean deleteDestino(String cidade) {
       return this.sing.deleteDestino(cidade);
    }

    @Override
    public List<DestinoDTO> selectAllDestinos() {
        return this.sing.selectAllDestinos();
    }

    @Override
    public DestinoDTO selectDestino(String cidade) {
        return this.sing.selectDestino(cidade);
    }

    @Override
    public boolean insertPontDestino(int valor_pont, String emailCli, String nomeDest) {
        return this.sing.insertPontDestino(valor_pont, emailCli, nomeDest);
    }
    
    @Override
    public boolean deletePontPart(int idPont){
        return this.sing.apagaPontPart(idPont);
    }
    
    @Override
    public boolean updatePontPart(int idPont,int novaPont){
        return this.sing.utualizaPontPart(idPont, novaPont);
    }

    @Override
    public boolean deletePontDestino(int idPont) {
        return this.sing.deletePontDestino(idPont);
    }

    @Override
    public boolean updatePontDestino(int idPont, int novaPont) {
        return this.sing.updatePontDestino(idPont, novaPont);
    }
    
    @Override
    public List<PontuacaoDTO> selectAllPontuacoesDestino(String emailCli){
        return this.sing.selectAllPontuacoesDestino(emailCli);
    }

    /*@Override
    public boolean removeTodasViagensAposHoraTerminar(int hora_cheg) {
        return this.sing.removeTodasViagensAposHoraTerminar(hora_cheg);
    }*/

   
    
}
