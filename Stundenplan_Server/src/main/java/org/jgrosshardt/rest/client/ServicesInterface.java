package org.jgrosshardt.rest.client;

import org.jgrosshardt.jpa.database.Fach;
import org.jgrosshardt.rest.model.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/schueler")
public interface ServicesInterface {

    @GET
    @Path("/")
    @Produces({ MediaType.TEXT_PLAIN })
    Response index();

    @GET
    @Path("/echo")
    @Produces({MediaType.TEXT_PLAIN})
    String echo(@HeaderParam("message") String message);

    @GET
    @Path("/echo_auth")
    @Produces({MediaType.TEXT_PLAIN})
    String echoAuth(@HeaderParam("message") String message);

    @GET
    @Path("/login")
    @Produces({ MediaType.APPLICATION_JSON })
    Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password);

    @GET
    @Path("/faecherauswahl")
    @Produces({ MediaType.APPLICATION_JSON })
    Fach[] getFaecherList();

    @GET
    @Path("/register")
    @Produces({ MediaType.APPLICATION_JSON })
    Response generateToken(@FormParam("username") String username, @FormParam("password") String password);
}
