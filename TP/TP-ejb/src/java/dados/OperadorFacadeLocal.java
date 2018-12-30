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
public interface OperadorFacadeLocal {

    void create(Operador operador);

    void edit(Operador operador);

    void remove(Operador operador);

    Operador find(Object id);
    
    public Operador findbyName(String email);

    List<Operador> findAll();

    List<Operador> findRange(int[] range);

    int count();
    
}
