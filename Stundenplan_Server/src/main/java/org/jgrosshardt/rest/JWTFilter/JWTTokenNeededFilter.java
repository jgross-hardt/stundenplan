package org.jgrosshardt.rest.JWTFilter;

import io.jsonwebtoken.SignatureException;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import javax.annotation.Priority;
import java.io.IOException;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        // "X-Auth-Token"

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            JWT.decodeJWT(token);
        } catch (SignatureException e) {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
