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
public interface BagagensFacadeLocal {

    void create(Bagagens bagagens);

    void edit(Bagagens bagagens);

    void remove(Bagagens bagagens);

    Bagagens find(Object id);

    List<Bagagens> findAll();

    List<Bagagens> findRange(int[] range);

    int count();
    
}
