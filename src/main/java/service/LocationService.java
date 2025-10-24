package service;

import java.util.List;

import dto.location.LocationResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import mapper.LocationMapper;
import repository.LocationRepository;

@ApplicationScoped
@NoArgsConstructor
public class LocationService {
    private LocationMapper mapper;
    private LocationRepository repository;

    @Inject
    public LocationService(LocationMapper mapper, LocationRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional
    public List<LocationResponseDTO> getAll() {
        return repository.getAll().stream().map(mapper::toDTO).toList();
    }

}
