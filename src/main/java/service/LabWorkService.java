package service;

import java.util.List;

import dto.IdRequestDTO;
import dto.labwork.LabWorkRequestDTO;
import dto.labwork.LabWorkResponseDTO;
import entity.LabWork;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mapper.LabWorkMapper;
import repository.LabWorkRepository;

@ApplicationScoped
public class LabWorkService {
    private LabWorkMapper mapper;
    private LabWorkRepository dao;

    @Inject
    public LabWorkService(LabWorkMapper mapper, LabWorkRepository dao) {
        this.mapper = mapper;
        this.dao = dao;
    }

    @Transactional
    public void create(LabWorkRequestDTO dto) {
        LabWork entity = mapper.toEntity(dto);
        dao.save(entity);
    }

    @Transactional
    public void update(LabWorkRequestDTO dto) {
        LabWork entity = mapper.toEntity(dto);
        dao.update(entity);
    }

    @Transactional
    public void delete(IdRequestDTO dto) {
        dao.deleteByKey(dto.getId());
    }

    public List<LabWorkResponseDTO> getAll() {
        return dao.getAll().stream().map(mapper::toDTO).toList();
    }
}
