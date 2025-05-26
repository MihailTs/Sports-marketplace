package bg.sofia.uni.fmi.javaweb.sports_marketplace.controllers;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.event.EventDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Event;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private EventService eventService;
    @Autowired
    public EventController(EventService eventService){
        this.eventService=eventService;
    }
    @GetMapping
    public ResponseEntity<List<Event>> getAll(){
        return ResponseEntity.ok(eventService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody EventDto eventDto, Authentication authentication){
        String email=authentication.getName();
        return ResponseEntity.ok(eventService.saveEvent(eventDto, email));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto){
        return ResponseEntity.ok(EventDto.fromEntity(eventService.updateEvent(id, eventDto)));
    }

}
