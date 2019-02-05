/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import tpdtos.BilheteDTO;

/**
 *
 * @author gustavo
 */
@Startup
@Singleton
public class TempoBean implements TempoBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")    
    @Resource
    private  SessionContext sessionCtx;       
    
    @EJB
    ViagensFacadeLocal viagens;
    
    @EJB
    ClienteFacadeLocal cliente;
    
    @EJB
    BilheteFacadeLocal bilhetes;
    
    @EJB
    LogsSendQueueBeanLocal logs;
    
    @EJB
    leilaoLocal leilao;        
    
    String timer_name="Tempo"; /*VARIAVEL QUE CONTROLA O TIMER ESPECIFICO*/
    private Tempo time;
    private TimerService t;
    private int valor_atual=1;
    
    private static final Logger logger=Logger.getLogger("");
    
    @PostConstruct
    public void startCrono(){
        CancelTimers();
        t= sessionCtx.getTimerService();
        time=new Tempo(0);
        t.createTimer(1,1, timer_name);/*INICIALMENTE O CRONO ESTA DEFINIDO DE 1 EM 1 SEGUNDO*/
    }
    
    @PreDestroy
    public void para(){
        CancelTimers();
    }
    
    @Override
    public void CancelTimers(){
        TimerService timers_em_execucao= this.sessionCtx.getTimerService();
        Collection<Timer> cronos= timers_em_execucao.getTimers();
        /*APAGAR TODOS OS TIMERS*/
        for(Timer x : cronos){
            if(x.getInfo().equals(this.timer_name)==true){
                x.cancel();
            }
        }
    }
    
    @Override
    public void retificaTempo(int novoVal){
        this.valor_atual=novoVal;
    }
    
    @Timeout
    public void timeout(Timer timer){
        time.setUnidade(time.getUnidade()+valor_atual);
        this.verificaViagensPartiramELeilao();
        //this.removeTodasViagensAposHoraTerminar(time.getUnidade());
        //logger.info("\nAcabou o tempo\n");
    }
    
    @Override
    public int getTempoAtual(){
        return this.time.getUnidade();
    }
    
    @Override
    public boolean verificaViagensPartiramELeilao(){
        
        try{
           
            /*BUSCAR TODAS AS VIAGENS*/
            List<Viagens> todasViagens=this.viagens.findAll();
            if(todasViagens.isEmpty()==true){
                return false;
            }
            
            /*VERIFICACAO DE QUAIS JA PARTIRAM*/
            for(Viagens x : todasViagens){
                if(x.getHoraPartida()<= this.getTempoAtual() && (x.getEstadoViagem().equals("Em Processo")==true || x.getEstadoViagem().equals("Em leilao")==true)){
                    List<BilheteDTO> retorno_bilhetes_leilao=this.leilao.retornoBilhetesGanhosLeilaoPorViagem(x);
                    if(retorno_bilhetes_leilao!=null){
                        for(BilheteDTO k : retorno_bilhetes_leilao){
                            Bilhete adicionar= new Bilhete(k.getPreco_bilhete());
                            adicionar.setLugar(this.bilhetes.count()+1);
                            adicionar.setIdViagens(x);
                            Cliente cc=this.cliente.find(k.getCli().getId());
                            if(cc!=null){
                                adicionar.setIdCliente(cc);
                                cc.setConta(cc.getConta()-k.getPreco_bilhete());/*RETIRO DINHEIRO AO CLIENTE*/
                            }
                            
                            x.setEstadoViagem("Em Viagem");
                            x.getBilheteCollection().add(adicionar);
                            this.viagens.edit(x);
                            this.bilhetes.create(adicionar);
                            /*RETIRAR DINHEIRO AO CLIENTE*/
                            this.cliente.edit(cc);/*ATUALIZO A INFORMACAO*/
                            /*ENVIO DO LOG DE COMPRA BILHETE LEILAO*/
                            Future<Boolean> envio_log=this.logs.sendToQueue("Comprado Bilhete Leilao ");
                        }
                        this.leilao.apagaViagemEmLeilao(x.getIdViagens());
                    }
                    
                    x.setEstadoViagem("Em Viagem");
                    this.viagens.edit(x);
                    Future<Boolean> envio_log=this.logs.sendToQueue("Partida da Viagem"+x.getIdViagens());
                }
            }
            
            /*VERIFICACAO DE QUAIS JA CONCLUIRAM*/
            for(Viagens x : todasViagens){
                if(x.getHoraChegada()<=this.getTempoAtual() && x.getEstadoViagem().equals("Em Viagem")==true){
                    x.setEstadoViagem("Concluida");
                    this.viagens.edit(x);
                    Future<Boolean> envio_log=this.logs.sendToQueue("Conclusao da Viagem"+x.getIdViagens());
                }
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean removeTodasViagensAposHoraTerminar(int hora_cheg) {

        try {
            List<Viagens> lista_viagens= new ArrayList<Viagens> ();
            /*VERIFICAR SE A VIAGEM EXISTE*/
            lista_viagens= this.viagens.findAll();
            
            if (lista_viagens.isEmpty() == false) {
                for (int i = 0; i < lista_viagens.size(); i++) {
                    if (lista_viagens.get(i).getHoraChegada() <= hora_cheg && lista_viagens.get(i).getEstadoViagem().equals("Concluida")==false) {
                        /*OBTENCAO DO ID DA VIAGEM A REMOVER*/
                        int id_v=lista_viagens.get(i).getIdViagens();
                        /*this.viagens.remove(lista_viagens.get(i));*/
                        lista_viagens.get(i).setEstadoViagem("Concluida");
                        this.viagens.edit(lista_viagens.get(i));
                        Future<Boolean> envio_log=this.logs.sendToQueue("Viagem Concluida"+id_v);
                    }
                }
            }
           
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
    
}
