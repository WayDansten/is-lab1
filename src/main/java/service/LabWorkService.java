package service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import dto.labwork.LabWorkRequestDTO;
import dto.labwork.LabWorkResponseDTO;
import entity.LabWork;
import entity.types.Difficulty;
import events.EventPublisher;
import exception.DifficultyException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    private EventPublisher eventPublisher;

    @Inject
    public LabWorkService(LabWorkMapper mapper, LabWorkRepository repository, ChangeTrackerService trackerService, EventPublisher eventPublisher) {
        this.mapper = mapper;
        this.repository = repository;
        this.trackerService = trackerService;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void create(LabWorkRequestDTO dto) {
        Set<WebSocketMessageType> changedTypes = trackerService.trackChanges(dto);
        LabWork entity = mapper.toEntity(dto);
        repository.save(entity);
        eventPublisher.fireEvent(changedTypes);
    }

    @Transactional
    public void update(LabWorkRequestDTO dto) {
        Set<WebSocketMessageType> changedTypes = trackerService.trackChanges(dto);
        LabWork entity = mapper.toEntity(dto);
        repository.update(entity);
        eventPublisher.fireEvent(changedTypes);
    }

    @Transactional
    public void lowerDifficulty(Integer id, Integer steps) {
        Set<WebSocketMessageType> changedTypes = new HashSet<>();
        changedTypes.add(WebSocketMessageType.LABWORK);
        LabWork entity = repository.getByKey(id).orElseThrow(() -> new EntityNotFoundException());
        if (entity.getDifficulty().getValue() - steps <= 0) {
            throw new DifficultyException();
        }
        Difficulty newDifficulty = Difficulty.getByValue(entity.getDifficulty().getValue() - steps);
        entity.setDifficulty(newDifficulty);
        repository.update(entity);
        eventPublisher.fireEvent(changedTypes);
    }

    @Transactional
    public void delete(Integer id) {
        Set<WebSocketMessageType> changedTypes = new HashSet<>();
        changedTypes.add(WebSocketMessageType.LABWORK);
        repository.deleteByKey(id);
        eventPublisher.fireEvent(changedTypes);
    }

    @Transactional
    public void deleteByAuthor(String author) {
        Set<WebSocketMessageType> changedTypes = new HashSet<>();
        changedTypes.add(WebSocketMessageType.LABWORK);
        List<LabWork> labWorks = repository.getByAuthor(author);
        if (labWorks.isEmpty()) {
            throw new EntityNotFoundException();
        }
        repository.delete(labWorks.get(0));
        eventPublisher.fireEvent(changedTypes);
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
