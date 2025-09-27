package util;

import dto.LocationDTO;
import entity.Location;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LocationMapper {
    public LocationDTO toDTO(Location entity) {
        return new LocationDTO(entity.getName(), entity.getX(), entity.getY(), entity.getZ());
    }

    public Location toEntity(LocationDTO dto) {
        Location entity = new Location();
        entity.setName(dto.getName());
        entity.setX(dto.getX());
        entity.setY(dto.getY());
        entity.setZ(dto.getZ());
        return entity;
    }
}
