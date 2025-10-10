package service;

import java.util.List;

import dto.person.PersonRequestDTO;
import dto.person.PersonResponseDTO;
import entity.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import mapper.PersonMapper;
import repository.PersonRepository;

@ApplicationScoped
@NoArgsConstructor
public class PersonService {
    private PersonMapper mapper;
    private PersonRepository repository;

    @Inject
    public PersonService(PersonMapper mapper, PersonRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional
    public void create(PersonRequestDTO dto) {
        Person entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Transactional
    public void update(PersonRequestDTO dto) {
        Person entity = mapper.toEntity(dto);
        repository.update(entity);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteByKey(id);
    }

    @Transactional
    public List<PersonResponseDTO> getAll() {
        return repository.getAll().stream().map(mapper::toDTO).toList();
    }
}
