package bg.sofia.uni.fmi.javaweb.sports_marketplace.controllers;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.UserDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.UserLoginDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.UserRegistrationDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UnAuthorizedAccessException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.jwt_util.JWTUtil;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private JWTUtil jwtUtil;
    private AuthenticationManager authManager;

    @Autowired
    public UserController(UserService userService, JWTUtil jwtUtil, AuthenticationManager authManager){
        this.userService=userService;
        this.jwtUtil=jwtUtil;
        this.authManager=authManager;
    }
    
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id){
        return userService.getUserById(id).map(UserDto::fromEntity).orElseThrow();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> changeUser(@PathVariable Long id, @RequestBody UserDto userDto){
        Optional<User> user=userService.getUserById(id);
        return ResponseEntity.ok(UserDto.fromEntity(userService.updateUser(user, userDto)));
    }


    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto){
        Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.email(), userLoginDto.password()));
        User user=userService.login(userLoginDto.email(), userLoginDto.password());
        return ResponseEntity.ok(jwtUtil.generateToken(user.getEmail(), user.getId()));
    }


    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDto userRegDto) {
        User user = userService.register(
                userRegDto.firstName(),
                userRegDto.lastName(),
                userRegDto.email(),
                userRegDto.password(),
                userRegDto.confirmPassword(),
                userRegDto.birthdate(),
                userRegDto.phone(),
                userRegDto.gender(),
                userRegDto.role() == null ? "user" : userRegDto.role()
        );

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRegDto.email(), userRegDto.password())
        );

        return ResponseEntity.ok(jwtUtil.generateToken(user.getEmail(), user.getId()));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers().stream().map(UserDto::fromEntity).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
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
    public ResponseEntity<UserDto> updateSelf(@RequestBody UserDto userDto, Authentication authentication){
        String email=authentication.getName();
        Optional<User> user=userService.getUserByEmail(email);


        return ResponseEntity.ok(UserDto.fromEntity(userService.updateUser(user, userDto)));
    }

}
