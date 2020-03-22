package org.jgrosshardt.rest.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jgrosshardt.jpa.Query;
import org.jgrosshardt.jpa.database.Fach;
import org.jgrosshardt.jpa.database.Schueler;
import org.jgrosshardt.rest.JWTFilter.JWT;
import org.jgrosshardt.rest.JWTFilter.JWTTokenNeeded;

import java.util.List;

@Path("/schueler")
public class StundenplanSchuelerService {

    static {
        Query.setup();
    }
    private Query query = new Query();

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

    //Test method
    @GET
    @Path("/echo")
    @Produces({ MediaType.TEXT_PLAIN })
    public String echo(@QueryParam("message") String message) {
        return (message != null ? message : "No message!");
    }

    //Test method
    @GET
    @Path("/echo_auth")
    @Produces({ MediaType.TEXT_PLAIN })
    @JWTTokenNeeded
    public String echoAuth(@QueryParam("message") String message) {
        return (message != null ? message : "No message!");
    }

    /**
     * authenticateUser takes username and password of an existing user
     * and if username and password are correct returns a temporary JWT Token
     * to the client
     *
     * @param username the username of the user
     * @param password the users password
     * @return the JWT Token
     */
    @POST
    @Path("/login")
    @Produces({ MediaType.TEXT_PLAIN })
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public String authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {

        //if username and password are correct
        if (authenticate(username, password))
        //Create a JWT Token that is valid for 10 min. and return it
        return JWT.createJWT("stundenplan", username, 600_000L, true);
        //Return an empty string if the authorization was unsuccessful
        return "";
    }

    private boolean authenticate(String username, String password) {
        System.err.println(query != null);
        List<Schueler> users = query.query("select s from Schueler s where username = '" + username.replace("'", "''") + "'", Schueler.class);
        if (users.size() != 1) {
            return false;
        }
        Schueler user = users.get(0);
        //TODO implement hash
        return user.getPassword().equals(password);
    }

    /**
     * getFaecherList is available with a get request at "/faecherauswahl"
     * and requires authentication with a JWT Token.
     * It returns all subjects stored in the database as JSON
     *
     * @return an array with all subjects in the database
     */
    @GET
    @Path("/faecherauswahl")
    @Produces({ MediaType.APPLICATION_JSON })
    @JWTTokenNeeded
    public Fach[] getFaecherList() {
        //Retrieve a List of all subjects from the database
        List<Fach> results = query.query("select f from Fach f", Fach.class);
        //Convert the List to an Array
        int length = results.size();
        Fach[] faecher = new Fach[length];
        for (int i = 0; i < length; i++) {
            faecher[i] = results.get(i);
        }
        //return the array
        return faecher;
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
