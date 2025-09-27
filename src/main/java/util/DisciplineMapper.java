package util;

import dto.DisciplineDTO;
import entity.Discipline;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DisciplineMapper {
    public DisciplineDTO toDTO(Discipline entity) {
        return new DisciplineDTO(entity.getName(), entity.getPracticeHours());
    }

    public Discipline toEntity(DisciplineDTO dto) {
        Discipline entity = new Discipline();
        entity.setName(dto.getName());
        entity.setPracticeHours(dto.getPracticeHours());
        return entity;
    }
}
