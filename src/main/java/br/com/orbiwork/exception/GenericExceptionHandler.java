package br.com.orbiwork.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception ex) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage("Erro interno inesperado"))
                .build();
    }

    public static class ErrorMessage {
        public String erro;
        public ErrorMessage(String msg) { this.erro = msg; }
    }
}
