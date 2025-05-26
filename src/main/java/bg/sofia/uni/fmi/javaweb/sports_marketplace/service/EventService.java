package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.event.EventDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.NoSuchEventException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.TimeframeMismatchException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UnAuthorizedAccessException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UserDoesntExistException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Event;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.EventRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.UserRepository;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private UserRepository userRepository;
    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
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
        return eventRepository.save(new Event(eventDto.title(), eventDto.description(), eventDto.location(), eventDto.startTime(), eventDto.endTime(), user.get()));
    }

    public void deleteEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new NoSuchEventException();
        }
        //if(!user.get().equals(event.get().getUser())){
        //    throw new UnAuthorizedAccessException();
        //}
        eventRepository.delete(event.get());
    }

    public Event updateEvent(Long id, EventDto eventDto) {
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
    public List<Event> getEventSByUserId(Long id){
        return eventRepository.findByUserId(id);
    }
}
