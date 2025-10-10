package service;

import java.util.List;
import java.util.Objects;

import dto.labwork.LabWorkRequestDTO;
import dto.labwork.LabWorkResponseDTO;
import entity.LabWork;
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
    public void delete(Integer id) {
        repository.deleteByKey(id);
        WebSocketNotifier.broadcast(WebSocketMessageType.LABWORK);
    }

    @Transactional
    public List<LabWorkResponseDTO> getAll() {
        return repository.getAll().stream().filter(Objects::nonNull).map(mapper::toDTO).toList();
    }
}
