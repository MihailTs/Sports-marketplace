package bg.sofia.uni.fmi.javaweb.sports_marketplace.repository;


import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ForumPostRepository extends JpaRepository<ForumPost, UUID> {
    List<ForumPost> findAllByForumId(UUID forumId);
    @Modifying
    @Transactional
    @Query("UPDATE ForumPost fp SET fp.user.id = :deletedUserId WHERE fp.user.id = :originalUserId")
    void updateUserToDeleted(@Param("originalUserId") UUID originalUserId,
                             @Param("deletedUserId") UUID deletedUserId);

}
