/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;
import tpdtos.AviaoDTO;
import tpdtos.CompanhiaDTO;
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
    
    @Override
    public String showOla(){
        return "Ola";
    }
    
    @Override
    public boolean insertCompanhia(CompanhiaDTO d){
        
        try{
            Companhia novaCompanhia=new Companhia(this.companhia.count()+1, d.getNome());
            //novaCompanhia.setIdAgencia();
            this.companhia.create(novaCompanhia);
            
        }
        catch(Exception e){
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean atualizaCompanhia(String nomeComp, String novoNome){
        
        /*PROCURA COMPANHIA*/
        try{
            Companhia estado=this.companhia.findbyName(nomeComp);
        
            if(estado==null){
                return false;
            }
        
            /*CASO EXISTA OBJETO NA BD*/
            estado.setNomeCompanhia(novoNome);
            this.companhia.edit(estado);
        }
        catch(Exception e){
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean apagaCompanhia(CompanhiaDTO d){
         
        /*PROCURA INICIALMENTE A COMPANHIA*/
        try{
            Companhia c=this.companhia.findbyName(d.getNome());
        
            if(c==null){
                return false;
            }
            
            /*SENAO APAGA A COMPANHIA*/
            this.companhia.remove(c);
        }
        catch(Exception e){
            return false;
        }
        
        return true;
    }
    
    @Override
    public List<CompanhiaDTO> selectAll(){
        
        /*FIND ALL TODAS AS COMPANHIAS*/
        List<CompanhiaDTO> lista_c_dto=null;
        try{
            List<Companhia> lista_comp=this.companhia.findAll();
            
            if(lista_comp==null){
                return null;
            }
            
            /*CONVERSAO DE COMPANHIA PARA COMPANHIA DTO*/
            lista_c_dto=new ArrayList<CompanhiaDTO>();
            for(Companhia x : lista_comp){
                CompanhiaDTO nova= new CompanhiaDTO(x.getNomeCompanhia());
                nova.setPontuacao_media(x.getPontuacaoMedia());
                /*PODE VIR A SER NECESSARIO COLOCAR OS AVIOES E AS PONTUACOES DENTRO DE CADA COMPANHIA*/
                
                lista_c_dto.add(nova);
            }
        }
        catch(Exception e ){
            return null;
        }
        return lista_c_dto;
    }
    
    @Override
    public CompanhiaDTO selectCompanhia(String nome_c){
        
        try{
            
            /*PROCURAR A COMPANHIA*/
            Companhia co=this.companhia.findbyName(nome_c);
            
            if(co==null){
                return null;
            }
            
            CompanhiaDTO retorno_companhia=new CompanhiaDTO(co.getNomeCompanhia());
            Collection<Aviao> lista_de_avioes=co.getAviaoCollection();
            if(lista_de_avioes==null){
                return retorno_companhia;
            }
            
            /*CASO HAJAM AVIOES NA COMPANHIA
            -->CONVERTE-LOS PARA LISTA DE AVIAO DTO*/
            for(Aviao x : lista_de_avioes){
                AviaoDTO aviao=new AviaoDTO(x.getNomeAviao(), x.getNumLugares());
                /*VERIFICAR SE VALE A PENA INCLUIR AS VIAGENS*/
                List<AviaoDTO> acrescentaAtualMaisUm=retorno_companhia.getAvioes();
                acrescentaAtualMaisUm.add(aviao);
                retorno_companhia.setListAvioes(acrescentaAtualMaisUm);
            }
            
            /*DEPOIS ACRESCENTAR AS VIAGENS*/
            return retorno_companhia;
        }
        catch(Exception e){
            return null;
        }
        
    }
    
    @Override
    public boolean encontrouOperador(OperadorDTO d){
        
        try{
            
            /*VERIFICAR SE O OPERADOR EXISTE*/
            Operador encontrou=operador.findbyName(d.getMail());
            
            if(encontrou==null){
                return false;
            }

            /*JA SABEMOS QUE O EMAIL COINCIDE, AGORA VAMOS VERIFICAR A PASS*/
            if(encontrou.getPassOperador().equals(d.getPass())!=true){
                return false;
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean insereOperador(OperadorDTO d){
        
        try{
            /*VERIFICAR INICIALMENTE SE NAO EXISTEM OPERADORES REGISTADOS COM O MSM EMAIL*/
            Operador ja_existe=operador.findbyName(d.getMail());
                         
            /*SENAO EXISTIR NENHUM UTILIZADOR REGISTADO COM O MESMO EMAIL ENTAO INSIRO-O NA BD*/
            if(ja_existe==null){
                Operador novoOp=new Operador(this.operador.count()+1,d.getNome(),d.getMail(),d.getPass());
                this.operador.create(novoOp);
                return true;
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
        return false;
    }
    
    @Override
    public boolean apagaOperador(String email){
        
        try{
            
            /*VERIFICAR INICIALMENTE SE EXISTE O OPERADOR A SER ELIMINADO*/
            Operador apagar=this.operador.findbyName(email);
            
            /*SE NAO EXISTIR, NAO POSSO APAGAR*/
            if(apagar==null){
                return false;
            }
            
            /*SE EXISTIR APAGO O OPERADOR*/
            this.operador.remove(apagar);
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean atualizaOp(String email, String novoNome){
        
        try{
        
            /*VERIFICAR INICIALMENTE SE EXISTE OPERADOR QUE SE PRETENDE ATUALIZAR*/
            Operador retornoOP=this.operador.findbyName(email);
            if(retornoOP==null){
                return false; /*NAO EXISTE*/
            }
            
            /*CASO EXISTA, DEFINO O SEU NOVO NOME E EDITO O OBJETO NA BD*/
            retornoOP.setNomeOperador(novoNome);
            
            this.operador.edit(retornoOP);
            
        }
        catch(Exception e){
            return false;
        }
        
        return true;
    }
    
    @Override
    public List<OperadorDTO> selectAllOPS(){
        
        /*RETORNO DE TODOS OS OPERADORES*/
        try{
            
            List<Operador> operadores=this.operador.findAll();
            
            if(operadores.isEmpty()==true){
                return null;
            }
            
            /*CRIO LISTA DE OPERADORES DTO*/
            List<OperadorDTO> retornoAllOperadores=new ArrayList<OperadorDTO>();
            
            for(Operador x : operadores){
                OperadorDTO op=new OperadorDTO(x.getNomeOperador(), x.getEmailOperador(), x.getPassOperador());
                retornoAllOperadores.add(op);
            }
            
            return retornoAllOperadores;
        }
        catch(Exception e){
            return null;
        }
    }
    
    @Override
    public boolean inserePartida(PartidaDTO part){
        
        /*NAO PODEM EXISTIR CIDADES COM O MESMO NOME*/
        try{
            
            Partidas p=this.partidas.findbyName(part.getCidade());
            
            /*VERIFICA SE JA EXISTE UMA PARTIDA COM A MESMA CIDADE, SE JA EXISTE NAO ACRESCENTA OUTRA*/
            if(p!=null){
                return false;
            }
            
            /*CASO AINDA NAO EXISTA, INSERE NA BD*/
            p=new Partidas(this.partidas.count()+1, part.getCidade(), 0);/*VARIAVEL P SE CHEGAR AQUI ESTA A NULL, POSSO UTILIZA-LA*/
            this.partidas.create(p);
            
        }
        catch(Exception e){
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean apagaPartida(String cidade){
        
        /*VERIFICAR INICIALMENTE SE A PARTIDA EXISTE*/
        try{
            
            Partidas part=this.partidas.findbyName(cidade);
            
            /*SENAO EXISTE NAO PODE APAGAR*/
            if(part==null){
                return false;
            }
            
            /*CASO EXISTA APAGA A PARTIDA*/
            this.partidas.remove(part);
            
        }
        catch(Exception e){
            return false;
        }
        
        return true;
    }
    
    @Override
    public List<PartidaDTO> seleccionaAllPartidas(){
        
        try{
            
            List<Partidas> retorno_partidas=this.partidas.findAll();
            
            if(retorno_partidas.isEmpty()==true){
                return null;
            }
            
            List<PartidaDTO> part=new ArrayList<PartidaDTO>();
            for(Partidas x : retorno_partidas){
                part.add(new PartidaDTO(x.getCidadePartida(),x.getPontMedia()));
            }
            
            return part;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public PartidaDTO seleccionaPartida(String cidade){
        
        /*CASO ENCONTRE TENHO DE COLOCAR TODOS OS DADOS DA PARTIDA RETORNADA NA PARTIDA DTO*/
        try{
            
            /*VERIFICAR SE A PARTIDA EXISTE*/
            Partidas p=this.partidas.findbyName(cidade);
            
            if(p==null){
                return null;
            }
            
            /*CASO EXISTA-->DEFINIR TODOS OS SEUS DADOS*/
            PartidaDTO partSeleccionada=new PartidaDTO(p.getCidadePartida(),p.getPontMedia());
            
            /*COLOCAR AS VIAGENS DA PARTIDA NA PARTIDA DTO*/
            if(p.getViagensCollection().isEmpty()==false){
                for(Viagens x : p.getViagensCollection()){
                    partSeleccionada.getViagens().add(new ViagemDTO(x.getNumLugares(), x.getHoraPartida(), x.getHoraChegada()));
                }
            }
            
            /*COLOCAR AS PONTUACOES DA PARTIDA NA PARTIDA DTO*/
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
    
}
