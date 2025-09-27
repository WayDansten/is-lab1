package util;

import dto.PersonDTO;
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

    public PersonDTO toDTO(Person entity) {
        return new PersonDTO(entity.getName(), entity.getEyeColor(), entity.getHairColor(),
                locationMapper.toDTO(entity.getLocation()), entity.getBirthday(), entity.getNationality());
    }

    public Person toEntity(PersonDTO dto) {
        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setEyeColor(dto.getEyeColor());
        entity.setHairColor(dto.getHairColor());
        entity.setLocation(locationMapper.toEntity(dto.getLocation()));
        entity.setBirthday(dto.getBirthday());
        entity.setNationality(dto.getNationality());
        return entity;
    }
}
