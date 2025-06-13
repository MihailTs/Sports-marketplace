import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ForumPost, ForumPostsService } from '../services/forum-posts.service';
import { ForumComponent } from '../../forums/forum/forum.component';
import { ForumPostComponent } from '../../forums/forum-post/forum-post.component';

@Component({
  selector: 'single-forum',
  standalone: true,
  templateUrl: './single-forum.component.html',
  styleUrls: ['./single-forum.component.css'],
  imports: [CommonModule, ForumComponent, ForumPostComponent]
})
export class SingleForumComponent implements OnInit {
  forumId!: string;
  posts: ForumPost[] = [];
  currentPage: number = 0;
  totalPages: number = 0;
  loading = true;

  constructor(
    private route: ActivatedRoute,
    private forumPostsService: ForumPostsService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.forumId = id;
        this.loadForums(0);
      }
    });
  }

  loadForums(page: number): void {
    this.loading = true;
    this.forumPostsService.getAllPosts(this.forumId, page).subscribe({
      next: (response) => {
        console.log(response);
        this.posts = response.content;
        this.totalPages = response.totalPages;
        this.currentPage = response.page;
        this.loading = false;
      },
      error: (err) => {
        console.error('Failed to load forums:', err);
        this.loading = false;
      }
    });
  }
}
