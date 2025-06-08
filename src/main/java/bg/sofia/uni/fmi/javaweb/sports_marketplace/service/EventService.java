package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.event.EventDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.NoSuchEventException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.TimeframeMismatchException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UserDoesntExistException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Event;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.EventParticipant;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.EventParticipantRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.EventRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {
    private UserRepository userRepository;
    private EventRepository eventRepository;
    private EventParticipantRepository eventParticipantRepository;

    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository, EventParticipantRepository eventParticipantRepository) {
        this.eventParticipantRepository=eventParticipantRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event getEventById(UUID id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new NoSuchEventException();
        }
        return event.get();
    }

    public Event saveEvent(EventDto eventDto, String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserDoesntExistException();
        }
        if (eventDto.startTime().isAfter(eventDto.endTime())) {
            throw new TimeframeMismatchException();
        }
        return eventRepository.save(new Event(eventDto.title(), eventDto.description(), eventDto.location(), eventDto.startTime(), eventDto.endTime(), user.get(), eventDto.sport()));
    }

    public void deleteEvent(UUID id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new NoSuchEventException();
        }
        //if(!user.get().equals(event.get().getUser())){
        //    throw new UnAuthorizedAccessException();
        //}
        eventRepository.delete(event.get());
    }

    public Event updateEvent(UUID id, EventDto eventDto) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new NoSuchEventException();
        }
        Event eventToChange = event.get();
        if (eventDto.startTime() != null) {
            eventToChange.setStartTime(eventDto.startTime());
        }
        if (eventDto.endTime() != null) {
            eventToChange.setEndTime(eventDto.endTime());
        }
        if (eventDto.location() != null) {
            eventToChange.setLocation(eventDto.location());
        }
        if (eventDto.title() != null) {
            eventToChange.setTitle(eventDto.title());
        }
        if (eventDto.description() != null) {
            eventToChange.setDescription(eventDto.description());
        }

        return eventRepository.save(eventToChange);
    }
    public List<Event> getEventsByUserId(UUID id){
        return eventRepository.findByCreatedById(id);
    }

    public List<EventParticipant> getParticipantsForEvent(UUID eventId) {
        Optional<Event> event = eventRepository.findById(eventId);

        if (event.isEmpty()) {
            throw new NoSuchEventException();
        }

        return eventParticipantRepository.findAllByEventId(eventId);
    }
}
