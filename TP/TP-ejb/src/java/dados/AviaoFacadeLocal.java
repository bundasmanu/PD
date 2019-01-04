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
public interface AviaoFacadeLocal {

    void create(Aviao aviao);

    void edit(Aviao aviao);

    void remove(Aviao aviao);

    Aviao find(Object id);
    
    List<Aviao> findNomeAviao(String nome);

    List<Aviao> findAll();

    List<Aviao> findRange(int[] range);

    int count();
    
}
