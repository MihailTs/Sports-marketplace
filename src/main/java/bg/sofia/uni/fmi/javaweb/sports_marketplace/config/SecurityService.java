package bg.sofia.uni.fmi.javaweb.sports_marketplace.config;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Event;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Forum;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ForumComment;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ForumPost;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.ForumCommentRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.EventService;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.ForumCommentService;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityService {
    private EventService eventService;
    private ForumCommentService forumCommentService;
    private ForumPostService forumPostService;
    @Autowired
    public SecurityService(EventService eventService, ForumCommentService forumCommentService, ForumPostService forumPostService){
        this.eventService=eventService;
        this.forumCommentService=forumCommentService;
        this.forumPostService=forumPostService;
    }
    public boolean isOwnerOfEvent(UUID eventId, String email) {
        Event event = eventService.getEventById(eventId);
        return event.getCreatedBy().getEmail().equals(email);
    }

    public boolean isOwnerOfComment(UUID forumId, UUID forumPostId, UUID commentId, String email) {
        ForumComment forumComment = forumCommentService.getForumComment(forumId, forumPostId, commentId);
        return forumComment.getUser().getEmail().equals(email);
    }

    public boolean isOwnerOfPost(UUID forumId, UUID forumPostId, String email) {
        ForumPost forumPost = forumPostService.getForumPost(forumId, forumPostId);
        return forumPost.getUser().getEmail().equals(email);
    }


}
