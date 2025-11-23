package br.com.orbiwork.resources;

import br.com.orbiwork.bo.CursoBO;
import br.com.orbiwork.model.Curso;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoResource {

    @Inject
    CursoBO bo;

    @GET
    public List<Curso> listar() {
        return bo.listar();
    }

    @GET
    @Path("/{id}")
    public Curso buscarPorId(@PathParam("id") Long id) {
        return bo.buscarPorId(id);
    }

    @POST
    public Response inserir(Curso curso) {
        bo.inserir(curso);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Curso curso) {
        curso.setId(id);
        bo.atualizar(curso);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        bo.deletar(id);
        return Response.noContent().build();
    }
}
