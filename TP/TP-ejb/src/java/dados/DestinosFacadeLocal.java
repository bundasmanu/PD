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
public interface DestinosFacadeLocal {

    void create(Destinos destinos);

    void edit(Destinos destinos);

    void remove(Destinos destinos);

    Destinos find(Object id);
    
    public Destinos findbyName(String nomeCidade);

    List<Destinos> findAll();

    List<Destinos> findRange(int[] range);

    int count();
    
}
