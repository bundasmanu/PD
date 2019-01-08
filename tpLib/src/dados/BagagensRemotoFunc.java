/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import tpdtos.BagagemDTO;

/**
 *
 * @author pedro
 */
public interface BagagensRemotoFunc {
    public boolean insertBagagem(int peso_bagagem,int id_viagem, int id_cliente);
    public boolean deleteBagagem(int id_bagagem);
    public boolean updateBagagem(int id_bagagem, int novo_peso);
    //public boolean selecionaBagagem(int id_bagagem);
    public List<BagagemDTO> selecionaAllBagagens();
}
