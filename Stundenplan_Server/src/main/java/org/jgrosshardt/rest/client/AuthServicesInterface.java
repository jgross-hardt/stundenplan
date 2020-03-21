package org.jgrosshardt.rest.client;

import org.jgrosshardt.rest.model.Movie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public interface AuthServicesInterface {

    @GET
    @Path("/gettoken")
    @Produces({ MediaType.APPLICATION_JSON })
    String generateToken(@QueryParam("username") String username, @QueryParam("password") String password);


}
