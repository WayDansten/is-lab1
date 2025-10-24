package service;

import java.util.List;

import dto.discipline.DisciplineResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import mapper.DisciplineMapper;
import repository.DisciplineRepository;

@ApplicationScoped
@NoArgsConstructor
public class DisciplineService {
    private DisciplineMapper mapper;
    private DisciplineRepository repository;

    @Inject
    public DisciplineService(DisciplineMapper mapper, DisciplineRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional
    public List<DisciplineResponseDTO> getAll() {
        return repository.getAll().stream().map(mapper::toDTO).toList();
    }

}
