package br.com.orbiwork.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DatabaseExceptionHandler implements ExceptionMapper<DatabaseException> {

    @Override
    public Response toResponse(DatabaseException ex) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessage(ex.getMessage()))
                .build();
    }

    public static class ErrorMessage {
        public String erro;
        public ErrorMessage(String msg) { this.erro = msg; }
    }
}
