/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import tpdtos.OperadorDTO;

/**
 *
 * @author gustavo
 */
public interface OperadorRemotoFunc {
    
    public boolean encontrouOperador(OperadorDTO d);
    public boolean insertOperador(OperadorDTO d);
    public boolean deleteOperador(String email);
    public boolean atualizaOperador(String email, String novoNome);/*FORNECER O EMAIL DO OPERADOR E ALTERAR O SEU NOME*/
    public List<OperadorDTO> selectAllOP();
}
