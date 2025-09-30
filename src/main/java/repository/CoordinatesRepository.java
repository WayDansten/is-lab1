package repository;

import entity.Coordinates;
import jakarta.persistence.EntityManager;

public class CoordinatesRepository extends AbstractRepository<Coordinates, Integer> {
    public CoordinatesRepository(EntityManager em) {
        super(em, Coordinates.class);
    }
}
