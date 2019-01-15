/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsTP;

import java.util.Set;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 *
 * @author gustavo
 */
@javax.ws.rs.ApplicationPath("webresources")
public  class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    
   
     public Response toResponse(Exception exception) {
        exception.printStackTrace();
        return Response.serverError().entity(exception.getMessage()).build();
    } 

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(wsTP.ViagemRS.class);
    }
}
