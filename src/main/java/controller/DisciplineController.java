package controller;

import java.util.List;

import jakarta.ws.rs.*;
import dto.discipline.DisciplineResponseDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.DisciplineService;

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

    @GET
    public Response getAll() {
        List<DisciplineResponseDTO> disciplines = service.getAll();
        return Response.ok(disciplines).build();
    }
}
