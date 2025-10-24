package service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import dto.labwork.LabWorkRequestDTO;
import dto.labwork.LabWorkResponseDTO;
import entity.LabWork;
import entity.types.Difficulty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.NoArgsConstructor;
import mapper.LabWorkMapper;
import repository.LabWorkRepository;
import websocket.WebSocketMessageType;

@ApplicationScoped
@NoArgsConstructor
public class LabWorkService {
    private LabWorkMapper mapper;
    private LabWorkRepository repository;
    private ChangeTrackerService trackerService;

    @Inject
    public LabWorkService(LabWorkMapper mapper, LabWorkRepository repository, ChangeTrackerService trackerService) {
        this.mapper = mapper;
        this.repository = repository;
        this.trackerService = trackerService;
    }

    @Transactional
    public Set<WebSocketMessageType> create(LabWorkRequestDTO dto) {
        Set<WebSocketMessageType> changedTypes = trackerService.trackChanges(dto);
        LabWork entity = mapper.toEntity(dto);
        repository.save(entity);
        return changedTypes;
    }

    @Transactional
    public Set<WebSocketMessageType> update(LabWorkRequestDTO dto) {
        Set<WebSocketMessageType> changedTypes = trackerService.trackChanges(dto);
        LabWork entity = mapper.toEntity(dto);
        repository.update(entity);
        return changedTypes;
    }

    @Transactional
    public void lowerDifficulty(Integer id, Integer steps) {
        LabWork entity = repository.getByKey(id);
        if (entity.getDifficulty().getValue() - steps <= 0) {
            throw new ConstraintViolationException(null, null);
        }
        Difficulty newDifficulty = Difficulty.getByValue(entity.getDifficulty().getValue() - steps);
        entity.setDifficulty(newDifficulty);
        repository.update(entity);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteByKey(id);
    }

    @Transactional
    public void deleteByAuthor(String author) {
        List<LabWork> labWorks = repository.getByAuthor(author);
        if (labWorks.isEmpty()) {
            throw new EntityNotFoundException();
        }
        repository.delete(labWorks.get(0));
    }

    @Transactional
    public List<LabWorkResponseDTO> getAll() {
        return repository.getAll().stream().filter(Objects::nonNull).map(mapper::toDTO).toList();
    }

    @Transactional
    public Long countGreaterThanAveragePoint(Float averagePoint) {
        return repository.countGreaterThanAveragePoint(averagePoint);
    }

    @Transactional
    public List<String> getByDescription(String prefix) {
        return repository.getByDescription(prefix);
    }
}
