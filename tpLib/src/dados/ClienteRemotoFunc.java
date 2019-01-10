/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import tpdtos.BilheteDTO;
import tpdtos.ClienteDTO;

/**
 *
 * @author gustavo
 */
public interface ClienteRemotoFunc {
    
    public boolean insertCliente(ClienteDTO c, String pass);/*POR SEGURANÇA A PASS É PASSADA POR ATRIBUTO*/
    public boolean deleteCliente(String email);/*COMO O EMAIL É UNICO, BASTA PASSAR ESSE PARAMETRO*/
    public boolean updateCliente(String email,String nova_pass);/*ATENCAO NAO É PRECISA VALIDAR O UTILIZADOR, ISSO É FEITO NA PARTE WEB*/
    public List<ClienteDTO> selectAllClientes();
    public List<BilheteDTO> selectAllBilhetesFromACliente(int id_cliente);
    public boolean verificaLogin(String email, String pass);
    public ClienteDTO selectCliente(String email);  
    
}
