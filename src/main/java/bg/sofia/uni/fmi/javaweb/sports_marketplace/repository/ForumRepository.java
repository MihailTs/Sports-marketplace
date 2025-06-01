package bg.sofia.uni.fmi.javaweb.sports_marketplace.repository;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ForumRepository extends JpaRepository<Forum, UUID> {
}
