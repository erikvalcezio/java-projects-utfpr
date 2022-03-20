package br.edu.utfpr.evalcezio.somar.dois.numeros.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Erik Eduardo Valcezio
 * @author 
 */

@Path("rest")
@Api(value = "somar dois números")
public class JakartaEE9Resource {

    @GET
    @Path("somar-dois-numeros/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Somar dois dois números")
    public String somar(@PathParam("a") int a, @PathParam("b") int b){
        System.out.println("Valor A: " + a );
        System.out.println("Valor B: " + b );
        return String.valueOf(a + b);
    }    
}
