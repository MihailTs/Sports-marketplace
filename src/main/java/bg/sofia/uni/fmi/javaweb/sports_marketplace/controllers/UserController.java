package bg.sofia.uni.fmi.javaweb.sports_marketplace.controllers;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.LoginResponseDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.event.EventDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.UserDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.UserLoginDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.UserRegistrationDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UnAuthorizedAccessException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UserDoesntExistException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.jwt_util.JWTUtil;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Event;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Notification;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Role;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.NotificationRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.EventService;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.NotificationService;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private EventService eventService;
    private JWTUtil jwtUtil;
    private AuthenticationManager authManager;
    private NotificationService notificationService;

    @Autowired
    public UserController(UserService userService, JWTUtil jwtUtil, AuthenticationManager authManager, EventService eventService, NotificationService notificationService){
        this.userService=userService;
        this.jwtUtil=jwtUtil;
        this.authManager=authManager;
        this.eventService=eventService;
        this.notificationService=notificationService;
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable UUID id){
        return userService.getUserById(id).map(UserDto::fromEntity).orElseThrow(()->new UserDoesntExistException("user with id:"+id +" doesn't exist"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> changeUser(@PathVariable UUID id, @RequestBody UserDto userDto){
        Optional<User> user=userService.getUserById(id);
        return ResponseEntity.ok(UserDto.fromEntity(userService.updateUser(user, userDto)));
    }


    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody UserLoginDto userLoginDto){
        Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.email(), userLoginDto.password()));
        User user=userService.login(userLoginDto.email(), userLoginDto.password());
        LoginResponseDto response = new LoginResponseDto(
                jwtUtil.generateToken(user),
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getProfileImageUrl()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody UserRegistrationDto userRegDto){
        User user=userService.register(
                userRegDto.firstName(),
                userRegDto.lastName(),
                userRegDto.email(),
                userRegDto.password(),
                userRegDto.confirmPassword(),
                userRegDto.role()==null? Role.USER:userRegDto.role(),
                userRegDto.imageUrl(),
                userRegDto.gender(),
                userRegDto.phoneNumber(),
                userRegDto.addressCreateDto(),
                userRegDto.birthdate()
        );
        return ResponseEntity.ok(Map.of("token", jwtUtil.generateToken(user)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers().stream().map(UserDto::fromEntity).toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        Optional<User> user=userService.getUserById(id);
        userService.deleteUser(user);
        return ResponseEntity.ok("Successfully deleted");
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteSelf(Authentication authentication){
        String email=authentication.getName();
        Optional<User> user=userService.getUserByEmail(email);

        userService.deleteUser(user);
        return ResponseEntity.ok("Successfully deleted");
    }

    @PutMapping("/me")
    public ResponseEntity<UserDto> updateSelf(@RequestBody UserDto userDto, Authentication authentication) {
        String email = authentication.getName();
        Optional<User> user = userService.getUserByEmail(email);


        return ResponseEntity.ok(UserDto.fromEntity(userService.updateUser(user, userDto)));
    }

    @DeleteMapping("/me/events/{id}")
    @PreAuthorize("@securityService.isOwnerOfEvent(#id, principal.username)")
    public ResponseEntity<String> deleteEvent(@PathVariable UUID id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Successfully deleted.");
    }

    @PutMapping("/me/events/{id}")
    @PreAuthorize("@securityService.isOwnerOfEvent(#id, principal.username)")
    public ResponseEntity<String> updateEvent(@PathVariable UUID id, @RequestBody EventDto eventDto){
        eventService.updateEvent(id, eventDto);
        return ResponseEntity.ok("Successfully deleted.");
    }

    @GetMapping("/{id}/events")
    public ResponseEntity<List<EventDto>> getEventsForUser(@PathVariable UUID id){
        return ResponseEntity.ok(eventService.getEventsByUserId(id).stream().map(EventDto::fromEntity).toList());
    }

    @GetMapping("/em/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(UserDto.fromEntity(userService.getUserByEmail(email).get()));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getSelf(Authentication authentication){
        return ResponseEntity.ok(UserDto.fromEntity(userService.getUserByEmail(authentication.getName()).get()));
    }

    @GetMapping("/{userId}/notifications")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable UUID userId){
        return ResponseEntity.ok(notificationService.getAllNotifications(userId));
    }
}
