package org.jgrosshardt;

import io.jsonwebtoken.Claims;
import org.jgrosshardt.rest.JWTFilter.JWT;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.function.Function;

public class Server {
    public static void main(String[] args) {
        final String ServerURL = "http://localhost";
        final int port = 8080;
        Client client;
        Response response;
        String URL;

        URL = ServerURL + ":" + port + "/Stundenplan_Server/stundenplan/auth/";
        client = ClientBuilder.newClient();

        String path = "faecherauswahl";
        response = client.target(URL + path).request().get();

        System.out.println(response.readEntity(String.class));

        client.close();
    }
}
