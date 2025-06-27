package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Sport;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.SportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SportService {
    private SportRepository sportRepository;

    public SportService(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    public List<Sport> getAllSports(){
        return sportRepository.findAll();
    }

    public Optional<Sport> getSportById(UUID sportId) {
        return sportRepository.findById(sportId);
    }

}
