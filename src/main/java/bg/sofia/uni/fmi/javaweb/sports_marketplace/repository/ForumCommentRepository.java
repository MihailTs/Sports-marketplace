package bg.sofia.uni.fmi.javaweb.sports_marketplace.repository;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ForumComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ForumCommentRepository extends JpaRepository<ForumComment, UUID> {
    List<ForumComment> findAllByForumPostId(UUID forumPostId);
    Optional<ForumComment> findByIdAndForumPostIdAndForumPostForumId(UUID forumPostId, UUID id, UUID ForumPostForumId);

    @Modifying
    @Transactional
    @Query("UPDATE ForumComment fc SET fc.user.id = :deletedUserId WHERE fc.user.id = :originalUserId")
    void updateUserToDeleted(@Param("originalUserId") UUID originalUserId,
                             @Param("deletedUserId") UUID deletedUserId);
}
