package controller;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import dto.coordinates.CoordinatesResponseDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.CoordinatesService;

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

    @GET
    public Response getAll() {
        List<CoordinatesResponseDTO> coordinatess = service.getAll();
        return Response.ok(coordinatess).build();
    }
}
