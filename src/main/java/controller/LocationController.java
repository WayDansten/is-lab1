package controller;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import dto.location.LocationResponseDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.LocationService;

@Path("/location")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LocationController {
    private LocationService service;
    
    public LocationController() {}

    @Inject
    public LocationController(LocationService service) {
        this.service = service;
    }

    @GET
    public Response getAll() {
        List<LocationResponseDTO> locations = service.getAll();
        return Response.ok(locations).build();
    }
}
