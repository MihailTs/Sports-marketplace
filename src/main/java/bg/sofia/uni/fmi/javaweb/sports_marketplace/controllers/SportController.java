package bg.sofia.uni.fmi.javaweb.sports_marketplace.controllers;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Sport;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/sports")
public class SportController {

    private SportService sportService;
    @Autowired
    public SportController(SportService sportService){
        this.sportService = sportService;
    }
    @GetMapping
    public ResponseEntity<List<Sport>> getAll(){
        return ResponseEntity.ok(sportService.getAllSports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sport>> getById(@PathVariable UUID id){
        return ResponseEntity.ok(sportService.getSportById(id));
    }

}
