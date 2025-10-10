package service;

import java.util.List;

import dto.coordinates.CoordinatesRequestDTO;
import dto.coordinates.CoordinatesResponseDTO;
import entity.Coordinates;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import mapper.CoordinatesMapper;
import repository.CoordinatesRepository;

@ApplicationScoped
@NoArgsConstructor
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
    public void delete(Integer id) {
        repository.deleteByKey(id);
    }

    @Transactional
    public List<CoordinatesResponseDTO> getAll() {
        return repository.getAll().stream().map(mapper::toDTO).toList();
    }
}
