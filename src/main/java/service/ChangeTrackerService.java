package service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import dto.coordinates.CoordinatesRequestDTO;
import dto.discipline.DisciplineRequestDTO;
import dto.labwork.LabWorkRequestDTO;
import dto.location.LocationRequestDTO;
import dto.person.PersonRequestDTO;
import entity.Coordinates;
import entity.Discipline;
import entity.Location;
import entity.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import repository.CoordinatesRepository;
import repository.DisciplineRepository;
import repository.LocationRepository;
import repository.PersonRepository;
import websocket.WebSocketMessageType;

@ApplicationScoped
@NoArgsConstructor
public class ChangeTrackerService {
    private CoordinatesRepository coordinatesRepository;
    private DisciplineRepository disciplineRepository;
    private PersonRepository personRepository;
    private LocationRepository locationRepository;

    @Inject
    public ChangeTrackerService(
            CoordinatesRepository coordinatesRepository,
            DisciplineRepository disciplineRepository,
            PersonRepository personRepository,
            LocationRepository locationRepository) {
        this.coordinatesRepository = coordinatesRepository;
        this.disciplineRepository = disciplineRepository;
        this.personRepository = personRepository;
        this.locationRepository = locationRepository;
    }

    public Set<WebSocketMessageType> trackChanges(LabWorkRequestDTO labWorkDTO) {
        Set<WebSocketMessageType> changedTypes = new HashSet<>();
        changedTypes.add(WebSocketMessageType.LABWORK);

        if (labWorkDTO.getCoordinates().getId() == null) {
            changedTypes.add(WebSocketMessageType.COORDINATES);
        } else {
            CoordinatesRequestDTO dto = labWorkDTO.getCoordinates();
            Coordinates entity = coordinatesRepository.getByKey(dto.getId());
            if (!(Objects.equals(dto.getX(), entity.getX())
                    && Objects.equals(dto.getY(), entity.getY()))) {
                changedTypes.add(WebSocketMessageType.COORDINATES);
            }
        }

        if (labWorkDTO.getDiscipline().getId() == null) {
            changedTypes.add(WebSocketMessageType.DISCIPLINE);
        } else {
            DisciplineRequestDTO dto = labWorkDTO.getDiscipline();
            Discipline entity = disciplineRepository.getByKey(dto.getId());
            if (!(Objects.equals(dto.getName(), entity.getName())
                    && Objects.equals(dto.getPracticeHours(), entity.getPracticeHours()))) {
                changedTypes.add(WebSocketMessageType.DISCIPLINE);
            }
        }

        if (labWorkDTO.getAuthor().getId() == null) {
            changedTypes.add(WebSocketMessageType.PERSON);
        } else {
            PersonRequestDTO dto = labWorkDTO.getAuthor();
            Person entity = personRepository.getByKey(dto.getId());
            if (!(Objects.equals(dto.getName(), entity.getName())
                    && Objects.equals(dto.getBirthday(), entity.getBirthday())
                    && Objects.equals(dto.getEyeColor(), entity.getEyeColor())
                    && Objects.equals(dto.getHairColor(), entity.getHairColor())
                    && Objects.equals(dto.getNationality(), entity.getNationality())
                    && Objects.equals(dto.getLocation().getId(), entity.getLocation().getId()))) {
                changedTypes.add(WebSocketMessageType.PERSON);
            }
        }

        if (labWorkDTO.getAuthor().getLocation().getId() == null) {
            changedTypes.add(WebSocketMessageType.LOCATION);
            changedTypes.add(WebSocketMessageType.PERSON);
        } else {
            LocationRequestDTO dto = labWorkDTO.getAuthor().getLocation();
            Location entity = locationRepository.getByKey(dto.getId());
            if (!(Objects.equals(dto.getName(), entity.getName())
                    && Objects.equals(dto.getX(), entity.getX())
                    && Objects.equals(dto.getY(), entity.getY())
                    && Objects.equals(dto.getZ(), entity.getZ()))) {
                changedTypes.add(WebSocketMessageType.LOCATION);
                changedTypes.add(WebSocketMessageType.PERSON);
            }
        }

        return changedTypes;
    }
}
