package org.jgrosshardt.rest.server;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jgrosshardt.rest.JWTFilter.JWT;
import org.jgrosshardt.rest.JWTFilter.JWTTokenNeeded;

@Path("/auth")
public class StundenplanService {

    @GET
    @Path("/")
    @Produces({ MediaType.TEXT_PLAIN })
    public Response index() {
        return Response.status(200).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity("").build();
    }

    @GET
    @Path("/echo")
    @Produces({ MediaType.TEXT_PLAIN })
    public String echo(@QueryParam("message") String message) {
        return (message != null ? message : "No message!");
    }

    @GET
    @Path("/echo_auth")
    @Produces({ MediaType.TEXT_PLAIN })
    @JWTTokenNeeded
    public String echoAuth(@QueryParam("message") String message) {
        return (message != null ? message : "No message!");
    }

    @GET
    @Path("/login")
    @Produces({ MediaType.TEXT_PLAIN })
    public String authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
        if (authenticate(username, password))
            return JWT.createJWT("tt", "stundenplan", username, 300_000L);
        return "";
    }

    private boolean authenticate(String username, String password) {
        return true;
    }

/*
    @GET
    @Path("/getinfo")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Movie movieByImdbId(@QueryParam("imdbId") String imdbId) {

        System.out.println("*** Calling  getinfo for a given ImdbID***");

        if (inventory.containsKey(imdbId)) {
            return inventory.get(imdbId);
        } else {
            return null;
        }
    }

    @POST
    @Path("/addmovie")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addMovie(Movie movie) {

        System.out.println("*** Calling  addMovie ***");

        if (null != inventory.get(movie.getImdbId())) {
            return Response.status(Response.Status.NOT_MODIFIED)
                    .entity("Movie is Already in the database.").build();
        }

        inventory.put(movie.getImdbId(), movie);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/updatemovie")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateMovie(Movie movie) {

        System.out.println("*** Calling  updateMovie ***");

        if (null == inventory.get(movie.getImdbId())) {
            return Response.status(Response.Status.NOT_MODIFIED)
                    .entity("Movie is not in the database.\nUnable to Update").build();
        }

        inventory.put(movie.getImdbId(), movie);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/deletemovie")
    public Response deleteMovie(@QueryParam("imdbId") String imdbId) {

        System.out.println("*** Calling  deleteMovie ***");

        if (null == inventory.get(imdbId)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Movie is not in the database.\nUnable to Delete").build();
        }

        inventory.remove(imdbId);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/listmovies")
    @Produces({ "application/json" })
    public List<Movie> listMovies() {
        return inventory.values().stream().collect(Collectors.toCollection(ArrayList::new));
    }

 */
}
