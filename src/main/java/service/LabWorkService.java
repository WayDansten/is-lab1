package service;

import java.util.List;

import dto.IdRequestDTO;
import dto.labwork.LabWorkRequestDTO;
import dto.labwork.LabWorkResponseDTO;
import entity.LabWork;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import mapper.LabWorkMapper;
import repository.LabWorkRepository;

@ApplicationScoped
@NoArgsConstructor
public class LabWorkService {
    private LabWorkMapper mapper;
    private LabWorkRepository repository;

    @Inject
    public LabWorkService(LabWorkMapper mapper, LabWorkRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional
    public void create(LabWorkRequestDTO dto) {
        LabWork entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Transactional
    public void update(LabWorkRequestDTO dto) {
        LabWork entity = mapper.toEntity(dto);
        repository.update(entity);
    }

    @Transactional
    public void delete(IdRequestDTO dto) {
        repository.deleteByKey(dto.getId());
    }

    @Transactional
    public List<LabWorkResponseDTO> getAll() {
        return repository.getAll().stream().map(mapper::toDTO).toList();
    }
}
