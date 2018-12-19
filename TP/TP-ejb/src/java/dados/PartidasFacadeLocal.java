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
public interface PartidasFacadeLocal {

    void create(Partidas partidas);

    void edit(Partidas partidas);

    void remove(Partidas partidas);

    Partidas find(Object id);

    List<Partidas> findAll();

    List<Partidas> findRange(int[] range);

    int count();
    
}
