package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.NoSuchEventException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UserDoesntExistException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Event;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.EventParticipant;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Status;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.EventParticipantRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.EventRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EventParticipantService {
    private final EventParticipantRepository eventParticipantRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public EventParticipantService(EventParticipantRepository eventParticipantRepository, UserRepository userRepository, EventRepository eventRepository){
        this.eventParticipantRepository=eventParticipantRepository;
        this.userRepository=userRepository;
        this.eventRepository=eventRepository;
    }

    public EventParticipant joinEvent(UUID userId, UUID eventId){
        User user=userRepository.findById(userId).orElseThrow(UserDoesntExistException::new);
        Event event=eventRepository.findById(eventId).orElseThrow(NoSuchEventException::new);

        Optional<EventParticipant> eventParticipant=eventParticipantRepository.findEventParticipantsByEvent_IdAndUser_Id(eventId, userId);

        if(eventParticipant.isEmpty()){
            eventParticipant=Optional.of(eventParticipantRepository.save(new EventParticipant(user, event, Status.REGISTERED)));

        } else {
            eventParticipant.get().setStatus(Status.REGISTERED);
        }
        return eventParticipant.get();
    }

    public EventParticipant setInterested(UUID userId, UUID eventId){
        User user=userRepository.findById(userId).orElseThrow(UserDoesntExistException::new);
        Event event=eventRepository.findById(eventId).orElseThrow(NoSuchEventException::new);


        Optional<EventParticipant> eventParticipant=eventParticipantRepository.findEventParticipantsByEvent_IdAndUser_Id(eventId, userId);

        if(eventParticipant.isEmpty()){
            eventParticipant=Optional.of(eventParticipantRepository.save(new EventParticipant(user, event, Status.INTERESTED)));

        } else {
            eventParticipant.get().setStatus(Status.INTERESTED);
        }
        return eventParticipant.get();

    }

    public void deleteParticipant(UUID userId, UUID eventId){
        if(!eventRepository.existsById(eventId)){
            throw new NoSuchEventException();
        }
        if (userRepository.existsById(userId)){
            throw new UserDoesntExistException();
        }
        EventParticipant eventParticipant=eventParticipantRepository.findEventParticipantsByEvent_IdAndUser_Id(eventId, userId).orElseThrow();
        eventParticipantRepository.delete(eventParticipant);
    }



}
