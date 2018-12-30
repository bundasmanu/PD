/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import tpdtos.PartidaDTO;

/**
 *
 * @author gustavo
 */
public interface PartidaRemotoFunc {
    
    public boolean insertPartida(PartidaDTO part);/*NAO PODEM EXISTIR CIDADES COM O MESMO NOME*/
    public boolean deletePartida(String cidade);
    //public boolean updatePartida-->Nao sei que parametro atualizar o nome nao faz sentido, só se for a pontuacao média, mas decerto mais valia fazer um trigger
    public List<PartidaDTO> selectAllPartidas();
    public PartidaDTO selectPartida(String cidade);//como só existe uma cidade com o mesmo nome, só retorna uma partida
}
