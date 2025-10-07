package controller;

import java.util.List;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import dto.ErrorResponseDTO;
import dto.IdRequestDTO;
import dto.labwork.LabWorkRequestDTO;
import dto.labwork.LabWorkResponseDTO;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import service.LabWorkService;
import util.MessageConstants;

@Path("/labwork")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LabWorkController {
    private LabWorkService service;
    
    public LabWorkController() {}

    @Inject
    public LabWorkController(LabWorkService service) {
        this.service = service;
    }

    @POST
    public Response add(LabWorkRequestDTO dto) {
        try {
            service.create(dto);
            return Response.status(Response.Status.CREATED).entity(MessageConstants.OK.getMessage()).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponseDTO(MessageConstants.ERR_BAD_REQUEST.getMessage())).build();
        }
    }

    @PUT
    public Response update(LabWorkRequestDTO dto) {
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
    public Response delete(IdRequestDTO dto) {
        try {
            service.delete(dto);
            return Response.ok(MessageConstants.OK.getMessage()).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponseDTO(MessageConstants.ERR_NOT_FOUND.getMessage())).build();
        }
    }

    @GET
    public Response getAll() {
        List<LabWorkResponseDTO> labWorks = service.getAll();
        return Response.ok(labWorks).build();
    }
}
