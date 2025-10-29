package exception;

import dto.misc.StringResponseDTO;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import util.MessageConstants;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {
    @Override
    public Response toResponse(PersistenceException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
        .entity(new StringResponseDTO(MessageConstants.ERR_PERSISTENCE.getMessage()))
        .build();
    }
}
