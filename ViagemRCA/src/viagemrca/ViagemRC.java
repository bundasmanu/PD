/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viagemrca;


//import com.sun.jersey.impl.json.writer.JsonEncoder;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.GET;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tpdtos.ViagemDTO;

/**
 * Jersey REST client generated for REST resource:ViagemRS [ViagemRS]<br>
 * USAGE:
 * <pre>
 *        ViagemRC client = new ViagemRC();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author gustavo
 */
public class ViagemRC {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://192.168.56.175:8080/TP-warWebservice/webresources";

    public ViagemRC() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("ViagemRS");
    }

    public void putJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    @GET
    public <T> T getViagensByDest(Class<T> responseType, String Destino) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Destino/{0}", new Object[]{Destino}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    @GET
    public  <T> T getViagensByPrice(Class<T> responseType, String Preco) throws ClientErrorException {
        //MediaType mediaType = new MediaType();
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Preco/{0}", new Object[]{Preco}));
        
        Builder builder = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        Response response= builder.get();

        
        //T response = builder.get((Class<T>) ViagemDTO.class);
        //MediaType.valueOf(MediaType.APPLICATION_JSON).equals(mediaType);
        
        return null;
    }
    


    public void close() {
        client.close();
    }
    
}
