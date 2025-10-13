package service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import dto.labwork.LabWorkRequestDTO;
import dto.labwork.LabWorkResponseDTO;
import entity.LabWork;
import entity.types.Difficulty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import mapper.LabWorkMapper;
import repository.LabWorkRepository;
import websocket.WebSocketMessageType;
import websocket.WebSocketNotifier;

@ApplicationScoped
@NoArgsConstructor
public class LabWorkService {
    private LabWorkMapper mapper;
    private LabWorkRepository repository;
    private final Random random = new Random();

    @Inject
    public LabWorkService(LabWorkMapper mapper, LabWorkRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional
    public void create(LabWorkRequestDTO dto) {
        LabWork entity = mapper.toEntity(dto);
        repository.save(entity);
        WebSocketNotifier.broadcast(WebSocketMessageType.LABWORK);
    }

    @Transactional
    public void update(LabWorkRequestDTO dto) {
        LabWork entity = mapper.toEntity(dto);
        repository.update(entity);
        WebSocketNotifier.broadcast(WebSocketMessageType.LABWORK);
    }

    @Transactional
    public void lowerDifficulty(Integer id, Difficulty difficulty) {
        LabWork entity = repository.getByKey(id);
        entity.setDifficulty(difficulty);
        repository.update(entity);
        WebSocketNotifier.broadcast(WebSocketMessageType.LABWORK);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteByKey(id);
        WebSocketNotifier.broadcast(WebSocketMessageType.LABWORK);
    }

    @Transactional
    public void deleteByAuthor(String author) {
        List<LabWork> labWorks = repository.getByAuthor(author);
        int id = random.nextInt(labWorks.size());
        repository.deleteByKey(id);
        WebSocketNotifier.broadcast(WebSocketMessageType.LABWORK);
    }

    @Transactional
    public List<LabWorkResponseDTO> getAll() {
        return repository.getAll().stream().filter(Objects::nonNull).map(mapper::toDTO).toList();
    }

    @Transactional
    public Long countGreaterThanAveragePoint(Float averagePoint) {
        return repository.countGreaterThanAveragePoint(averagePoint);
    }
}
