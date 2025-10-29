package exception;

import dto.misc.StringResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import util.MessageConstants;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException>{
    @Override
    public Response toResponse(EntityNotFoundException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
        .entity(new StringResponseDTO(MessageConstants.ERR_NOT_FOUND.getMessage()))
        .build();
    }
}
