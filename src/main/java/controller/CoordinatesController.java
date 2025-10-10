package controller;

import java.util.List;

import jakarta.ws.rs.*;
import dto.coordinates.CoordinatesRequestDTO;
import dto.coordinates.CoordinatesResponseDTO;
import dto.response.MessageResponseDTO;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.CoordinatesService;
import util.MessageConstants;

@Path("/coordinates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CoordinatesController {
    private CoordinatesService service;
    
    public CoordinatesController() {}

    @Inject
    public CoordinatesController(CoordinatesService service) {
        this.service = service;
    }

    @POST
    public Response add(CoordinatesRequestDTO dto) {
        try {
            service.create(dto);
            return Response.status(Response.Status.CREATED).entity(MessageConstants.OK.getMessage()).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponseDTO(MessageConstants.ERR_BAD_REQUEST.getMessage())).build();
        }
    }

    @PUT
    public Response update(CoordinatesRequestDTO dto) {
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
        List<CoordinatesResponseDTO> coordinatess = service.getAll();
        return Response.ok(coordinatess).build();
    }
}
