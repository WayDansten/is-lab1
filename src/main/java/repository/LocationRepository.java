package repository;

import entity.Location;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LocationRepository extends AbstractRepository<Location, Integer> {
    @Inject
    public LocationRepository() {
        super(Location.class);
    }
}
