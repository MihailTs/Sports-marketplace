import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ForumPost, ForumPostsService } from '../services/forum-posts.service';
import {ForumComment, ForumCommentsService} from '../services/forum-comment.service';
import {CommonModule, NgFor, NgIf} from '@angular/common';
import {ForumPostComponent} from '../../forums/forum-post/forum-post.component';
import {ForumCommentComponent} from '../../forums/forum-comment/forum-comment.component';

@Component({
  selector: 'single-forum',
  standalone: true,
  templateUrl: './single-forum.component.html',
  styleUrls: ['./single-forum.component.css'],
  imports: [
    CommonModule,
    ForumPostComponent,
    ForumCommentComponent
  ]
})
export class SingleForumComponent implements OnInit {
  forumId!: string;
  posts: ForumPost[] = [];
  selectedPost?: ForumPost;
  comments: ForumComment[] = [];
  loadingPosts = true;
  loadingComments = false;

  constructor(
    private route: ActivatedRoute,
    private forumPostsService: ForumPostsService,
    private forumCommentsService: ForumCommentsService
  ) {}

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
