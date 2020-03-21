package org.jgrosshardt.rest.client;

import org.jgrosshardt.rest.model.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("")
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
    @Path("/register")
    @Produces({ MediaType.APPLICATION_JSON })
    Response generateToken(@FormParam("username") String username, @FormParam("password") String password);

    @GET
    @Path("/getinfo")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Movie movieByImdbId(@QueryParam("imdbId") String imdbId);

    @GET
    @Path("/listmovies")
    @Produces({ "application/json" })
    List<Movie> listMovies();

    @POST
    @Path("/addmovie")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Response addMovie(Movie movie);

    @PUT
    @Path("/updatemovie")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Response updateMovie(Movie movie);

    @DELETE
    @Path("/deletemovie")
    Response deleteMovie(@QueryParam("imdbId") String imdbId);
}
