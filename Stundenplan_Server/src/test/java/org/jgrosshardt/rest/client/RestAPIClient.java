package org.jgrosshardt.rest.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.function.Function;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.jgrosshardt.rest.JWTFilter.JWT;
import org.junit.Test;

import io.jsonwebtoken.Claims;

public class RestAPIClient {
    
    private static final String ServerURL = "http://localhost";
    
    private static final int port = 8080;

    private Client client;
    
    private Response response;

    private String URL;

    public RestAPIClient() {
        URL = ServerURL + ":" + port + "/Stundenplan_Server/stundenplan/auth/";
        client = ClientBuilder.newClient();
    }

    public void close() {
        client.close();
    }

    public void getRequest(String path) {
        response = client.target(URL + path).request().get();
    }

    public WebTarget getTarget(String path) {
        return client.target(URL + path);
    }

    @Test
    public void testJWT() {
        String token = JWT.createJWT("test", "hjklhjhkj", "kuiopoiop", 30_000L);
        Claims claims = JWT.decodeJWT(token);
        claims.forEach((k, v) -> {
            System.err.println(k + "=>" + v);
        });
    }

    public WebTarget withTarget(String path, Function<WebTarget, WebTarget> handle) {
        return handle.apply(getTarget(path));
    }
    
    @Test
    public void testEcho() {
        String testMsg = "Dies ist ein Test";
        response = withTarget("echo", target -> {
            return target.queryParam("message", testMsg);
        }).request().header(HttpHeaders.AUTHORIZATION, "").get();

        assertEquals("Expected names of artists does not match", testMsg, response.readEntity(String.class));

        response.close();
    }

    
    @Test
    public void testEchoAuth() {
        String testMsg = "Dies ist ein Test";
        
        // attempt, which fails
        response = withTarget("echo_auth", target -> {
            return target.queryParam("message", testMsg);
        }).request().header(HttpHeaders.AUTHORIZATION, "").get();

        assertTrue("Expected status does not match", response.getStatus() != 200);
        assertNotEquals("Expected names of message does not match", testMsg, response.readEntity(String.class));
        
        // login
        response = withTarget("login", target -> {
            return target //
                    .queryParam("username", "jkljkj") //
                    .queryParam("password", "ttt");
        }).request().header(HttpHeaders.AUTHORIZATION, "").get();

        assertTrue("Expected status does not match", response.getStatus() == 200);
        String token = response.readEntity(String.class);

        // attempt, which succeeds
        response = withTarget("echo_auth", target -> {
            return target //
                    .queryParam("message", testMsg);
        }).request().header(HttpHeaders.AUTHORIZATION, token).get();

        assertTrue("Expected status does not match", response.getStatus() == 200);
        assertEquals("Expected names of message does not match", testMsg, response.readEntity(String.class));

        response.close();
    }
}
