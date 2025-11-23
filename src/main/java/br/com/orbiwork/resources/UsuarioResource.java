package br.com.orbiwork.resources;

import br.com.orbiwork.bo.UsuarioBO;
import br.com.orbiwork.model.Usuario;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioBO bo;

    @GET
    public List<Usuario> listar() {
        return bo.listar();
    }

    @GET
    @Path("/{id}")
    public Usuario buscarPorId(@PathParam("id") Long id) {
        return bo.buscarPorId(id);
    }

    @POST
    public Response inserir(Usuario usuario) {
        bo.inserir(usuario);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Usuario usuario) {
        usuario.setId(id);
        bo.atualizar(usuario);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        bo.deletar(id);
        return Response.noContent().build();
    }
}
