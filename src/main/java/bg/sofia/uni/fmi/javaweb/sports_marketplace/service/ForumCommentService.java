package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum.ForumCommentCreationDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.NoSuchForumCommentException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.NoSuchForumException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.NoSuchForumPostException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UserDoesntExistException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ForumComment;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ForumPost;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.ForumCommentRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.ForumPostRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.ForumRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ForumCommentService {
    private ForumCommentRepository forumCommentRepository;
    private ForumPostRepository forumPostRepository;
    private ForumRepository forumRepository;
    private UserRepository userRepository;

    public ForumCommentService(ForumCommentRepository forumCommentRepository, ForumPostRepository forumPostRepository, ForumRepository forumRepository, UserRepository userRepository){
        this.forumCommentRepository=forumCommentRepository;
        this.forumPostRepository=forumPostRepository;
        this.userRepository=userRepository;
        this.forumRepository=forumRepository;
    }

    public List<ForumComment> getAllForumComments(UUID forumId, UUID forumPostId){
        if(!forumRepository.existsById(forumId)){
            throw new NoSuchForumException();
        }
        return forumCommentRepository.findAllByForumPostId(forumPostId);
    }

    public ForumComment getForumComment(UUID forumId, UUID forumPostId, UUID forumCommentId){
        if(!forumRepository.existsById(forumId)){
            throw new NoSuchForumException();
        }
        return forumCommentRepository.findByIdAndForumPostIdAndForumPostForumId(forumCommentId, forumPostId, forumId).orElseThrow(NoSuchForumCommentException::new);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteForumComment(UUID forumId, UUID forumPostId, UUID forumCommentId){
        if(!forumRepository.existsById(forumId)){
            throw new NoSuchForumException();
        }
        if(!forumPostRepository.existsById(forumPostId)){
            throw new NoSuchForumPostException();
        }
        forumCommentRepository.deleteById(forumCommentId);
    }

    public ForumComment createComment(UUID forumId, ForumCommentCreationDto forumCommentCreationDto, String email, UUID postId){
        if(!forumRepository.existsById(forumId)){
            throw new NoSuchForumException();
        }
        User user=userRepository.findByEmail(email).orElseThrow(UserDoesntExistException::new);
        ForumPost forumPost=forumPostRepository.findById(postId).orElseThrow(NoSuchForumPostException::new);
        return forumCommentRepository.save(new ForumComment(user, forumPost, forumCommentCreationDto.content()));
    }
}
