package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.event;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Event;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Sport;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record EventDto(@NotBlank String title, String description, @NotBlank String location, @NotNull LocalDateTime startTime, @NotNull LocalDateTime endTime, Sport sport, int capacity) {
    public static EventDto fromEntity(Event event){
        return new EventDto(event.getTitle(), event.getDescription(), event.getLocation(), event.getStartTime(), event.getEndTime(), event.getSport(), event.getCapacity());
    }
}
