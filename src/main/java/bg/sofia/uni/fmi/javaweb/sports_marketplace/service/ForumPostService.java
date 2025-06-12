package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum.ForumDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum.ForumPostCreationDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.NoSuchForumException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.NoSuchForumPostException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UserDoesntExistException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Forum;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ForumPost;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.ForumPostRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.ForumRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForumPostService {
    private ForumPostRepository forumPostRepository;
    private ForumRepository forumRepository;
    private UserRepository userRepository;
    public ForumPostService(ForumPostRepository forumPostRepository, ForumRepository forumRepository, UserRepository userRepository){
        this.forumPostRepository=forumPostRepository;
        this.forumRepository=forumRepository;
        this.userRepository=userRepository;
    }

    public Page<ForumPost> getAllForumPosts(UUID forumId, Pageable pageable){
        return forumPostRepository.findAllByForumId(forumId, pageable);
    }

    public ForumPost getForumPost(UUID forumId, UUID forumPostId){
        if(!forumRepository.existsById(forumId)){
            throw new NoSuchForumException();
        }
        return forumPostRepository.findById(forumPostId).orElseThrow(NoSuchForumPostException::new);
    }

    public ForumPost createForumPost(UUID forumId, ForumPostCreationDto forumPostCreationDto, String email){
        Forum forum=forumRepository.findById(forumId).orElseThrow(NoSuchForumException::new);
        User user=userRepository.findByEmail(email).orElseThrow(UserDoesntExistException::new);
        ForumPost forumPost= forumPostRepository.save(new ForumPost(forum, user, forumPostCreationDto.title(), forumPostCreationDto.content()));
        forumPost.setCreatedAt(LocalDateTime.now());
        forumPost.setUpdatedAt(LocalDateTime.now());
        return forumPost;
    }

    public void deleteForumPost(UUID forumId, UUID forumPostId){
        if(!forumRepository.existsById(forumId)){
            throw new NoSuchForumException();
        }
        if(!forumPostRepository.existsById(forumPostId)){
            throw new NoSuchForumPostException();
        }
        forumPostRepository.deleteById(forumPostId);
    }

    public Page<ForumPost> searchPosts(UUID forumId, String keyword, Pageable pageable){
        if(!forumRepository.existsById(forumId)){
            throw new NoSuchForumException();
        }
        return forumPostRepository.findAllByForumIdAndTitleContainingIgnoreCaseOrForumIdAndContentContainingIgnoreCase(forumId, keyword, forumId, keyword, pageable);
    }
}
