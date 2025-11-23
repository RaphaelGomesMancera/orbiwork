package br.com.orbiwork.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException ex) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorMessage(ex.getMessage()))
                .build();
    }

    public static class ErrorMessage {
        public String erro;
        public ErrorMessage(String msg) { this.erro = msg; }
    }
}
