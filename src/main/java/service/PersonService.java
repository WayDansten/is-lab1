package service;

import java.util.List;

import dto.IdRequestDTO;
import dto.person.PersonRequestDTO;
import dto.person.PersonResponseDTO;
import entity.Person;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.PersonMapper;
import repository.PersonRepository;

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
    public void delete(IdRequestDTO dto) {
        repository.deleteByKey(dto.getId());
    }

    @Transactional
    public List<PersonResponseDTO> getAll() {
        return repository.getAll().stream().map(mapper::toDTO).toList();
    }
}
