package bg.sofia.uni.fmi.javaweb.sports_marketplace.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity
@NoArgsConstructor
@Table(name = "forum_comment")
public class ForumComment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id")
    private ForumPost forumPost;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public ForumComment(User user, ForumPost forumPost, String content){
        this.user=user;
        this.forumPost=forumPost;
        this.content=content;
        this.createdAt=LocalDateTime.now();
    }
}
