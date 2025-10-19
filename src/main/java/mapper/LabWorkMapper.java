package mapper;

import dto.labwork.LabWorkRequestDTO;
import dto.labwork.LabWorkResponseDTO;
import entity.LabWork;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LabWorkMapper {

    private PersonMapper personMapper;
    private DisciplineMapper disciplineMapper;
    private CoordinatesMapper coordinatesMapper;

    @Inject
    public LabWorkMapper() {
        this.personMapper = new PersonMapper();
        this.disciplineMapper = new DisciplineMapper();
        this.coordinatesMapper = new CoordinatesMapper();
    }

    public LabWorkResponseDTO toDTO(LabWork entity) {
        return new LabWorkResponseDTO(
                entity.getId(),
                entity.getName(),
                coordinatesMapper.toDTO(entity.getCoordinates()),
                entity.getCreationDate(),
                entity.getDescription(),
                entity.getDifficulty(),
                disciplineMapper.toDTO(entity.getDiscipline()),
                entity.getMinimalPoint(),
                entity.getAveragePoint(),
                personMapper.toDTO(entity.getAuthor()));
    }

    public LabWork toEntity(LabWorkRequestDTO dto) {
        LabWork entity = new LabWork();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
            entity.setCreationDate(dto.getCreationDate());
        }
        entity.setName(dto.getName());
        entity.setCoordinates(coordinatesMapper.toEntity(dto.getCoordinates()));
        entity.setDescription(dto.getDescription());
        entity.setDifficulty(dto.getDifficulty());
        entity.setDiscipline(disciplineMapper.toEntity(dto.getDiscipline()));
        entity.setMinimalPoint(dto.getMinimalPoint());
        entity.setAveragePoint(dto.getAveragePoint());
        entity.setAuthor(personMapper.toEntity(dto.getAuthor()));
        return entity;
    }
}
