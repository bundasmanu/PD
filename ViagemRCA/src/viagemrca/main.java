/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viagemrca;

import java.util.List;
import java.util.Scanner;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import tpdtos.ViagemDTO;

/**
 *
 * @author gustavo
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sc= new Scanner(System.in);
        String preco;
        
        //System.out.println("Qual o preco");
        //preco=sc.nextLine();
        ViagemRC v=new ViagemRC();

        ViagemDTO retorno_viagens=v.getViagensByPrice(ViagemDTO.class,"200");
       
        System.out.println("NULL");
        v.close();
    }
    
}
