import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ForumPost, ForumPostsService } from '../services/forum-posts.service';
import {ForumComment, ForumCommentsService} from '../services/forum-comment.service';
import {CommonModule, NgFor, NgIf} from '@angular/common';
import {ForumPostComponent} from '../../forums/forum-post/forum-post.component';
import {ForumCommentComponent} from '../../forums/forum-comment/forum-comment.component';
import {AuthStateService, AuthUser} from '../services/state-services/authState.service';
import {FormsModule} from '@angular/forms';
import {PrimaryButtonComponent} from '../../buttons/primary-button.component';
import {TextBoxComponent} from '../../text-input/text-box.component';

@Component({
  selector: 'single-forum',
  standalone: true,
  templateUrl: './single-forum.component.html',
  styleUrls: ['./single-forum.component.css'],
  imports: [
    CommonModule,
    ForumPostComponent,
    ForumCommentComponent,
    FormsModule,
    PrimaryButtonComponent,
    TextBoxComponent
  ]
})
export class SingleForumComponent implements OnInit {
  forumId!: string;
  posts: ForumPost[] = [];
  selectedPost?: ForumPost;
  comments: ForumComment[] = [];
  loadingPosts = true;
  loadingComments = false;

  newPost: { title: string; userId: string | undefined; content: string } = { title: '',  userId: '', content: '' };
  newCommentContent = '';
  posting = false;
  commenting = false;
  user: AuthUser | null = null;

  constructor(
    private authState: AuthStateService,
    private route: ActivatedRoute,
    private forumPostsService: ForumPostsService,
    private forumCommentsService: ForumCommentsService
  ) {
    this.authState.user$.subscribe(user => {
    this.user = user;
  });

    this.authState.loadUserFromStorage();
  }

  addPost(): void {
    if (!this.newPost.title || !this.newPost.content) return;
    this.posting = true;
    this.forumPostsService.createPost(this.forumId, this.newPost).subscribe({
      next: (createdPost) => {
        this.posts.unshift(createdPost);
        this.newPost = { title: this.newPost.title, userId: this.user?.id, content: this.newPost.content };
        this.posting = false;
      },
      error: (err) => {
        console.error('Failed to add post:', err);
        this.posting = false;
      }
    });
  }

  addComment(): void {
    if (!this.newCommentContent || !this.selectedPost) return;
    this.commenting = true;
    this.forumCommentsService.createComment(this.forumId, this.selectedPost.id, { content: this.newCommentContent }).subscribe({
      next: (createdComment) => {
        this.comments.push(createdComment);
        this.newCommentContent = '';
        this.commenting = false;
      },
      error: (err) => {
        console.error('Failed to add comment:', err);
        this.commenting = false;
      }
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.forumId = id;
        this.loadForums();
      }
    });
  }

  loadForums(): void {
    this.loadingPosts = true;
    this.forumPostsService.getAllPosts(this.forumId, 0).subscribe({
      next: (response) => {
        this.posts = response.content;
        this.loadingPosts = false;
      },
      error: (err) => {
        console.error('Failed to load forums:', err);
        this.loadingPosts = false;
      }
    });
  }

  selectPost(post: ForumPost): void {
    this.selectedPost = post;
    this.loadComments(post.id);
  }

  loadComments(postId: string): void {
    this.loadingComments = true;
    this.forumCommentsService.getComments(this.forumId, postId).subscribe({
      next: (comments) => {
        this.comments = comments;
        console.log(comments);
        this.loadingComments = false;
      },
      error: (err) => {
        console.error('Failed to load comments:', err);
        this.loadingComments = false;
      }
    });
  }
}
