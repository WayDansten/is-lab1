package controller;

import java.util.List;

import jakarta.ws.rs.*;
import dto.person.PersonResponseDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.PersonService;

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

    @GET
    public Response getAll() {
        List<PersonResponseDTO> persons = service.getAll();
        return Response.ok(persons).build();
    }
}
