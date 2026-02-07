package com.eventdriver.ejercicios.quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
/**
 * Recurso REST simple de ejemplo que permite comprobar que la aplicación
 * Quarkus está corriendo y atendiendo peticiones HTTP.
 */
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }
}
