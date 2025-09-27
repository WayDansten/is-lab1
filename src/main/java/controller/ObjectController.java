package controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dto.ErrorResponseDTO;
import dto.IdRequestDTO;
import dto.LabWorkDTO;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import service.ObjectService;

@Path("/api/obj")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ObjectController {
    private ObjectService service;

    private static final String OK = "Request successfully fulfilled";

    private static final String ERR_BAD_REQUEST = "Failed to fulfill the request: field constraints violated.";
    private static final String ERR_NOT_FOUND = "Failed to fulfill the request: entity not found.";

    @Inject
    public ObjectController(ObjectService service) {
        this.service = service;
    }

    @POST
    @Path("/add")
    public Response addLabwork(LabWorkDTO dto) {
        try {
            service.create(dto);
            return Response.status(Response.Status.CREATED).entity(OK).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponseDTO(ERR_BAD_REQUEST)).build();
        }
    }

    @PUT
    @Path("/update")
    public Response updateLabwork(LabWorkDTO dto) {
        try {
            service.update(dto);
            return Response.ok(OK).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(ERR_NOT_FOUND).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ERR_BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete-id")
    public Response deleteLabworkByID(IdRequestDTO dto) {
        try {
            service.delete(dto);
            return Response.ok(OK).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponseDTO(ERR_NOT_FOUND)).build();
        }
    }

    @GET
    @Path("/get")
    public Response getAllLabworks() {
        List<LabWorkDTO> labWorks = service.getAll();
        return Response.ok(labWorks).build();
    }
}
