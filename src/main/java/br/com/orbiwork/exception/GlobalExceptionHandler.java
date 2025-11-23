package br.com.orbiwork.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException ex) {

        ErrorMessage error = new ErrorMessage(ex.getMessage());

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(error)
                .build();
    }

    public static class ErrorMessage {
        public String erro;

        public ErrorMessage(String msg) {
            this.erro = msg;
        }
    }
}
