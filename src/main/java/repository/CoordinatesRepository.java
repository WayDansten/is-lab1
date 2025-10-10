package repository;

import entity.Coordinates;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CoordinatesRepository extends AbstractRepository<Coordinates, Integer> {
    @Inject
    public CoordinatesRepository() {
        super(Coordinates.class);
    }
}
