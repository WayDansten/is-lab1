package controller;

import java.util.List;

import jakarta.ws.rs.*;
import dto.discipline.DisciplineRequestDTO;
import dto.discipline.DisciplineResponseDTO;
import dto.response.MessageResponseDTO;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.DisciplineService;
import util.MessageConstants;

@Path("/discipline")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DisciplineController {
    private DisciplineService service;
    
    public DisciplineController() {}

    @Inject
    public DisciplineController(DisciplineService service) {
        this.service = service;
    }

    @POST
    public Response add(DisciplineRequestDTO dto) {
        try {
            service.create(dto);
            return Response.status(Response.Status.CREATED).entity(MessageConstants.OK.getMessage()).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponseDTO(MessageConstants.ERR_BAD_REQUEST.getMessage())).build();
        }
    }

    @PUT
    public Response update(DisciplineRequestDTO dto) {
        try {
            service.update(dto);
            return Response.ok(MessageConstants.OK.getMessage()).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(MessageConstants.ERR_NOT_FOUND.getMessage()).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(MessageConstants.ERR_BAD_REQUEST.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        try {
            service.delete(id);
            return Response.ok(MessageConstants.OK.getMessage()).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new MessageResponseDTO(MessageConstants.ERR_NOT_FOUND.getMessage())).build();
        }
    }

    @GET
    public Response getAll() {
        List<DisciplineResponseDTO> disciplines = service.getAll();
        return Response.ok(disciplines).build();
    }
}
