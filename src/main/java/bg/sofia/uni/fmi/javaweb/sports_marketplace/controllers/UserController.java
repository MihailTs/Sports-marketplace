package bg.sofia.uni.fmi.javaweb.sports_marketplace.controllers;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.UserDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController userController(UserService userService){
        this.userService=userService;
    }
    
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id){
        return userService.getUserById(id).map(UserDto::fromEntity).orElseThrow();
    }
}
