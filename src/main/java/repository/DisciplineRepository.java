package repository;

import entity.Discipline;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DisciplineRepository extends AbstractRepository<Discipline, Integer> {
    @Inject
    public DisciplineRepository() {
        super(Discipline.class);
    }
}
