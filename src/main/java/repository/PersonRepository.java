package repository;

import entity.Person;
import jakarta.persistence.EntityManager;

public class PersonRepository extends AbstractRepository<Person, Integer> {
    public PersonRepository(EntityManager em) {
        super(em, Person.class);
    }
}
