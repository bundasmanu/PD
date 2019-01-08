/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import tpdtos.ViagemDTO;

/**
 *
 * @author gustavo
 */
public interface ViagemRemotoFunc {
    
    public boolean insertViagem(int hora_part, int hora_cheg, int id_aviao, int id_partida, int id_chegada); /*PODERIA SER UTILIZADO O NOME AVIAO E OS NOMES DAS CIDADES, PORQUE SAO UNICAS*/
    public boolean deleteViagem(int idViagem);
    public boolean updateViagembyAviao(int id_viagem, int id_novo_aviao); /*PODERIA SER UTILIZADO O NOME DO AVIAO*/
    public ViagemDTO selectViagem(int idViagem);
    public List<ViagemDTO> selectAllViagens();
    
}
