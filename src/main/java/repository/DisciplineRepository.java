package repository;

import entity.Discipline;
import jakarta.persistence.EntityManager;

public class DisciplineRepository extends AbstractRepository<Discipline, Integer> {
    public DisciplineRepository(EntityManager em) {
        super(em, Discipline.class);
    }
}
