package repository;

import entity.Location;
import jakarta.persistence.EntityManager;

public class LocationRepository extends AbstractRepository<Location, Integer> {
    public LocationRepository(EntityManager em) {
        super(em, Location.class);
    }
}
