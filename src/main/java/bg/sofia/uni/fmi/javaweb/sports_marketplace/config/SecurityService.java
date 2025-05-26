package bg.sofia.uni.fmi.javaweb.sports_marketplace.config;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Event;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.EventService;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {
    private EventService eventService;
    @Autowired
    public SecurityService(EventService eventService){
        this.eventService=eventService;
    }
    public boolean isOwnerOfEvent(Long eventId, String email) {
        Event event = eventService.getEventById(eventId);
        return event.getUser().getEmail().equals(email);
    }
}
