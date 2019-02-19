/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gustavo
 */
@Local
public interface ViagensFacadeLocal {

    void create(Viagens viagens);

    void edit(Viagens viagens);

    void remove(Viagens viagens);

    Viagens find(Object id);

    List<Viagens> findbyPrice(int preco);
    
    Viagens findInverse(int p, int t);
    
    List<Viagens> findViagensParametrizaveis(int dest, int minPreco, int maxPreco, int maxVagas);
            
    public boolean viagemLugaresVagos(int idViagem, int numeroVagas);
    
    List<Viagens> findAll();

    List<Viagens> findRange(int[] range);

    int count();
    
}
