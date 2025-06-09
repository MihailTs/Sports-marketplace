package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum.ForumCreationDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum.ForumDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.NoSuchForumException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Forum;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Sport;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.ForumPostRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.ForumRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForumService {
    private ForumRepository forumRepository;
    private SportRepository sportRepository;

    @Autowired
    public ForumService(ForumRepository forumRepository, SportRepository sportRepository){
        this.forumRepository=forumRepository;
        this.sportRepository=sportRepository;
    }

    public Page<Forum> getAllForums(Pageable pageable){
        return forumRepository.findAll(pageable);
    }

    public Forum createForum(ForumCreationDto forumDto){
        Optional<Sport> sport=sportRepository.findByName(forumDto.sport());
        if(sport.isEmpty()){
            sport=Optional.of(new Sport(forumDto.sport()));
        }
        return forumRepository.save(new Forum(forumDto.title(), forumDto.description(), sport.get()));
    }

    public void deleteForum(UUID id){
        if(!forumRepository.existsById(id)){
            throw new NoSuchForumException();
        }
        forumRepository.deleteById(id);
    }
}
