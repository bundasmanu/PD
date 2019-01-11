/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsTP;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import dados.singletonLocalLocal;
import java.util.List;
import javax.ws.rs.PathParam;
import tpdtos.ViagemDTO;

/**
 * REST Web Service
 *
 * @author gustavo
 */
@Path("ViagemRS")
@Stateless
public class ViagemRS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ViagemRS
     */
    public ViagemRS() {
    }

    /**
     * Retrieves representation of an instance of wsTP.ViagemRS
     * @return an instance of tpdtos.ViagemDTO
     */
    
    @EJB(name="Viagens")
    singletonLocalLocal sing;
    
    @GET
    @Path("/Preco/{Preco}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ViagemDTO> getViagensByPrice(@PathParam("Preco") int preco) {
        try{
            
            List<ViagemDTO> viagens_retorno=this.sing.seleccionaViagensPorPreco(preco);
            return viagens_retorno;
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
   
    @GET
    @Path("/Destino/{Destino}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ViagemDTO> getViagensByDest(@PathParam("Destino") String dest){
        
        try{
            
            List<ViagemDTO> retorno_viagem=this.sing.seleccionaViagensPorDestino(dest);
            return retorno_viagem;
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    /**
     * PUT method for updating or creating an instance of ViagemRS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(tpdtos.ViagemDTO content) {
    }
}
