package bg.sofia.uni.fmi.javaweb.sports_marketplace.controllers;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.event.EventDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Event;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.EventParticipant;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Status;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.EventParticipantService;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private EventService eventService;
    private EventParticipantService eventParticipantService;
    @Autowired
    public EventController(EventService eventService, EventParticipantService eventParticipantService){
        this.eventService=eventService;
        this.eventParticipantService=eventParticipantService;
    }
    @GetMapping
    public ResponseEntity<List<Event>> getAll(){
        return ResponseEntity.ok(eventService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable UUID id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody EventDto eventDto, Authentication authentication){
        String email=authentication.getName();
        return ResponseEntity.ok(eventService.saveEvent(eventDto, email));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable UUID id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable UUID id, @RequestBody EventDto eventDto){
        return ResponseEntity.ok(EventDto.fromEntity(eventService.updateEvent(id, eventDto)));
    }

    @GetMapping("{id}/participants")
    public ResponseEntity<List<EventParticipant>> getAllParticipants(@PathVariable UUID id){
        return ResponseEntity.ok(eventService.getParticipantsForEvent(id));
    }

    @GetMapping("{id}/participants/registered")
    public ResponseEntity<List<EventParticipant>> getRegisteredParticipants(@PathVariable UUID id){
        return ResponseEntity.ok(eventService.getParticipantsForEvent(id).stream().filter(eventParticipant -> eventParticipant.getStatus().equals(Status.REGISTERED)).toList());
    }

    @GetMapping("{id}/participants/wait_listed")
    public ResponseEntity<List<EventParticipant>> getWaitListedParticipants(@PathVariable UUID id){
        return ResponseEntity.ok(eventService.getParticipantsForEvent(id).stream().filter(eventParticipant -> eventParticipant.getStatus().equals(Status.WAIT_LISTED)).toList());
    }

    @PatchMapping("{eventId}/participants/{userId}")
    public ResponseEntity<EventParticipant> setUserInterested(@PathVariable UUID eventId, @PathVariable UUID userId){
        return ResponseEntity.ok(eventParticipantService.setInterested(userId, eventId));
    }

    @PatchMapping("{eventId}/participants/{userId}/register")
    public ResponseEntity<EventParticipant> registerUser(@PathVariable UUID eventId, @PathVariable UUID userId){
        return ResponseEntity.ok(eventParticipantService.joinEvent(userId, eventId));
    }

    @DeleteMapping("{eventId}/participants/{userId}")
    public ResponseEntity<EventParticipant> setNotInterested(@PathVariable UUID eventId, @PathVariable UUID userId){
        eventParticipantService.deleteParticipant(userId, eventId);
        return ResponseEntity.noContent().build();
    }

}