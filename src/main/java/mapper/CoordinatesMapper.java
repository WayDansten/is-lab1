package mapper;

import dto.coordinates.CoordinatesRequestDTO;
import dto.coordinates.CoordinatesResponseDTO;
import entity.Coordinates;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CoordinatesMapper {
    public CoordinatesResponseDTO toDTO(Coordinates entity) {
        return new CoordinatesResponseDTO(entity.getId(), entity.getX(), entity.getY());
    }

    public Coordinates toEntity(CoordinatesRequestDTO dto) {
        Coordinates entity = new Coordinates();
        entity.setX(dto.getX());
        entity.setY(dto.getY());
        return entity;
    }
}
