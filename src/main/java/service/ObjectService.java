package service;

import java.util.List;

import dao.LabWorkDAO;
import dto.IdRequestDTO;
import dto.LabWorkDTO;
import entity.LabWork;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import util.LabWorkMapper;

@ApplicationScoped
public class ObjectService {
    private LabWorkMapper mapper;
    private LabWorkDAO dao;

    @Inject
    public ObjectService(LabWorkMapper mapper, LabWorkDAO dao) {
        this.mapper = mapper;
        this.dao = dao;
    }

    @Transactional
    public void create(LabWorkDTO dto) {
        LabWork entity = mapper.toEntity(dto);
        dao.save(entity);
    }

    @Transactional
    public void update(LabWorkDTO dto) {
        LabWork entity = mapper.toEntity(dto);
        dao.update(entity);
    }

    @Transactional
    public void delete(IdRequestDTO dto) {
        dao.deleteByID(dto.getId());
    }

    public List<LabWorkDTO> getAll() {
        return dao.getAll().stream().map(mapper::toDTO).toList();
    }
}
