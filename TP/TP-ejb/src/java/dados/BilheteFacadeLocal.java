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
public interface BilheteFacadeLocal {

    void create(Bilhete bilhete);

    void edit(Bilhete bilhete);

    void remove(Bilhete bilhete);

    Bilhete find(Object id);

    List<Bilhete> findAll();

    List<Bilhete> findRange(int[] range);

    int count();
    
}
