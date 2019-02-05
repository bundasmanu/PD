/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import javax.ejb.Local;
import tpdtos.BilheteDTO;
import tpdtos.ViagemDTO;

/**
 *
 * @author gustavo
 */
@Local
public interface leilaoLocal {
    
    public boolean adicionaBilheteLeilao(BilheteDTO bil);
    public List<BilheteDTO> retornoBilhetesGanhosLeilaoPorViagem(Viagens v);
    public List<BilheteDTO> retornaBilhetesViagemLeilaoOrdenados(ViagemDTO v);
    public List<BilheteDTO> verificaSeClientesTemDinheiro(List<BilheteDTO> b, int lugares_possiveis);
    public boolean apagaViagemEmLeilao(int id);
    
}
