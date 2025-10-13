package controller;

import java.util.List;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import dto.labwork.LabWorkRequestDTO;
import dto.labwork.LabWorkResponseDTO;
import dto.misc.DifficultyRequestDTO;
import dto.misc.LongResponseDTO;
import dto.misc.StringRequestDTO;
import dto.misc.StringResponseDTO;
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
            return Response.status(Response.Status.CREATED).entity(new StringResponseDTO(MessageConstants.OK.getMessage())).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new StringResponseDTO(MessageConstants.ERR_BAD_REQUEST.getMessage())).build();
        }
    }

    @PATCH
    public Response update(LabWorkRequestDTO dto) {
        try {
            service.update(dto);
            return Response.ok(new StringResponseDTO(MessageConstants.OK.getMessage())).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(MessageConstants.ERR_NOT_FOUND.getMessage()).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(MessageConstants.ERR_BAD_REQUEST.getMessage()).build();
        }
    }

    @PATCH
    @Path("/{id}")
    public Response lowerDifficulty(@PathParam("id") Integer id, DifficultyRequestDTO dto) {
        try {
            service.lowerDifficulty(id, dto.getDifficulty());
            return Response.ok(new StringResponseDTO(MessageConstants.OK.getMessage())).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(MessageConstants.ERR_NOT_FOUND.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        try {
            service.delete(id);
            return Response.ok(new StringResponseDTO(MessageConstants.OK.getMessage())).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new StringResponseDTO(MessageConstants.ERR_NOT_FOUND.getMessage())).build();
        }
    }

    @DELETE
    @Path("/author")
    public Response deleteByAuthor(StringRequestDTO dto) {
        try {
            service.deleteByAuthor(dto.getString());
            return Response.ok(new StringResponseDTO(MessageConstants.OK.getMessage())).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new StringResponseDTO(MessageConstants.ERR_NOT_FOUND.getMessage())).build();
        }
    }

    @GET
    public Response getAll() {
        List<LabWorkResponseDTO> labWorks = service.getAll();
        return Response.ok(labWorks).build();
    }

    @GET
    @Path("average_point")
    public Response countGreaterThanAveragePoint(@QueryParam("averagePoint") Float averagePoint) {
        Long count = service.countGreaterThanAveragePoint(averagePoint);
        return Response.ok(new LongResponseDTO(count)).build();
    }
}
