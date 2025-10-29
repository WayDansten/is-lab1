package mapper;

import dto.person.PersonRequestDTO;
import dto.person.PersonResponseDTO;
import entity.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PersonMapper {

    private LocationMapper locationMapper;

    @Inject
    public PersonMapper() {
        this.locationMapper = new LocationMapper();
    }

    public PersonResponseDTO toDTO(Person entity) {
        return new PersonResponseDTO(entity.getId(), entity.getName(), entity.getEyeColor(), entity.getHairColor(),
                locationMapper.toDTO(entity.getLocation()), entity.getBirthday(), entity.getNationality());
    }

    public Person toEntity(PersonRequestDTO dto) {
        Person entity = new Person();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entity.setName(dto.getName());
        entity.setEyeColor(dto.getEyeColor());
        entity.setHairColor(dto.getHairColor());
        entity.setLocation(locationMapper.toEntity(dto.getLocation()));
        entity.setBirthday(dto.getBirthday());
        entity.setNationality(dto.getNationality());
        return entity;
    }
}
