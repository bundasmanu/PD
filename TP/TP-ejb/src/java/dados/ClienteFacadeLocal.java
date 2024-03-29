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
public interface ClienteFacadeLocal {

    void create(Cliente cliente);

    void edit(Cliente cliente);

    void remove(Cliente cliente);

    Cliente find(Object id);
    
    Cliente findbyEmail(String email);

    List<Cliente> findAll();

    List<Cliente> findRange(int[] range);

    int count();
    
    //informação acerca do cliente
        
    //retorna lista de companhias associados a um cliente
    List<Companhia> findAllCompaniesFromClient(int id_cliente);
    
    //retorna lista de destinos associados a um cliente
    List<Destinos> findAllDestiniesFromCliente(int id_cliente);
    
}
