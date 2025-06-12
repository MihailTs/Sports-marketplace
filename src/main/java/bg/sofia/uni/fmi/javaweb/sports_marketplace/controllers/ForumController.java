package bg.sofia.uni.fmi.javaweb.sports_marketplace.controllers;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.PagedResponse;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.forum.*;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Forum;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.ForumCommentService;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.ForumPostService;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/forums")
public class ForumController {
    private ForumService forumService;
    private ForumPostService forumPostService;
    private ForumCommentService forumCommentService;

    @Autowired
    public ForumController(ForumService forumService, ForumPostService forumPostService, ForumCommentService forumCommentService){
        this.forumService=forumService;
        this.forumPostService=forumPostService;
        this.forumCommentService=forumCommentService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<ForumDto>> getAllForums(@PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(PagedResponse.fromPage(forumService.getAllForums(pageable), ForumDto::fromEntity));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ForumDto> createForum(@RequestBody ForumCreationDto forumDto){
        return ResponseEntity.ok(ForumDto.fromEntity(forumService.createForum(forumDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteForum(@PathVariable UUID id){
        forumService.deleteForum(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<PagedResponse<ForumPostDto>> getAllPostsFromForum(@PathVariable UUID id, @PageableDefault(size=10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(PagedResponse.fromPage(forumPostService.getAllForumPosts(id, pageable), ForumPostDto::fromEntity));
    }

    @PostMapping("/{forumId}/posts")
    public ResponseEntity<ForumPostDto> createPost(@PathVariable UUID forumId, @RequestBody ForumPostCreationDto forumPostCreationDto, Authentication authentication) {
        String email=authentication.getName();
        return ResponseEntity.ok(ForumPostDto.fromEntity(forumPostService.createForumPost(forumId, forumPostCreationDto, email)));
    }

    @GetMapping("/{forumId}/posts/{id}")
    public ResponseEntity<ForumPostDto> getPost(@PathVariable UUID forumId, @PathVariable UUID id){
        return ResponseEntity.ok(ForumPostDto.fromEntity(forumPostService.getForumPost(forumId, id)));
    }

    @PreAuthorize("hasRole('ADMIN')or@securityService.isOwnerOfPost(#forumId, #postId, authentication.principal)")
    @DeleteMapping("/{forumId}/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable UUID forumId, @PathVariable UUID postId){
        forumPostService.deleteForumPost(forumId, postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{forumId}/posts/{postId}/comments")
    public ResponseEntity<List<ForumCommentDto>> getAllComments(@PathVariable UUID forumId, @PathVariable UUID postId){
        return ResponseEntity.ok(forumCommentService.getAllForumComments(forumId, postId).stream().map(ForumCommentDto::fromEntity).toList());
    }

    @PostMapping("/{forumId}/posts/{postId}/comments")
    public ResponseEntity<ForumCommentDto> createComment(@PathVariable UUID forumId, @PathVariable UUID postId, @RequestBody ForumCommentCreationDto forumCommentCreationDto, Authentication authentication){
        String email=authentication.getName();
        return ResponseEntity.ok(ForumCommentDto.fromEntity(forumCommentService.createComment(forumId, forumCommentCreationDto, email, postId)));
    }

    @PreAuthorize("hasRole('ADMIN')or@securityService.isOwnerOfComment(#forumId, #postId, #commentId, authentication.principal)")
    @DeleteMapping("/{forumId}/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable UUID forumId, @PathVariable UUID postId, @PathVariable UUID commentId){
        forumCommentService.deleteForumComment(forumId, postId, commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{forumId}/posts/{postId}/comments/{commentId}")
    public ResponseEntity<ForumCommentDto> getComment(@PathVariable UUID forumId, @PathVariable UUID postId, @PathVariable UUID commentId){
        return ResponseEntity.ok(ForumCommentDto.fromEntity(forumCommentService.getForumComment(forumId, postId, commentId)));
    }

    @GetMapping("{forumId}/posts/search")
    public ResponseEntity<PagedResponse<ForumPostDto>> searchPosts(@PathVariable UUID forumId, @RequestParam String keyword, @PageableDefault(size=10, sort="updatedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(PagedResponse.fromPage(forumPostService.searchPosts(forumId, keyword, pageable), ForumPostDto::fromEntity));
    }
}
