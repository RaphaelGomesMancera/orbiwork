package br.com.orbiwork.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class HomeRedirectResource {

    @GET
    public Response redirectToSwagger() {
        return Response
                .status(Response.Status.FOUND)
                .header("Location", "/swagger/#/")
                .build();
    }
}
