package repository;

import entity.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PersonRepository extends AbstractRepository<Person, Integer> {
    @Inject
    public PersonRepository() {
        super();
    }
}
