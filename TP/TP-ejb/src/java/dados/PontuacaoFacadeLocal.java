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
public interface PontuacaoFacadeLocal {

    void create(Pontuacao pontuacao);

    void edit(Pontuacao pontuacao);

    void remove(Pontuacao pontuacao);

    Pontuacao find(Object id);

    List<Pontuacao> findAll();

    List<Pontuacao> findRange(int[] range);

    int count();
    
}
