package util;

import dto.CoordinatesDTO;
import entity.Coordinates;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CoordinatesMapper {
    public CoordinatesDTO toDTO(Coordinates entity) {
        return new CoordinatesDTO(entity.getId(), entity.getX(), entity.getY());
    }

    public Coordinates toEntity(CoordinatesDTO dto) {
        Coordinates entity = new Coordinates();
        entity.setX(dto.getX());
        entity.setY(dto.getY());
        return entity;
    }
}
