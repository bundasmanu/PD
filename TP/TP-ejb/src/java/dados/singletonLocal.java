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
    AviaoFacadeLocal aviao;

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
                CompanhiaDTO nova = new CompanhiaDTO(x.getNomeCompanhia());
                nova.setPontuacao_media(x.getPontuacaoMedia());
                /*PODE VIR A SER NECESSARIO COLOCAR OS AVIOES E AS PONTUACOES DENTRO DE CADA COMPANHIA*/

                lista_c_dto.add(nova);
            }
        } catch (Exception e) {
            return null;
        }
        return lista_c_dto;
    }

    @Override
    public List<AviaoDTO> selectAvioes(String nome_c) {

        List<AviaoDTO> lista_avioes = null;
        try {

            /*PROCURAR A COMPANHIA*/
            Companhia co = this.companhia.findbyName(nome_c);

            if (co == null) {
                return null;
            }

            Collection<Aviao> lista_de_avioes = co.getAviaoCollection();
            if (lista_avioes == null) {
                return null;
            }

            /*CASO HAJAM AVIOES NA COMPANHIA
            -->CONVERTE-LOS PARA LISTA DE AVIAO DTO*/
            lista_avioes = new ArrayList<AviaoDTO>();
            for (Aviao x : lista_de_avioes) {
                AviaoDTO aviao = new AviaoDTO(x.getNomeAviao(), x.getNumLugares());
                /*VERIFICAR SE VALE A PENA INCLUIR AS VIAGENS*/
                lista_avioes.add(aviao);
            }

        } catch (Exception e) {
            return null;
        }

        return lista_avioes;
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
    public boolean deleteAviao(String nome_aviao, String nome_companhia) {
        Companhia verifica_existencia = companhia.findbyName(nome_companhia);
        if (verifica_existencia == null) {
            return false;
        }

        Aviao encontrado = aviao.findNomeAviao(nome_aviao);
        if (encontrado == null) {
            return false;
        }

        aviao.remove(encontrado);

        return true;
    }

    @Override
    public boolean updateAviao(String nome_aviao,Integer novo_num_lugares) {
        try{
        Aviao av= aviao.findNomeAviao(nome_aviao);
        if(av==null){
            return false;
        }
        
        av.setNumLugares(novo_num_lugares);
        AviaoDTO aviaodto= new AviaoDTO(av.getNomeAviao(),av.getNumLugares());
        aviao.edit(av);
        }
        catch(Exception e){
            return false;
        }

        return true;
    }

    @Override
    public String selectionaAviao(String nome_aviao){
        /*
        
         Empregado emp = efl.find(id_empregado);
        String str = "";
        if (emp != null) {
            str = "IdEmpregado: " + emp.getIdEmpregado() + " Nome: " + emp.getNome() + " IdLoja: " + emp.getIdL();
        } else {
            str = "O empregado nao existe..";
        }
        return str;
        */
      
      Aviao av= aviao.findNomeAviao(nome_aviao);
      String str="";
      if(av!=null){
          str="IdAviao: "+av.getIdAviao()+ "Nome do aviao: "+av.getNomeAviao() + "Numero lugares: "+ av.getNumLugares()+"Companhia: "+av.getIdCompanhia().getNomeCompanhia();
      }
      else{
          str="Esse aviao nao existe";
      }
      
      return str;
    }

}
