package service;

import java.util.List;

import dto.IdRequestDTO;
import dto.coordinates.CoordinatesRequestDTO;
import dto.coordinates.CoordinatesResponseDTO;
import entity.Coordinates;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.CoordinatesMapper;
import repository.CoordinatesRepository;

@ApplicationScoped
public class CoordinatesService {
    private CoordinatesMapper mapper;
    private CoordinatesRepository repository;

    @Inject
    public CoordinatesService(CoordinatesMapper mapper, CoordinatesRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional
    public void create(CoordinatesRequestDTO dto) {
        Coordinates entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Transactional
    public void update(CoordinatesRequestDTO dto) {
        Coordinates entity = mapper.toEntity(dto);
        repository.update(entity);
    }

    @Transactional
    public void delete(IdRequestDTO dto) {
        repository.deleteByKey(dto.getId());
    }

    @Transactional
    public List<CoordinatesResponseDTO> getAll() {
        return repository.getAll().stream().map(mapper::toDTO).toList();
    }
}
