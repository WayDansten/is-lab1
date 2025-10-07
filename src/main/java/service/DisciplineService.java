package service;

import java.util.List;

import dto.IdRequestDTO;
import dto.discipline.DisciplineRequestDTO;
import dto.discipline.DisciplineResponseDTO;
import entity.Discipline;
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
    public void create(DisciplineRequestDTO dto) {
        Discipline entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Transactional
    public void update(DisciplineRequestDTO dto) {
        Discipline entity = mapper.toEntity(dto);
        repository.update(entity);
    }

    @Transactional
    public void delete(IdRequestDTO dto) {
        repository.deleteByKey(dto.getId());
    }

    @Transactional
    public List<DisciplineResponseDTO> getAll() {
        return repository.getAll().stream().map(mapper::toDTO).toList();
    }

}
