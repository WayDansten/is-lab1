package controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import service.LabWorkService;
import util.MessageConstants;
import websocket.WebSocketMessageType;
import websocket.WebSocketNotifier;

@Path("/labwork")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LabWorkController {
    private LabWorkService service;

    public LabWorkController() {
    }

    @Inject
    public LabWorkController(LabWorkService service) {
        this.service = service;
    }

    @POST
    public Response add(LabWorkRequestDTO dto) {
        Set<WebSocketMessageType> changedTypes = service.create(dto);
        changedTypes.forEach(WebSocketNotifier::broadcast);
        return Response.status(Response.Status.CREATED).entity(new StringResponseDTO(MessageConstants.OK.getMessage()))
                .build();
    }

    @PATCH
    public Response update(LabWorkRequestDTO dto) {
        Set<WebSocketMessageType> changedTypes = service.update(dto);
        changedTypes.forEach(WebSocketNotifier::broadcast);
        return Response.ok(new StringResponseDTO(MessageConstants.OK.getMessage())).build();
    }

    @PATCH
    @Path("/difficulty")
    public Response lowerDifficulty(DifficultyRequestDTO dto) {
        service.lowerDifficulty(dto.getId(), dto.getSteps());
        WebSocketNotifier.broadcast(WebSocketMessageType.LABWORK);
        return Response.ok(new StringResponseDTO(MessageConstants.OK.getMessage())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        service.delete(id);
        WebSocketNotifier.broadcast(WebSocketMessageType.LABWORK);
        return Response.ok(new StringResponseDTO(MessageConstants.OK.getMessage())).build();
    }

    @DELETE
    @Path("/author")
    public Response deleteByAuthor(StringRequestDTO dto) {
        service.deleteByAuthor(dto.getString());
        WebSocketNotifier.broadcast(WebSocketMessageType.LABWORK);
        return Response.ok(new StringResponseDTO(MessageConstants.OK.getMessage())).build();
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

    @GET
    @Path("description")
    public Response getByDescription(@QueryParam("prefix") String prefix) {
        List<String> data = service.getByDescription(prefix);
        if (data.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new StringResponseDTO(MessageConstants.ERR_NOT_FOUND.getMessage()))
                    .build();
        }
        String names = data.stream().collect(Collectors.joining(", "));
        String responseString = String.format("Found lab works: %s.", names);
        return Response.ok(new StringResponseDTO(responseString)).build();
    }
}
