package service;

import java.util.List;

import dto.IdRequestDTO;
import dto.location.LocationRequestDTO;
import dto.location.LocationResponseDTO;
import entity.Location;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.LocationMapper;
import repository.LocationRepository;

public class LocationService {
    private LocationMapper mapper;
    private LocationRepository repository;

    @Inject
    public LocationService(LocationMapper mapper, LocationRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional
    public void create(LocationRequestDTO dto) {
        Location entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Transactional
    public void update(LocationRequestDTO dto) {
        Location entity = mapper.toEntity(dto);
        repository.update(entity);
    }

    @Transactional
    public void delete(IdRequestDTO dto) {
        repository.deleteByKey(dto.getId());
    }

    @Transactional
    public List<LocationResponseDTO> getAll() {
        return repository.getAll().stream().map(mapper::toDTO).toList();
    }

}
