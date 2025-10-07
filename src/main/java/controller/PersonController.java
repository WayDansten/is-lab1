package controller;

import java.util.List;

import jakarta.ws.rs.*;

import dto.ErrorResponseDTO;
import dto.IdRequestDTO;
import dto.person.PersonRequestDTO;
import dto.person.PersonResponseDTO;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.PersonService;
import util.MessageConstants;

@Path("/person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonController {
    private PersonService service;
    
    public PersonController() {}

    @Inject
    public PersonController(PersonService service) {
        this.service = service;
    }

    @POST
    public Response add(PersonRequestDTO dto) {
        try {
            service.create(dto);
            return Response.status(Response.Status.CREATED).entity(MessageConstants.OK.getMessage()).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponseDTO(MessageConstants.ERR_BAD_REQUEST.getMessage())).build();
        }
    }

    @PUT
    public Response update(PersonRequestDTO dto) {
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
    public Response deleteByID(IdRequestDTO dto) {
        try {
            service.delete(dto);
            return Response.ok(MessageConstants.OK.getMessage()).build();
        } catch (PersistenceException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponseDTO(MessageConstants.ERR_NOT_FOUND.getMessage())).build();
        }
    }

    @GET
    public Response getAll() {
        List<PersonResponseDTO> persons = service.getAll();
        return Response.ok(persons).build();
    }
}
