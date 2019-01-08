/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import tpdtos.PontuacaoDTO;

/**
 *
 * @author gustavo
 */
public interface PontuacaoRemoteFunc {
    
    //parte referente a pontuacao de uma determinada companhia
    public boolean insertPontComp(int valor, String emailCli, String nomeComp);
    public boolean deletePontComp(int idPont);
    public boolean updatePontComp(int idPont, int novaPont);
    public List<PontuacaoDTO> selectAllClientPont(int idCli); //TAMBEM DAVA PELO EMAIL, MAS POR AGORA FIZ ASSIM
    
    //parte referente a pontuacao de um determinado destino
    public boolean insertPontDestino(int valor_pont,String emailCli,String nomeDest);
    public boolean deletePontDestino(int idPont);
    public boolean updatePontDestino(int idPont,int novaPont);
    public List<PontuacaoDTO> selectAllPontuacoesDestino(String emailCli);
    //select fazer depois
}
