package br.com.orbiwork.resources;

import br.com.orbiwork.bo.TrilhaBO;
import br.com.orbiwork.model.Trilha;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/trilhas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrilhaResource {

    @Inject
    TrilhaBO bo;

    @GET
    public List<Trilha> listar() {
        return bo.listar();
    }

    @GET
    @Path("/{id}")
    public Trilha buscarPorId(@PathParam("id") Long id) {
        return bo.buscarPorId(id);
    }

    @POST
    public Response inserir(Trilha trilha) {
        bo.inserir(trilha);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Trilha trilha) {
        trilha.setId(id);
        bo.atualizar(trilha);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        bo.deletar(id);
        return Response.noContent().build();
    }
}
