package exception;

import dto.misc.StringResponseDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DifficultyExceptionMapper implements ExceptionMapper<DifficultyException> {
    @Override
    public Response toResponse(DifficultyException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
        .entity(new StringResponseDTO(exception.getMessage()))
        .build();
    }
}
