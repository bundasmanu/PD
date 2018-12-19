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
public interface AgenciaViagensFacadeLocal {

    void create(AgenciaViagens agenciaViagens);

    void edit(AgenciaViagens agenciaViagens);

    void remove(AgenciaViagens agenciaViagens);

    AgenciaViagens find(Object id);

    List<AgenciaViagens> findAll();

    List<AgenciaViagens> findRange(int[] range);

    int count();
    
}
