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
public interface CompanhiaFacadeLocal {

    void create(Companhia companhia);

    void edit(Companhia companhia);

    void remove(Companhia companhia);

    Companhia find(Object id);
    
    public Companhia findbyName(String nome);

    List<Companhia> findAll();

    List<Companhia> findRange(int[] range);

    int count();
    
}
